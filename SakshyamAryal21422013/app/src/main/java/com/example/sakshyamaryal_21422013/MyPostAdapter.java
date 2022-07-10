package com.example.sakshyamaryal_21422013;

import static com.example.sakshyamaryal_21422013.DatabaseHelperClass.TABLE_NAME;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyPostAdapter extends RecyclerView.Adapter<MyPostAdapter.ModelViewHolder> {
    Context cv;
    ArrayList<CommentClass> userCommentArrayList = new ArrayList<>();
    // for name of user
    SQLiteDatabase sqlDB;

    public MyPostAdapter(Context cv, int singledata, ArrayList<CommentClass> userCommentArrayList,SQLiteDatabase sqlDB) {
        this.cv = cv;
        this.userCommentArrayList = userCommentArrayList;
        this.sqlDB = sqlDB;
    }

    @NonNull
    @Override
    public ModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater in = LayoutInflater.from(cv);
        View view = in.inflate(R.layout.singledata_mypost,null);
        return new MyPostAdapter.ModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelViewHolder holder, @SuppressLint("RecyclerView") int position) {

        final CommentClass viewpost = userCommentArrayList.get(position);
        holder.postText.setText(viewpost.getPost());
        holder.name.setText(viewpost.getName());
        holder.date.setText(viewpost.getDate());


        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle(); // get bundle to pass the value of with overloading put

                bundle.putInt("id",viewpost.getId());
                bundle.putString("post",viewpost.getPost());
                bundle.putString("name",viewpost.getName());

                Intent in = new Intent(cv, PostData.class);
                in.putExtra("userComment",bundle);
                cv.startActivity(in);
            }
        });

        holder.dlt.setOnClickListener(new View.OnClickListener() {
            DatabaseHelperClass DB = new DatabaseHelperClass(cv);
            @Override
            public void onClick(View v) {
                sqlDB = DB.getReadableDatabase();
                //detete post with id because id is unique
                long deleteData = sqlDB.delete(TABLE_NAME,"id="+viewpost.getId(),null);
                if (deleteData!=-1){
                    Toast.makeText(cv, "Post Deleted", Toast.LENGTH_SHORT).show();
                    userCommentArrayList.remove(position); // remove post if condition is true
                    notifyDataSetChanged();
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return userCommentArrayList.size();
    }

    public class ModelViewHolder extends RecyclerView.ViewHolder {
        TextView titleText, postText, name, date;
        Button editBtn, dlt;//dlete
        CardView cardv;

        public ModelViewHolder(@NonNull View itemView) {
            super(itemView);
            postText = (TextView) itemView.findViewById(R.id.postText2);
            name = (TextView) itemView.findViewById(R.id.name2);
            date = (TextView) itemView.findViewById(R.id.toDate2);
            dlt = (Button) itemView.findViewById(R.id.button_delete);
            editBtn = (Button) itemView.findViewById(R.id.button_edit);
        }
    }
}

