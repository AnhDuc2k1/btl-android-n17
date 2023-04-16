package com.example.baibao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "health_db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "health_table";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_DESCRIPTION = "description";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_IMAGE + " INTEGER,"
                + COLUMN_DESCRIPTION + " TEXT"
                + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    // Hàm thêm một bản ghi vào cơ sở dữ liệu
    public void addHealthData(String title, int image, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(COLUMN_TITLE, title);
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_IMAGE, image);
        values.put(COLUMN_DESCRIPTION, description);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    // Hàm lấy dữ liệu từ cơ sở dữ liệu
    public HealthData getHealthData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_ID, COLUMN_TITLE, COLUMN_IMAGE, COLUMN_DESCRIPTION},
                COLUMN_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        @SuppressLint("Range") HealthData healthData = new HealthData(
                cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)),
                cursor.getInt(cursor.getColumnIndex(COLUMN_IMAGE)),
                cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))
        );
        cursor.close();
        db.close();
        return healthData;
    }

//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//
//    }
}

