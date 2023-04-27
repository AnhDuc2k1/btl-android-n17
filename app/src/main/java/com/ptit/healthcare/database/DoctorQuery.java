package com.ptit.healthcare.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ptit.healthcare.entities.Doctor;

import java.util.ArrayList;
import java.util.List;

public class DoctorQuery {

    private Context context;
    private final DatabaseHelper databaseHelper;
    private static final String TABLE_NAME = "doctor";
    private static final String ID = "id";
    private static final String NAME = "doctor_name";
    private static final String DEPARTMENT = "department";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String EXPERIENCE = "experience";

    public DoctorQuery(Context context) {
        this.context = context;
        this.databaseHelper = new DatabaseHelper(context);
    }

    public List<Doctor> getAll() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String sqlQuery = "SELECT * FROM " + TABLE_NAME;
        List<Doctor> doctors = new ArrayList<>();

        Cursor cursor = db.rawQuery(sqlQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Doctor doctor = new Doctor();
                doctor.setId(cursor.getInt(0));
                doctor.setName(cursor.getString(1));
                doctor.setDepartment(cursor.getString(2));
                doctor.setPhoneNumber(cursor.getString(3));
                doctor.setExperience(cursor.getInt(4));

                doctors.add(doctor);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return doctors;
    }

    public List<Doctor> findDoctorByName(String name) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " +
                NAME + " LIKE '%" + name + "%'";

        List<Doctor> listBS = new ArrayList<>();

        Cursor cursor = db.rawQuery(sqlQuery, null);

        if(cursor.moveToFirst()) {
            do {
                Doctor doctor = new Doctor();
                doctor.setId(cursor.getInt(0));
                doctor.setName(cursor.getString(1));
                doctor.setDepartment(cursor.getString(2));
                doctor.setPhoneNumber(cursor.getString(3));
                doctor.setExperience(cursor.getInt(4));

                listBS.add(doctor);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return listBS;
    }

    public Doctor getSingle(int id) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{ID, NAME, DEPARTMENT, PHONE_NUMBER,
                        EXPERIENCE}, ID + "=?",
                new String[]{String.valueOf(id)},null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Doctor doctor = new Doctor(cursor.getInt(0), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), cursor.getInt(4));

        cursor.close();
        db.close();

        return doctor;
    }

    public void add(Doctor doctor) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME, doctor.getName());
        values.put(DEPARTMENT, doctor.getDepartment());
        values.put(PHONE_NUMBER, doctor.getPhoneNumber());
        values.put(EXPERIENCE, doctor.getExperience());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public int update(Doctor doctor) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME, doctor.getName());
        values.put(DEPARTMENT, doctor.getDepartment());
        values.put(PHONE_NUMBER, doctor.getPhoneNumber());
        values.put(EXPERIENCE, doctor.getExperience());

        return db.update(TABLE_NAME, values, ID + "=?",
                new String[] {String.valueOf(doctor.getId())});
    }

    public int delete(int id) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        return db.delete(TABLE_NAME, ID + "=?", new String[] {String.valueOf(id)});
    }
}