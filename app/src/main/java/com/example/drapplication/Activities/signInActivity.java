package com.example.drapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.drapplication.R;

import java.text.DateFormat;

public class signInActivity extends AppCompatActivity {
    private Button userLogin;
    private Button DoctorLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        userLogin=(Button)findViewById(R.id.signInasUser);
        DoctorLogin=(Button)findViewById(R.id.signInasDoctor);

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginInasuser();
            }
        });
        DoctorLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginInasdoctor();
            }
        });
    }
    public void loginInasuser()
    {
        startActivity(new Intent(this,LoginUser.class));
        finish();
    }
    public void loginInasdoctor()
    {
        startActivity(new Intent(this,loginInDr.class));
        finish();
    }
}