package com.ptit.healthcare.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ptit.healthcare.entities.Department;
import com.ptit.healthcare.entities.Doctor;

import java.util.ArrayList;
import java.util.List;

public class DepartmentQuery {
    private Context context;
    private final DatabaseHelper databaseHelper;
    private static final String TABLE_NAME = "department";
    private static final String ID = "id";
    private static final String NAME = "name";

    public DepartmentQuery(Context context) {
        this.context = context;
        this.databaseHelper = new DatabaseHelper(context);
    }

    public List<Department> getAll() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String sqlQuery = "SELECT * FROM " + TABLE_NAME;
        List<Department> departments = new ArrayList<>();

        Cursor cursor = db.rawQuery(sqlQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Department department = new Department();
                department.setId(cursor.getInt(0));
                department.setName(cursor.getString(1));

                departments.add(department);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return departments;
    }

    public Department getDepartmentByName(String name) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " +
                NAME + " LIKE '%" + name + "%'";

        Cursor cursor = db.rawQuery(sqlQuery, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Department department = new Department(cursor.getInt(0), cursor.getString(1));

        cursor.close();
        db.close();
        return department;
    }

    public Department getSingle(int id) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{ID, NAME}, ID + "=?",
                new String[]{String.valueOf(id)},null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Department department = new Department(cursor.getInt(0),
                                                cursor.getString(1));

        cursor.close();
        db.close();

        return department;
    }

    public void add(Department department) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME, department.getName());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public int update(Department  department) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME, department.getName());

        return db.update(TABLE_NAME, values, ID + "=?",
                new String[] {String.valueOf(department.getId())});
    }

    public int delete(int id) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        return db.delete(TABLE_NAME, ID + "=?", new String[] {String.valueOf(id)});
    }
}
