package com.example.sakshyamaryal_21422013;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChangeProfile extends AppCompatActivity {
    EditText firstname,lastname,password,userEmail,regdate,update;
    Button btn;


    DatabaseHelperClass databaseHelperClass = new DatabaseHelperClass(this); // get database helper object
    TextView textDate, editProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_profile);

        firstname = findViewById(R.id.user_first_name);
        lastname = findViewById(R.id.user_lastname);
        password = findViewById(R.id.user_password);
        userEmail = findViewById(R.id.user_email);

        btn = findViewById(R.id.Save);

        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date()); // get current date

        Intent intent = getIntent(); // passing intent
        String userdetailname = intent.getStringExtra("NAME"); //get value of user login


        DatabaseHelperClass db = new DatabaseHelperClass(ChangeProfile.this);
        UserDetails details = db.getUserProfileDetails(userdetailname);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelperClass ud = new DatabaseHelperClass(getApplicationContext());

                String firstName,lastName,passWord,email;
                // convert the information to edit text widget
                firstName =  firstname.getText().toString();
                lastName = lastname.getText().toString();
                passWord = password.getText().toString();
                email = userEmail.getText().toString();

                UserDetails userDetails = new UserDetails();

                // set details
                userDetails.setFname(firstName);
                userDetails.setLname(lastName);
                userDetails.setPassword(passWord);
                userDetails.setEmail(email);

                // condition with parameter from databasehelper class
                if (ud.update(userDetails,userdetailname)){
                    Intent intent1 = new Intent(ChangeProfile.this,UserProfile.class);
                    intent1.putExtra("NAME", userDetails.getEmail());
                    Toast.makeText(ChangeProfile.this, "Profile updated", Toast.LENGTH_SHORT).show();
                    startActivity(intent1);
                }
                else {
                    Toast.makeText(ChangeProfile.this, "Restart to save again.", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
