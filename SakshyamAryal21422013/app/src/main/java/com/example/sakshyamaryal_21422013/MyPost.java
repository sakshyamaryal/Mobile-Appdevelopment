package com.example.sakshyamaryal_21422013;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MyPost extends AppCompatActivity {

    DatabaseHelperClass db;
    SQLiteDatabase sqlDatabase;
    RecyclerView recyclerview;
    MyPostAdapter iAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_post);
        db = new DatabaseHelperClass(this);

        Intent intent = getIntent(); // passing intent
        String userdetailname = intent.getStringExtra("NAME");

        idnumber();
        viewPosts(userdetailname);

        recyclerview.setLayoutManager(new LinearLayoutManager(this));
    }




    private void viewPosts(String username) {
        sqlDatabase = db.getReadableDatabase();
        Cursor fetch = sqlDatabase.rawQuery("Select * from posts where username = ? ", new String[] {username});
        ArrayList<CommentClass> userCommentArrayList = new ArrayList<>();

        while (fetch.moveToNext()){
            int id = fetch.getInt(0);
            String post = fetch.getString(1);
            String name = fetch.getString(2);
            String date = fetch.getString(3);
            userCommentArrayList.add(new CommentClass(id,post,name,date));

        }
        fetch.close();
        iAdapter = new MyPostAdapter(this,R.layout.singledata_mypost,userCommentArrayList, sqlDatabase);
        recyclerview.setAdapter(iAdapter);
    }



    private void idnumber() {
        recyclerview = findViewById(R.id.rview_2);
    }
}

