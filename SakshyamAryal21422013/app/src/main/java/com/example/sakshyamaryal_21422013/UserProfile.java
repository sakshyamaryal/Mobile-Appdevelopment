package com.example.sakshyamaryal_21422013;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class UserProfile extends AppCompatActivity {

    TextView uname,firstname,lastname,password,userEmail,regdate,update;
    Button btn;
    Calendar calendar;
    String contextDate;
    TextView textDate_TV,editProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        calendar = Calendar.getInstance();
        contextDate = DateFormat.getDateInstance().format(calendar.getTime());
        textDate_TV = findViewById(R.id.dateToday);
        textDate_TV.setText(contextDate);

        firstname = findViewById(R.id.userloginname);
        lastname = findViewById(R.id.userLastName);
        password = findViewById(R.id.userPassword);
        userEmail = findViewById(R.id.emaild);
        regdate = findViewById(R.id.userDateregistered);
        update = findViewById(R.id.userDateupdated);
        editProfile = findViewById(R.id.editProf);

        btn = findViewById(R.id.timeline);
        uname = findViewById(R.id.firstName);

        Intent intent = getIntent(); // passing intent
        String userdetailname = intent.getStringExtra("NAME");
        Toast.makeText(this,userdetailname, Toast.LENGTH_SHORT).show();
        String dates = intent.getStringExtra("date");
        uname.setText(userdetailname);

        DatabaseHelperClass db = new DatabaseHelperClass(UserProfile.this); // calling the database helper class to get the stored data
        UserDetails  userDetails = db.getUserProfileDetails(userdetailname);

        firstname.setText(userDetails.getFname());
        lastname.setText(userDetails.getLname());
        password.setText(userDetails.getPassword());
        userEmail.setText(userDetails.getEmail());
        update.setText(userDetails.getDateUpdated());
        regdate.setText(userDetails.getDateRegistered()); // setting text in text view that was stored in edit text widget


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(UserProfile.this, GeneralTimeline.class);
                in.putExtra("NAME", userDetails.getFname());
                in.putExtra("date", userDetails.getDateUpdated());
                startActivity(in);
            }
        });
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(UserProfile.this, ChangeProfile.class);
                in.putExtra("NAME", userDetails.getFname());
                in.putExtra("date", userDetails.getDateUpdated());
                startActivity(in);
            }
        });

    }
}