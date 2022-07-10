package com.example.sakshyamaryal_21422013;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseHelperClass extends SQLiteOpenHelper {
    public static final String DATABASENAME="post.db";
    public static final String TABLE_NAME="posts";
    public static final String TABLE_one="users";
    public static final String USERNAME="username";
    public static final String PassColumn="password";
    public static final String lastNAME="lname";
    public static final String EMAIL="email";
    public static final String DATEUPDATE = "dateUpdated";

    String query;
    String tableOne;
    // constructor
    public DatabaseHelperClass(Context context) {
        super(context, DATABASENAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDatabase) {
        // create table
        tableOne = "create table " + TABLE_one + "(username text primary key, password text, lname text,email text unique,dateRegistered text,dateUpdated text)";
        query = "create table " + TABLE_NAME + "(id integer primary key,post text, username text,date text)";
        myDatabase.execSQL(query);
        myDatabase.execSQL(tableOne);

    }

    @Override
    public void onUpgrade(SQLiteDatabase myDatabase, int oldVersion, int newVersion) {
//        myDatabase.execSQL("drop Table if exists users");
//        query = " drop table if exists " + TABLE_NAME + "";
//        myDatabase.execSQL(query);
//        onCreate(myDatabase);
    }


    // function for registration page to insert user information
    public Boolean insertData(String username, String password, String lname, String email, String dateRegistered,String dateUpdated){
        SQLiteDatabase myDatabase = this.getWritableDatabase(); // return the writable database
        ContentValues valueContent= new ContentValues();  //content values for overloading put method
        valueContent.put("username", username);
        valueContent.put("password", password);
        valueContent.put("lname",lname);
        valueContent.put("email",email);
        valueContent.put("dateRegistered",dateRegistered);
        valueContent.put("dateUpdated",dateUpdated);

        long result = myDatabase.insert("users", null, valueContent);
        if(result==-1)
            return false;
        else
            return true;
    }


    public UserDetails getUserProfileDetails(String userdetailname){
        // method which pass user details from the user table
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String dbquery = "SELECT * FROM " + TABLE_one;
        Cursor fetch = sqLiteDatabase.rawQuery(dbquery,null);

        if (fetch.moveToFirst()){
            do {

                String fname= fetch.getString(0);
                String password = fetch.getString(1);
                String lname = fetch.getString(2);
                String email = fetch.getString(3);
                String dateUpdated = fetch.getString(4);
                String dateRegistered = fetch.getString(5);


                UserDetails userDetails = new UserDetails(fname,lname,password,email, dateUpdated, dateRegistered);

                if (userdetailname.equals(email)){
//                    findValue.add(userDetails);
                    return userDetails;
                }
            }while (fetch.moveToNext());
        }else {

        }
        UserDetails userDetails1 = new UserDetails(null,null,null,null, null,null);
        return userDetails1;
    }



//get the registered information and chek in login page
    public Boolean checkUserLoginDetails(String email, String password){
        SQLiteDatabase myDatabase= this.getWritableDatabase();

        Cursor cursor = myDatabase.rawQuery("Select * from users where email = ? and password = ? ", new String[] {email,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkUserdetails(String username, String password, String lname, String email){
        SQLiteDatabase myDatabase= this.getWritableDatabase();
        Cursor cursor = myDatabase.rawQuery("Select * from users where username = ? and password = ? and lname = ? and email = ? ", new String[] {username,password,lname,email});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

// update database method to update the userprofile and get user name, password ,etc
    public boolean update(UserDetails userDetails,String userDetailName){
        boolean result = true;
        try {
            String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
            SQLiteDatabase myDatabase = this.getWritableDatabase();
            ContentValues valueContent = new ContentValues();
            valueContent.put( USERNAME, userDetails.getFname());
            valueContent.put(PassColumn,userDetails.getPassword());
            valueContent.put(EMAIL,userDetails.getEmail());
            valueContent.put(lastNAME,userDetails.getLname());
            valueContent.put(DATEUPDATE,date);
            long res =  myDatabase.update(TABLE_one,valueContent, USERNAME + " = ? ", new String[]{userDetailName});
        }
        catch (Exception e){
            result = false;
        }
        return result;
    }


}

