package com.example.drapplication.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class loginInDr extends AppCompatActivity {
    private EditText docemail;
    private EditText docpassword;
    private Button logindocbutton;
    private FirebaseAuth docAuth;
    private FirebaseAuth.AuthStateListener docAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_in_dr);
        docemail = (EditText) findViewById(R.id.Drlogin_email);
        docpassword = (EditText) findViewById(R.id.Drlogin_password);
        logindocbutton = (Button) findViewById(R.id.Druser_loginButton);
        docAuth = FirebaseAuth.getInstance();
        docAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    //Next activity lagao idhar
                }
            }
        };
        logindocbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startdocsignin();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        docAuth.addAuthStateListener(docAuthListener);
    }
    private void startdocsignin(){
        String email = docemail.getText().toString();
        String password= docpassword.getText().toString();
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            Toast.makeText(loginInDr.this , "Fields are empty..", Toast.LENGTH_LONG).show();
        }else {
            docAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(loginInDr.this  , "Authentication failed..", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}