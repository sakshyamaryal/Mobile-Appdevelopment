package com.example.sakshyamaryal_21422013;

import static com.example.sakshyamaryal_21422013.DatabaseHelperClass.TABLE_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class PostData extends AppCompatActivity {
    TextView name,dates;
    EditText post,title;
    Button postButton,viewButton,editButton;
    DatabaseHelperClass db;
    SQLiteDatabase sqLiteDatabase;
    int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_data);

        Intent intent = getIntent(); // passing intent
        String username = intent.getStringExtra("NAME");
        String date = intent.getStringExtra("date");

        db = new DatabaseHelperClass(this);

        getUserid();
        fillData(username,date);
        editPostData();



    }

    private void editPostData() {
        if(getIntent().getBundleExtra("userComment")!=null){
            Bundle editComment = getIntent().getBundleExtra("userComment");
            id = editComment.getInt("id");
            post.setText(editComment.getString("post"));
            editButton.setVisibility(View.VISIBLE);
            postButton.setVisibility(View.GONE);

        }
    }


    private void fillData(String username,String date) {
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues content = new ContentValues(); // content values with multiple put method to get the information
                content.put("post",post.getText().toString());
                content.put("username",username);
                content.put("date",date);

                sqLiteDatabase = db.getWritableDatabase();
                Long res = sqLiteDatabase.insert(TABLE_NAME,null,content);
                if(res!=null){
                    Toast.makeText(PostData.this, "Comment Posted", Toast.LENGTH_SHORT).show();
                    post.setText("");

                }
                else {
                    Toast.makeText(PostData.this, "Post Not Posted", Toast.LENGTH_SHORT).show();
                }

            }
        });

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PostData.this, GeneralTimeline.class);
                intent.putExtra("NAME",username);
                intent.putExtra("date",date);
                startActivity(intent);
            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues content = new ContentValues();
                content.put("post",post.getText().toString());
                sqLiteDatabase = db.getReadableDatabase();
                long result = sqLiteDatabase.update(TABLE_NAME,content,"id="+id,null);
                if (result < 0){
                    Toast.makeText(PostData.this, "Post not edited", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(PostData.this, "Post edited", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getUserid() {
        name = findViewById(R.id.name);
        dates = findViewById(R.id.toDate);
        post = findViewById(R.id.comment);
        postButton = findViewById(R.id.postComment);
        viewButton = findViewById(R.id.viewpost);
        editButton = findViewById(R.id.edit);
    }
}