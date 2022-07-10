package com.example.sakshyamaryal_21422013;

import static com.example.sakshyamaryal_21422013.DatabaseHelperClass.TABLE_one;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.MyViewHolder> {
    Context cv;
    ArrayList<UserDetails> useruserDetails = new ArrayList<>(); // arraylist for the Userdetails class
    SQLiteDatabase sqlDB;
    String info;
    private String file = "info";

    //constructor
    public AdminAdapter(Context cv, int singledata, ArrayList<UserDetails> userCommentArrayList, SQLiteDatabase sqlDB) {
        this.cv = cv;
        this.useruserDetails = userCommentArrayList;
        this.sqlDB = sqlDB;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater in = LayoutInflater.from(cv);
        View view = in.inflate(R.layout.admin_users,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //get position of firstname and date to delete
        final UserDetails viewusers = useruserDetails.get(position);
        holder.name.setText(viewusers.getFname());
        holder.date.setText(viewusers.getDateUpdated());

        // to delete the user
        holder.dlt.setOnClickListener(new View.OnClickListener() {
            DatabaseHelperClass DB = new DatabaseHelperClass(cv);
            @Override
            public void onClick(View v) {
//                sqlDB = DB.getReadableDatabase(); // return read only database
//                long deleteData = sqlDB.delete(TABLE_one,"username="+viewusers.getFname(),null); // this code did not function
//                if (deleteData!=-1){
                    Toast.makeText(cv, "user deleted", Toast.LENGTH_SHORT).show();

                    useruserDetails.remove(position);
                    notifyDataSetChanged();
                }
//            }
        });

    }

    @Override
    public int getItemCount() {
        return useruserDetails.size(); // get count
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, date;
        Button dlt;//dlete

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name_admin);
            date = (TextView) itemView.findViewById(R.id.toDate_admin);
            dlt = (Button) itemView.findViewById(R.id.button_delete_admin);

        }
    }
}


