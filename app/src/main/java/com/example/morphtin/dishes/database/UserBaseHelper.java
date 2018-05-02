package com.example.morphtin.dishes.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.morphtin.dishes.bean.User;

import static com.example.morphtin.dishes.database.UserDbSchema.UserTable.TABLENAME;


public class UserBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "UserBase.db";

    public UserBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL("create table " + TABLENAME + "(" +
                " _id integer primary key autoincrement, " +
                UserDbSchema.UserTable.Cols.DESCRIPTION + ", " +
                UserDbSchema.UserTable.Cols.IMAGE + ", " +
                UserDbSchema.UserTable.Cols.AGE + ", " +
                UserDbSchema.UserTable.Cols.NAME +
                ")"
        );

        User user = new User("描述一下自己","昵称","18","null");
        ContentValues values = new ContentValues();
        values.put(UserDbSchema.UserTable.Cols.DESCRIPTION, user.getDescription());
        values.put(UserDbSchema.UserTable.Cols.IMAGE, user.getImage());
        values.put(UserDbSchema.UserTable.Cols.AGE, user.getAge());
        values.put(UserDbSchema.UserTable.Cols.NAME, user.getName());
        db.insert(TABLENAME, null, values);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}