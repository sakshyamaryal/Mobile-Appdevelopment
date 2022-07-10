package com.example.sakshyamaryal_21422013;


import static com.example.sakshyamaryal_21422013.DatabaseHelperClass.TABLE_NAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class GeneralTimeline extends AppCompatActivity {
    DatabaseHelperClass db;
    SQLiteDatabase sqlDatabase;
    RecyclerView recyclerview;
    ImplementAdapter iAdapter;
    FloatingActionButton btnpost,btn_viewMyPost,btn_refresh,btn_prof;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_timeline);
        db = new DatabaseHelperClass(this);
        btnpost = findViewById(R.id.floatingActionButton);
        btn_viewMyPost = findViewById(R.id.floatingActionButton2);
        btn_refresh = findViewById(R.id.floatingActionButton4);

        Intent intent = getIntent(); // passing intent
        String userdetailname = intent.getStringExtra("NAME");
        Toast.makeText(this,userdetailname, Toast.LENGTH_SHORT).show();
        String dates = intent.getStringExtra("date");


        idnumber();
        viewPosts();
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        btnpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(GeneralTimeline.this,PostData.class);
                in.putExtra("NAME",userdetailname);
                in.putExtra("date",dates);
                startActivity(in);
            }
        });



        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
            }
        });

        btn_viewMyPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(GeneralTimeline.this,MyPost.class);
                in.putExtra("NAME",userdetailname);
                in.putExtra("date",dates);
                startActivity(in);
            }
        });
    }


    private void viewPosts() {
        sqlDatabase = db.getReadableDatabase(); // return readable data
        Cursor fetch = sqlDatabase.rawQuery("select * from " + TABLE_NAME ,null); // query to manipulate from cursor
        ArrayList<CommentClass> userCommentArrayList = new ArrayList<>(); // array list for post

        while (fetch.moveToNext()){ // condition to get index of the array list
            int id = fetch.getInt(0);
            String post = fetch.getString(1);
            String name = fetch.getString(2);
            String date = fetch.getString(3);
            userCommentArrayList.add(new CommentClass(id,post,name,date));

        }
        fetch.close();
        iAdapter = new ImplementAdapter(this,R.layout.singledata,userCommentArrayList, sqlDatabase); // get the layout in this class
        recyclerview.setAdapter(iAdapter);
    }



    private void idnumber() {
        recyclerview = findViewById(R.id.rview);
    }
}