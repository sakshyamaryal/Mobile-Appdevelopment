package com.example.sakshyamaryal_21422013;

import static com.example.sakshyamaryal_21422013.DatabaseHelperClass.TABLE_one;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Admin extends AppCompatActivity {

    DatabaseHelperClass db; //database helper class
    SQLiteDatabase sqlDatabase;
    RecyclerView recyclerview;
    AdminAdapter Adapter;
    FloatingActionButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        db = new DatabaseHelperClass(this); // to create object

        Intent intent = getIntent(); // getting intent
        String userdetailname = intent.getStringExtra("NAME"); // navigated name

        idnumber();
        viewusers();

        recyclerview.setLayoutManager(new LinearLayoutManager(this)); // setting layout for

    }


    private void viewusers() {
        sqlDatabase = db.getReadableDatabase(); //  returns readonly database
        Cursor fetch = sqlDatabase.rawQuery("select * from " + TABLE_one ,null); // to manipulate the database
        ArrayList<UserDetails> userDetails = new ArrayList<>();
        // condition
        while (fetch.moveToNext()){

            String fname= fetch.getString(0); // search index for each database properties
            String password = fetch.getString(1);
            String lname = fetch.getString(2);
            String email = fetch.getString(3);
            String dateUpdated = fetch.getString(4);
            String dateRegistered = fetch.getString(5);
            userDetails.add(new UserDetails(fname,password,lname,email,dateUpdated,dateRegistered));


        }
        fetch.close();
        Adapter = new AdminAdapter(this,R.layout.admin_users,userDetails, sqlDatabase); // call adapter to show the layout in which users are stored
        recyclerview.setAdapter(Adapter); //
    }


    private void idnumber() {
        recyclerview = findViewById(R.id.rview_3);
    }
}