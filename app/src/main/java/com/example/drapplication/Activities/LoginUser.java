package com.example.drapplication.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
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

public class LoginUser extends AppCompatActivity {
    private EditText useremail;
    private EditText userpassword;
    private Button loginuserbutton;
    private FirebaseAuth userAuth;
    private FirebaseAuth.AuthStateListener userAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        useremail = (EditText) findViewById(R.id.login_email);
        userpassword= (EditText) findViewById(R.id.login_password);
        loginuserbutton = (Button) findViewById(R.id.user_loginButton);
        userAuth = FirebaseAuth.getInstance();
        userAuthListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null){
                    //Next activity lagao idhar
                }
            }
        };
        loginuserbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startusersignin();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        userAuth.addAuthStateListener(userAuthListener);
    }

    private void startusersignin(){
        String email = useremail.getText().toString();
        String password= userpassword.getText().toString();
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            Toast.makeText(LoginUser.this , "Fields are empty..", Toast.LENGTH_LONG).show();
        }else {
            userAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        checkIfEmailVerified();
                    }
                    else if(!task.isSuccessful()){
                        Toast.makeText(LoginUser.this , "Authentication failed..", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
    private void checkIfEmailVerified()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user.isEmailVerified())
        {
            // user is verified, so you can finish this activity or send user to activity which you want.

            Toast.makeText(LoginUser.this, "Successfully logged in..", Toast.LENGTH_SHORT).show();
        }
        else
        {
            // email is not verified, so just prompt the message to the user and restart this activity.
            // NOTE: don't forget to log out the user.
            Toast.makeText(LoginUser.this, "Verify your account..", Toast.LENGTH_SHORT).show();
            //FirebaseAuth.getInstance().signOut();

            //restart this activity

        }
    }
}