package com.example.drapplication.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.drapplication.R;

public class DoctorQualificationActivity extends AppCompatActivity {
    private ImageButton profilePic;
    private EditText degreeName;
    private EditText Specialization;
    private EditText yearsOfExperience;
    private EditText presentlyWorking;
    private Button doctorSignUp;
    private Uri mImageUri;
    private static final int GALLERY_CODE=1;

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

        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery=new Intent(Intent.ACTION_GET_CONTENT);
                gallery.setType("image/*");
                startActivityForResult(gallery,GALLERY_CODE);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GALLERY_CODE&&resultCode==RESULT_OK);
        {
         mImageUri=data.getData();
         profilePic.setImageURI(mImageUri);
        }
    }
}