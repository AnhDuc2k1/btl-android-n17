package com.ptit.healthcare.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "health_care";

    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE article (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "pathImage TEXT, " +
                "name_article TEXT, " +
                "description TEXT, " +
                "url TEXT)";
        db.execSQL(sqlQuery);

        sqlQuery = "CREATE TABLE users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT, " +
                "email TEXT, " +
                "pass TEXT, " +
                "phoneNumber TEXT, " +
                "dob TEXT, " +
                "roles TEXT, " +
                "height INTEGER, " +
                "weight INTEGER)";
        db.execSQL(sqlQuery);

        sqlQuery = "CREATE TABLE labtest (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name_labtest TEXT, " +
                "price INTEGER, " +
                "description TEXT, "+
                "departmentID INTEGER, " +
                "FOREIGN KEY(departmentID) REFERENCES department(id))";
        db.execSQL(sqlQuery);

        sqlQuery = "CREATE TABLE doctor (" +
                "id INTEGER PRIMARY KEY, " +
                "doctor_name TEXT, " +
                "phoneNumber TEXT, " +
                "experience INTEGER, "+
                "departmentID INTEGER, " +
                "FOREIGN KEY(departmentID) REFERENCES department(id))";
        db.execSQL(sqlQuery);

        sqlQuery = "CREATE TABLE department (" +
                "id INTEGER PRIMARY KEY, " +
                "name TEXT)";
        db.execSQL(sqlQuery);

        sqlQuery = "CREATE TABLE examination_schedule (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "examinationTime TEXT, " +
                "examinationDate TEXT, " +
                "status TEXT, " +
                "userID INTEGER, " +
                "labtestID INTEGER, " +
                "doctorID INTEGER, " +
                "FOREIGN KEY(userID) REFERENCES users(id)," +
                "FOREIGN KEY(labtestID) REFERENCES labtest(id), " +
                "FOREIGN KEY(doctorID) REFERENCES doctor(id))";
        db.execSQL(sqlQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS article");
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS labtest");
        db.execSQL("DROP TABLE IF EXISTS doctor");
        db.execSQL("DROP TABLE IF EXISTS orders");
        db.execSQL("DROP TABLE IF EXISTS item");
        onCreate(db);
    }




}