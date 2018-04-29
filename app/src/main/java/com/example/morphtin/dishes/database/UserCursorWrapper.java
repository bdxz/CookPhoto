package com.example.morphtin.dishes.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.morphtin.dishes.bean.User;


public class UserCursorWrapper extends CursorWrapper {

    public UserCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public User getUser() {
        String description = getString(getColumnIndex(UserDbSchema.UserTable.Cols.DESCRIPTION));
        String image = getString(getColumnIndex(UserDbSchema.UserTable.Cols.IMAGE));
        String age = getString(getColumnIndex(UserDbSchema.UserTable.Cols.AGE));
        String name = getString(getColumnIndex(UserDbSchema.UserTable.Cols.NAME));


        User user = new User(description,name,age,image);

        return user;
    }
}
