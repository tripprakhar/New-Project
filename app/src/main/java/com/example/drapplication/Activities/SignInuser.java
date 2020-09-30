package com.example.drapplication.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.drapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignInuser extends AppCompatActivity {
    private EditText UserFirstName;
    private EditText UserMiddleName;
    private EditText UserLastName;
    private EditText UserPhoneNo;
    private EditText UserEmail;
    private EditText UserPassword;
    private EditText UserAge;
    private Button UserSignUp;
    private DatabaseReference mDataBaseReference;
    private FirebaseAuth mAuth;
    private ProgressDialog mProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_inuser);

        UserFirstName=(EditText) findViewById(R.id.userfirst_name);
        UserMiddleName=(EditText)findViewById(R.id.usermiddle_name);
        UserLastName=(EditText)findViewById(R.id.userlast_name);
        UserPhoneNo=(EditText)findViewById(R.id.userphone_no);
        UserEmail=(EditText)findViewById(R.id.useremail_id);
        UserPassword=(EditText)findViewById(R.id.userpassword);
        UserAge=(EditText)findViewById(R.id.userage);
        UserSignUp=(Button)findViewById(R.id.user_signUpButton);

        mAuth=FirebaseAuth.getInstance();

        mProgress=new ProgressDialog(this);

        UserSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUserAccount();
            }
        });

    }

    public void createUserAccount()
    {
        if(TextUtils.isEmpty(UserFirstName.getText().toString())||TextUtils.isEmpty(UserMiddleName.getText().toString())||TextUtils.isEmpty(UserLastName.getText().toString())
                ||TextUtils.isEmpty(UserPhoneNo.getText().toString())||TextUtils.isEmpty(UserEmail.getText().toString())||TextUtils.isEmpty(UserPassword.getText().toString())
            ||TextUtils.isEmpty(UserAge.getText().toString()))
        {
            Toast.makeText(SignInuser.this,"All fields are mandatory to fill",Toast.LENGTH_LONG).show();
        }
        else
        {
          register();
        }
    }
    public void register()
    {
        String email=UserEmail.getText().toString().trim();
        String password=UserPassword.getText().toString().trim();
        final String first=UserFirstName.getText().toString().trim();
        final String middle=UserMiddleName.getText().toString().trim();
        final String phone=UserPhoneNo.getText().toString().trim();
        final String last =UserLastName.getText().toString().trim();
        final String age=UserAge.getText().toString().trim();
        mProgress.setMessage("Creating Account.....");
        mProgress.show();
               mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       mProgress.dismiss();
                       if(task.isSuccessful())
                       {
                           HashMap<String,String> m=new HashMap<>();
                          m.put("firstname",first);
                          m.put("middleName",middle);
                          m.put("lastName",last);
                          m.put("Phone Number",phone);
                          m.put("Age",age);
                          FirebaseDatabase.getInstance().getReference("UsersDetails").child(mAuth.getCurrentUser().getUid())
                          .setValue(m).addOnCompleteListener(new OnCompleteListener<Void>() {
                              @Override
                              public void onComplete(@NonNull Task<Void> task) {
                                  if(task.isSuccessful())
                                  {
                                      Toast.makeText(SignInuser.this, "Account Registered", Toast.LENGTH_SHORT).show();
                                      sendVerificationEmail();
                                  }
                                  else
                                  {
                                      Toast.makeText(SignInuser.this, "Account Not Registered", Toast.LENGTH_SHORT).show();
                                  }
                              }
                          });
                       }
                       else
                       {
                           Toast.makeText(SignInuser.this,"Something went wrong",Toast.LENGTH_LONG).show();
                       }
                   }
               });

    }
    private void sendVerificationEmail()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // email sent


                            // after email is sent just logout the user and finish this activity
                            FirebaseAuth.getInstance().signOut();
                            startActivity(new Intent(SignInuser.this, LoginUser.class));
                            finish();
                        }
                        else
                        {
                            // email not sent, so display message and restart the activity or do whatever you wish to do

                            //restart this activity
                            overridePendingTransition(0, 0);
                            finish();
                            overridePendingTransition(0, 0);
                            startActivity(getIntent());

                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}