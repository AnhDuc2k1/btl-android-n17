package com.ptit.healthcare.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ptit.healthcare.entities.ExaminationSchedule;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.ExemptionMechanism;

public class ExaminationScheduleQuery {
    private Context context;
    private final DatabaseHelper databaseHelper;
    private static final String TABLE_NAME = "examination_schedule";
    private static final String ID = "id";
    private static final String EXAMINATION_TIME = "examinationTime";
    private static final String EXAMINATION_DATE = "examinationDate";
    private static final String STATUS = "status";
    private static final String PRICE = "price";
    private static final String USER_ID = "userID";
    private static final String LABTEST_ID = "labtestID";
    private static final String DOCTOR_ID = "doctorID";

    public ExaminationScheduleQuery(Context context) {
        this.context = context;
        this.databaseHelper = new DatabaseHelper(context);
    }

    public List<ExaminationSchedule> getAllByStatus() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE status =" + "'Đang xử lý...'";
        List<ExaminationSchedule> examinationScheduleList = new ArrayList<>();

        Cursor cursor = db.rawQuery(sqlQuery, null);
        if (cursor.moveToFirst()) {
            do {
                ExaminationSchedule examinationSchedule = new ExaminationSchedule();
                examinationSchedule.setId(cursor.getInt(0));
                examinationSchedule.setExaminationTime(cursor.getString(1));
                examinationSchedule.setExaminationDate(cursor.getString(2));
                examinationSchedule.setStatus(cursor.getString(3));
                examinationSchedule.setPrice(cursor.getInt(4));
                examinationSchedule.setUserId(cursor.getInt(5));
                examinationSchedule.setLabtestId(cursor.getInt(6));
                examinationSchedule.setDoctorId(cursor.getInt(7));

                examinationScheduleList.add(examinationSchedule);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return examinationScheduleList;
    }

    public List<ExaminationSchedule> getAllByUserId(int userId) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE userID =" + userId;
        List<ExaminationSchedule> examinationScheduleList = new ArrayList<>();

        Cursor cursor = db.rawQuery(sqlQuery, null);
        if (cursor.moveToFirst()) {
            do {
                ExaminationSchedule examinationSchedule = new ExaminationSchedule();
                examinationSchedule.setId(cursor.getInt(0));
                examinationSchedule.setExaminationTime(cursor.getString(1));
                examinationSchedule.setExaminationDate(cursor.getString(2));
                examinationSchedule.setStatus(cursor.getString(3));
                examinationSchedule.setPrice(cursor.getInt(4));
                examinationSchedule.setUserId(cursor.getInt(5));
                examinationSchedule.setLabtestId(cursor.getInt(6));
                examinationSchedule.setDoctorId(cursor.getInt(7));

                examinationScheduleList.add(examinationSchedule);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return examinationScheduleList;
    }
    public boolean getScheduleByExamTime(String time, String date){
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + EXAMINATION_TIME +" = '" + time
                +"' AND " + EXAMINATION_DATE + " = '" + date + "'";

        Cursor cursor = db.rawQuery(sqlQuery, null);
        if (cursor != null) {
            return false;
        }
        return true;
    }

    public ExaminationSchedule getSingle(int id) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{ID, EXAMINATION_TIME, EXAMINATION_DATE,
                        STATUS, PRICE, USER_ID, LABTEST_ID, DOCTOR_ID}, ID + "=?",
                new String[]{String.valueOf(id)},null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        ExaminationSchedule schedule = new ExaminationSchedule(cursor.getInt(0),
                cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getInt(4),
                cursor.getInt(5), cursor.getInt(6),cursor.getInt(7));

        cursor.close();
        db.close();

        return schedule;
    }

    public void add(ExaminationSchedule examinationSchedule) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(EXAMINATION_TIME, examinationSchedule.getExaminationTime());
        values.put(EXAMINATION_DATE, examinationSchedule.getExaminationDate());
        values.put(STATUS, examinationSchedule.getStatus());
        values.put(PRICE, examinationSchedule.getPrice());
        values.put(USER_ID, examinationSchedule.getUserId());
        values.put(LABTEST_ID, examinationSchedule.getLabtestId());
        values.put(DOCTOR_ID, examinationSchedule.getDoctorId());


        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public int update(int id, String status) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(STATUS, status);

        return db.update(TABLE_NAME, values, ID + "=?",
                new String[] {String.valueOf(id)});
    }
}
