package com.example.drapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompatExtras;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.drapplication.R;

public class DoctorSignUP extends AppCompatActivity {
    private EditText firstName;
    private EditText middleName;
    private EditText lastName;
    private EditText phoneNumber;
    private EditText Email;
    private EditText password;
    private Button Next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_sign_u_p);

        firstName=(EditText)findViewById(R.id.Doctorfirst_name);
        middleName=(EditText)findViewById(R.id.Doctormiddle_name);
        lastName=(EditText)findViewById(R.id.Doctorlast_name);
        phoneNumber=(EditText)findViewById(R.id.Doctorphone_no);
        Email=(EditText)findViewById(R.id.Doctoremail_id);
        password=(EditText)findViewById(R.id.Doctorpassword);
        Next=(Button) findViewById(R.id.next_Button);

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(firstName.getText().toString())||TextUtils.isEmpty(lastName.getText().toString())||TextUtils.isEmpty(middleName.getText().toString())
                ||TextUtils.isEmpty(phoneNumber.getText().toString())||TextUtils.isEmpty(Email.getText().toString())||TextUtils.isEmpty(password.getText().toString()))
                {
                    Toast.makeText(DoctorSignUP.this,"All fields are mandatory to fill",Toast.LENGTH_LONG).show();
                }
                else {
                    aageJabc();
                }
                         }
        });
    }
    public void aageJabc()
    {
        Intent intent=new Intent(this,DoctorQualificationActivity.class);
        intent.putExtra("FirstName",firstName.getText().toString());
        intent.putExtra("LastName",lastName.getText().toString());
        intent.putExtra("MiddleName",middleName.getText().toString());
        intent.putExtra("PhnNo",phoneNumber.getText().toString());
        intent.putExtra("EmailId",Email.getText().toString());
        intent.putExtra("Password",password.getText().toString());
        startActivity(intent);
        finish();
    }
}