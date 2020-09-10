package com.example.sqlfinaldynamic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import java.util.ArrayList;
import java.util.List;

public class DBUser extends DBAdapter {

    public static final String C_ID = "ID";
    public static final String C_FIRST_NAME = "FirstName";
    public static final String C_LAST_NAME = "LastName";
    public static final String C_EMAIL = "Email";
    public static final String C_PHONE = "PhoneNumber";

    public static final String TABLE_NAME = "Users";
    private static final String TAG = DBUser.class.getName();

    public static final String CREATE_TABLE = "CREATE TABLE "
            + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            C_FIRST_NAME + " TEXT, " +
            C_LAST_NAME + " TEXT, " +
            C_EMAIL + " TEXT, " +
            C_PHONE + " TEXT)";




    public DBUser(Context context)
    {
        super(context);
    }



    public boolean addSingle(User user)
    {
        try {
            open();
        } catch(SQLException e)
        {
            e.printStackTrace();
        }

        ContentValues initValues = new ContentValues();
        boolean result;
        initValues.put(C_FIRST_NAME, user.getFirstName());
        initValues.put(C_LAST_NAME, user.getLastName());
        initValues.put(C_EMAIL, user.getEmail());
        initValues.put(C_PHONE, user.getPhone());

        result = (db.insert(TABLE_NAME, null, initValues) > 0);
        db.close();

        return result;
    }

    public boolean deleteSingle(long id)
    {
        return db.delete(TABLE_NAME, C_ID + "=" + id, null) > 0;
    }

    public User getSingle(long id)
    {
        User user = null;

        try{
            open();
        } catch(Exception e)
        {
            e.printStackTrace();
        }

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + C_ID + " = " + id;
        Cursor cursor = db.rawQuery(query, null);

        user = createUser(cursor);

        if(cursor != null)
        {
            cursor.moveToFirst();

        }

        return user;
    }


    public List<User> getAll()
    {
        Cursor cursor;
        List<User> returnList = new ArrayList<>();

        try {
            open();
        } catch(SQLException e)
        {
            e.printStackTrace();
        }

        cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if(cursor.moveToFirst())
        {
            do{
                User user = createUser(cursor);
                returnList.add(user);

            }while(cursor.moveToNext());
        }

        return returnList;
    }

    private User createUser(Cursor cursor)
    {
        User user = new User();
        try{
            user.setId(cursor.getLong(0));
            user.setFirstName(cursor.getString(1));
            user.setLastName(cursor.getString(2));
            user.setEmail(cursor.getString(3));
            user.setPhone(cursor.getString(4));

        } catch(SQLException e)
        {
            e.printStackTrace();
        }
        return user;
    }
}
