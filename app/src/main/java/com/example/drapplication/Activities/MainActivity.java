package com.example.drapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.drapplication.R;

public class MainActivity extends AppCompatActivity
{
    private Button signinbutton;
    private Button signupbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signinbutton=(Button) findViewById(R.id.signinId);
        signupbutton=(Button) findViewById(R.id.signupId);


    }
    public void gotosignin (View view){
        Intent intent = new Intent (this,  signInActivity.class);
        startActivity(intent);
    }
    public void gotosignup (View view){
        Intent intent = new Intent (this,  signUpActivity.class);
        startActivity(intent);
    }

}