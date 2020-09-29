package com.example.drapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.drapplication.R;

public class signUpActivity extends AppCompatActivity {
    private Button UserSignUpButton;
    private Button DoctorSignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        UserSignUpButton=(Button)findViewById(R.id.signUpAsUser);
        DoctorSignUpButton=(Button)findViewById(R.id.signUpAsDoctor);


        UserSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpasUser();
            }
        });
        DoctorSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpasDoctor();
            }
        });

    }
    public void signUpasDoctor()
    {
        Intent intent = new Intent(this,DoctorSignUP.class);
        startActivity(intent);
        finish();
    }
    public void signUpasUser()
    {
        Intent intent =new Intent(this,SignInuser.class);
        startActivity(intent);
        finish();
    }
}