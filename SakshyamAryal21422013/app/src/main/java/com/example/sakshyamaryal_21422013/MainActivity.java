package com.example.sakshyamaryal_21422013;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText user,pass;
    Button log;
    TextView register,date;

    SQLiteDatabase sqLiteDatabase;
    DatabaseHelperClass userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = findViewById(R.id.username);
        pass = findViewById(R.id.password1);
        log = findViewById(R.id.login);
        register = findViewById(R.id.register);

        Intent intent = getIntent(); // get intent
        String date = intent.getStringExtra("dateUpdated");



        userDB = new DatabaseHelperClass(this); // dynamic class alocation

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = user.getText().toString(); // get text in text view
                String password = pass.getText().toString();

                if (TextUtils.isEmpty(uname) || TextUtils.isEmpty(password)){
                    Toast.makeText(MainActivity.this, "Username and Password must be filled.", Toast.LENGTH_SHORT).show();
                }

                else {
                    Boolean checkUsernamePassword = userDB.checkUserLoginDetails(uname, password);
                    if (checkUsernamePassword == true){
                        Toast.makeText(MainActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(getApplicationContext(), UserProfile.class); // navigate to other page if boolean conditon in database is true
                        in.putExtra("NAME", uname);
                        in.putExtra("date",date);
                        startActivity(in);


                    }
                    else if ((uname.equals("admin") && password.equals("admin"))) {
                        Intent intent = new Intent(MainActivity.this, Admin.class);
                        intent.putExtra("NAME", uname);
                        Toast.makeText(MainActivity.this, "Welcome admin", Toast.LENGTH_SHORT).show();
                        startActivity(intent); // admin user id and password
                        startActivity(intent); // admin user id and password

                    }

                    else {
                        Toast.makeText(MainActivity.this, "login invalid", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), RegistrationPage.class);
                startActivity(in); // navigate to registration page
                startActivity(in); // navigate to registration page
            }
        });
    }



}
