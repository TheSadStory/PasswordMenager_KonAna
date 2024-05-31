package com.example.passwordmenager_konana;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class databaseHelper extends SQLiteOpenHelper {


    public databaseHelper(@Nullable Context context) {
        super(context, "users.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the users table
        String createTable = "CREATE TABLE users (ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, PASSWORD TEXT)";
        // Create the user_data'table
        String createUserDataTable = "CREATE TABLE user_data (data_id INTEGER PRIMARY KEY AUTOINCREMENT , PlatformName TEXT, Username TEXT, Password TEXT, USERID INTEGER, FOREIGN KEY(USERID) REFERENCES users(USERID)) ";
        db.execSQL(createTable);
        db.execSQL(createUserDataTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //SENDS DATA IN TABLE
    public Boolean sendToDatabase(UserTable usertable) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("USERNAME", usertable.getUsername());
        cv.put("PASSWORD", usertable.getPassword());

        String hashedPassword = PasswordHasher.hash(usertable.getPassword());
        cv.put("PASSWORD", hashedPassword);

        long insert = db.insert("users", null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    //CHECKS IF SAME DATA ALREADY EXSIST
    public boolean checkUserExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM users WHERE USERNAME = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username});
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }

    //CHECKS USERNAME AND PASSWORD ARE CORRECT
    public boolean loginUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM users WHERE USERNAME = ? AND PASSWORD = ?"; //  idk what ? does exsacly i will have too google it better
        Cursor cursor = db.rawQuery(query, new String[]{username, password});
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }

    //SEND DATA TOO DATA_TABLE FOR USER
    public Boolean sendToDatabase2(UserDataTable userData) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("PlatformName", userData.getPlatformName());
        cv.put("Username", userData.getUsername());
        cv.put("Password", userData.getPassword());
        cv.put("userID", userData.getUserID());
        long insert = db.insert("user_data", null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    //CHECKS USER ID AND GIVES IT OUT
    public int getUserId(String username) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT ID FROM users WHERE USERNAME=?";
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{username});
        int id = -1;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                id = cursor.getInt(0);
            }
            cursor.close();
        }
        sqLiteDatabase.close();
        return id;
    }

    public Cursor getData(int id){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT data_id as _id, PlatformName, Username, Password, USERID FROM user_data WHERE USERID = '" + id + "'" , null);
    }
}
