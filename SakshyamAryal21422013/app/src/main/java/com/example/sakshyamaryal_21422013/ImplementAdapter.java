package com.example.sakshyamaryal_21422013;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ImplementAdapter extends RecyclerView.Adapter<ImplementAdapter.ModelViewHolder> {
    Context cv;
    ArrayList<CommentClass> userCommentArrayList = new ArrayList<>(); // array list for post
    SQLiteDatabase sqlDB;

    // constructor
    public ImplementAdapter(Context cv, int singledata, ArrayList<CommentClass> userCommentArrayList,SQLiteDatabase sqlDB) {
        this.cv = cv;
        this.userCommentArrayList = userCommentArrayList;
        this.sqlDB = sqlDB;
    }



    @NonNull
    @Override
    public ImplementAdapter.ModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater in = LayoutInflater.from(cv);
        View view = in.inflate(R.layout.singledata,null);
        return new ModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImplementAdapter.ModelViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // to set text of the provided text view
        final CommentClass viewpost = userCommentArrayList.get(position);
        holder.postText.setText(viewpost.getPost());
        holder.name.setText(viewpost.getName());
        holder.date.setText(viewpost.getDate());




    }
    @Override
    public int getItemCount() {
        return userCommentArrayList.size();
    }

    public class ModelViewHolder extends RecyclerView.ViewHolder {
        TextView postText,name,date;

        public ModelViewHolder(@NonNull View itemView) {
            super(itemView);
            postText = (TextView)itemView.findViewById(R.id.postText);
            name = (TextView)itemView.findViewById(R.id.name);
            date = (TextView)itemView.findViewById(R.id.toDate);


        }
    }
}

