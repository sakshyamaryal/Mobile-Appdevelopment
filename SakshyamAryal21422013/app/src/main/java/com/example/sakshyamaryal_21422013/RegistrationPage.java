package com.example.sakshyamaryal_21422013;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistrationPage extends AppCompatActivity {
    Button registration;
    EditText name,password,repass,lastName,emailAddress;
    TextView login;

    DatabaseHelperClass userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        registration = findViewById(R.id.button);
        name = findViewById(R.id.uname);
        password = findViewById(R.id.password);
        repass = findViewById(R.id.confirmpass);
        login = findViewById(R.id.loginpage);
        lastName = findViewById(R.id.uLastname);
        emailAddress =findViewById(R.id.email);


        userDB = new DatabaseHelperClass(this);

        goToLogin();

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = name.getText().toString();
                String security = password.getText().toString();
                String reEnterPassword = repass.getText().toString();
                String lname = lastName.getText().toString();
                String email = emailAddress.getText().toString();
                String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());


                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(security) || TextUtils.isEmpty(reEnterPassword)|| TextUtils.isEmpty(lname)|| TextUtils.isEmpty(email)){
                    Toast.makeText(RegistrationPage.this, "Fill Up all the fields.", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (security.equals(reEnterPassword)){
                        Boolean checkUserInfo = userDB.checkUserdetails(username,security,lname,email); // checking the re password

                        if (!checkUserInfo ){
                            Boolean insertData = userDB.insertData(username,security,lname,email,date,date); // store data

                            if (insertData){
                                // pass to login activity
                                Toast.makeText(RegistrationPage.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                intent.putExtra("dateUpdated",date);
                                startActivity(intent);
                                
                            }
                            else{
                                Toast.makeText(RegistrationPage.this, "Registration Unsuccessful/email already exists", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(RegistrationPage.this, "User already existed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(RegistrationPage.this, "Password not matched", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }

    private void goToLogin() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),GeneralTimeline.class);
                startActivity(intent);
            }
        });
    }


}