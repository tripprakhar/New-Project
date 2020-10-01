package com.example.drapplication.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.drapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.HashMap;

public class DoctorQualificationActivity extends AppCompatActivity {
    private ImageButton profilePic;
    private EditText degreeName;
    private EditText Specialization;
    private EditText yearsOfExperience;
    private EditText presentlyWorking;
    private Button doctorSignUp;
    private Uri mImageUri;
    public Uri resultUri=null;
    private ProgressDialog mProgress;
    private static final int GALLERY_CODE=1;

    private FirebaseAuth mAuth;

    private StorageReference mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_qualification);
        profilePic= (ImageButton)findViewById(R.id.profile_pic);
        degreeName=(EditText)findViewById(R.id.degree_name);
        Specialization=(EditText)findViewById(R.id.specialization_name);
        yearsOfExperience=(EditText)findViewById(R.id.doctor_experience);
        presentlyWorking=(EditText)findViewById(R.id.presently_working);
        doctorSignUp=(Button)findViewById(R.id.finalDoctorDetails);
        mProgress=new ProgressDialog(this);

        mAuth=FirebaseAuth.getInstance();

        mStorage=FirebaseStorage.getInstance().getReference().child("Doctor_Profile_Pic");

        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery=new Intent(Intent.ACTION_GET_CONTENT);
                gallery.setType("image/*");
                startActivityForResult(gallery,GALLERY_CODE);

            }
        });
        doctorSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccountDoctor();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GALLERY_CODE&&resultCode==RESULT_OK)
        {
         mImageUri=data.getData();
            CropImage.activity(mImageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .start(this);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                resultUri = result.getUri();
                profilePic.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }

    }
    public void createAccountDoctor()
    {
      if(TextUtils.isEmpty(degreeName.getText().toString())||TextUtils.isEmpty(Specialization.getText().toString())||TextUtils.isEmpty(yearsOfExperience.getText().toString())
      ||TextUtils.isEmpty(presentlyWorking.getText().toString())||resultUri==null)
      {
          Toast.makeText(DoctorQualificationActivity.this,"All feilds are mandatory",Toast.LENGTH_LONG).show();
      }
      else
      {
          register();
      }
    }
    public void register()
    {

       final String degree=degreeName.getText().toString().trim();
       final String specialization= Specialization.getText().toString().trim();
       final String years=yearsOfExperience.getText().toString().trim();
       final String present=presentlyWorking.getText().toString().trim();
       Intent intent=getIntent();
       final String first=intent.getStringExtra("FirstName").trim();
       final String middle=intent.getStringExtra("MiddleName").trim();
       final String last=intent.getStringExtra("LastName").trim();
       String email=intent.getStringExtra("EmailId").trim();
       String password=intent.getStringExtra("Password").trim();
       final String phone=intent.getStringExtra("PhnNo").trim();
       Log.d("check",email);
        mProgress.setMessage("Creating Account...");
        mProgress.show();
       mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
               mProgress.dismiss();
               if(task.isSuccessful()) {

                   HashMap<String,String> m=new HashMap<>();
                   m.put("firstname",first);
                   m.put("middleName",middle);
                   m.put("lastName",last);
                   m.put("Phone Number",phone);
                   m.put("Degree",degree);
                   m.put("Speciality",specialization);
                   m.put("Years",years);
                   m.put("Working",present);
                   FirebaseDatabase.getInstance().getReference("DoctorsDetails").child(mAuth.getCurrentUser().getUid())
                           .setValue(m).addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                           if(task.isSuccessful())
                           {
                               Toast.makeText(DoctorQualificationActivity.this, "Account Registered", Toast.LENGTH_SHORT).show();
                           }
                           else
                           {
                               Toast.makeText(DoctorQualificationActivity.this, "Account Not Registered", Toast.LENGTH_SHORT).show();
                           }
                       }
                   });

               }
               else
               {
                   Toast.makeText(DoctorQualificationActivity.this,"Something went wrong",Toast.LENGTH_LONG).show();
               }

           }
       });

    }
}