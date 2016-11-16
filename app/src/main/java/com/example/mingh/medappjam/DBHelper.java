package com.example.mingh.medappjam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLData;

/**
 * Created by MINGH on 11/14/16.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "AppJam.db";
    public static final int DB_VERSION = 1;
    public static final String TAG = DBHelper.class.getSimpleName();

    public static final String USER_TABLE = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_NAME = "name";

    public static final String CREATE_TABLE_USER = "CREATE TABLE " + USER_TABLE + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_EMAIL + " TEXT,"
            + COLUMN_PASSWORD + " TEXT"
            + COLUMN_NAME + " TEXT);";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        onCreate(db);
    }

    public void addUser(String email, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);

        long id = db.insert(USER_TABLE, null, values);
        Log.d(TAG, "USER INSERTED " + id);
    }

    public boolean getUser(String email, String password)
    {
        String selectQuery = "SELECT * FROM " + USER_TABLE + " WHERE " +
                COLUMN_EMAIL + " = " + "'" + email + "'" + " AND " +
                COLUMN_PASSWORD + " = " + "'" + password + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0)
        {
            return true;
        }

        cursor.close();
        db.close();

        return false;
    }

    public boolean getUser(String email)
    {
        String selectQuery = "SELECT * FROM " + USER_TABLE + " WHERE " +
                COLUMN_EMAIL + " = " + "'" + email + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0)
        {
            return true;
        }

        cursor.close();
        db.close();

        return false;
    }

}
