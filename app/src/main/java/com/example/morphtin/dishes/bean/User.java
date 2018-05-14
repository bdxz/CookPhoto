package com.example.morphtin.dishes.bean;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.morphtin.dishes.database.UserBaseHelper;
import com.example.morphtin.dishes.database.UserCursorWrapper;
import com.example.morphtin.dishes.database.UserDbSchema;

import java.util.List;

import static com.example.morphtin.dishes.database.UserDbSchema.UserTable.Cols.AGE;
import static com.example.morphtin.dishes.database.UserDbSchema.UserTable.Cols.DESCRIPTION;
import static com.example.morphtin.dishes.database.UserDbSchema.UserTable.Cols.IMAGE;
import static com.example.morphtin.dishes.database.UserDbSchema.UserTable.Cols.NAME;

/**
 * Created by Morphtin on 2018/4/27.
 */

public class User implements Parcelable {
    private String description;
    private String name;
    private String profileImage;
    private String age;
    private SQLiteDatabase mDatabase;
    private Context mContext;

    private static User sUser;
    private User(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new UserBaseHelper(mContext).getWritableDatabase();

    }

    public static User getUser(Context context){
        if(sUser ==null){
            sUser = new User(context);
        }
        return sUser;
    }

    public User(String description,String name,String age,String profileImage){
        this.name = name;
        this.profileImage = profileImage;
        this.age = age;
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Storing the User data to Parcel object
     **/
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(profileImage);
        dest.writeString(age);
        dest.writeString(description);
    }

    /**
     * Retrieving User data from Parcel object
     * This constructor is invoked by the method createFromParcel(Parcel source) of
     * the object CREATOR
     **/
    private User(Parcel in){
        this.name = in.readString();
        this.profileImage = in.readString();
        this.age = in.readString();
        this.description = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {

        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };


    public String getName(){
        return name;
    }
    public String getImage(){
        return profileImage;
    }
    public String getAge(){
        return age;
    }
    public String getDescription(){
        return description;
    }

    public void setName(String name){
        this.name=name;
    }
    public void setImage(String profileImage){
        this.profileImage=profileImage;
    }
    public void setAge(String age){this.age= age; }
    public void setDescription(String description){
        this.description=description;
    }


    public User getUserInf(String name){
        UserCursorWrapper cursor = queryUsers(
                NAME + " = ?",
                new String[]{name.toString()}
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getUser();
        } finally {
            cursor.close();
        }
    }

    public void deleteUser(User User){
        mDatabase.delete(UserDbSchema.UserTable.TABLENAME, "name = ?", new String[] { User.getName() });

    }

    public void updateUser(String name,User user){
        ContentValues values = getContentValues(user);
        mDatabase.update(UserDbSchema.UserTable.TABLENAME, values,"name = ?", new String[] { name });
    }

//    public List<User> getUsers(){
//        Users.clear();
//        UserCursorWrapper cursor = queryUsers(null, null);
//        try {
//            cursor.moveToFirst();
//            while (!cursor.isAfterLast()) {
//                Users.add(cursor.getUser());
//                cursor.moveToNext();
//            }
//        } finally {
//            cursor.close();
//        }
//
//        return Users;
//    }

    public void addUser(User User){
        //Users.add(User);
        ContentValues values = getContentValues(User);
        mDatabase.insert(UserDbSchema.UserTable.TABLENAME, null, values);
    }

    private static ContentValues getContentValues(User User) {
        ContentValues values = new ContentValues();
        values.put(DESCRIPTION, User.getDescription());
        values.put(IMAGE, User.getImage());
        values.put(AGE, User.getAge());
        values.put(NAME, User.getName());
        return values;
    }
    private UserCursorWrapper queryUsers(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                UserDbSchema.UserTable.TABLENAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );
        return new UserCursorWrapper(cursor);
    }
}
