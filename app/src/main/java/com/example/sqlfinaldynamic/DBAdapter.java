package com.example.sqlfinaldynamic;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public abstract class DBAdapter {

    public static final String DATABASE_NAME = "CovidApp.db";
    public static final int DATABASE_VERSION = 2;

    public static Context context;
    public static DataBaseHelper dataBaseHelper;
    public static SQLiteDatabase db;


    public DBAdapter(Context context)
    {
        this.context = context;
        this.dataBaseHelper = new DataBaseHelper(this.context);
    }

    private static class DataBaseHelper extends SQLiteOpenHelper {
        private static final String TAG = DBAdapter.class.getName();


        DataBaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            Log.e(TAG, "OnCreate: Creating database tables");

            /*  This portion creates all tables */
            //Example: db.execSQL(DBSignature.CREATE_TABLE);
            db.execSQL(DBUser.CREATE_TABLE);
            //cdb.execSQL();
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            //db.execSQL("DROP TABLE IF EXISTS" + TABLE SIG);
            onCreate(db);
        }
    }

    public DBAdapter open() throws SQLException
    {
        this.db = this.dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        this.dataBaseHelper.close();
    }


}
