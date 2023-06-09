package com.ptit.healthcare.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ptit.healthcare.entities.Labtest;

import java.util.ArrayList;
import java.util.List;

public class LabtestQuery {

    private Context context;

    private final DatabaseHelper databaseHelper;

    private static final String ID = "id";

    private static final String NAME = "name_labtest";

    private static final String PRICE = "price";

    private static final String DESCRIPTION = "description";

    private static final String DEPARTMENT_ID = "departmentID";

    private static final String TABLE_NAME = "labtest";

    public LabtestQuery(Context context) {
        this.context = context;
        this.databaseHelper = new DatabaseHelper(context);
    }

    public List<Labtest> getAll() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String sqlQuery = "SELECT * FROM " + TABLE_NAME;
        List<Labtest> labtests = new ArrayList<>();

        Cursor cursor = db.rawQuery(sqlQuery, null);
        if(cursor.moveToFirst()) {
            do {
                Labtest labtest = new Labtest();
                labtest.setId(cursor.getInt(0));
                labtest.setName(cursor.getString(1));
                labtest.setPrice(cursor.getInt(2));
                labtest.setDescription(cursor.getString(3));
                labtest.setDepartmentId(cursor.getInt(4));
                labtests.add(labtest);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return labtests;
    }

    public List<Labtest> getAllLabtestByName(String name) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + NAME
                + " LIKE '%" + name + "%'";
        List<Labtest> labtestList = new ArrayList<>();

        Cursor cursor = db.rawQuery(sqlQuery, null);

        if(cursor.moveToFirst()) {
            do {
                Labtest labtest = new Labtest();
                labtest.setId(cursor.getInt(0));
                labtest.setName(cursor.getString(1));
                labtest.setPrice(cursor.getInt(2));
                labtest.setDescription(cursor.getString(3));
                labtest.setDepartmentId(cursor.getInt(4));
                labtestList.add(labtest);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return labtestList;
    }

    public Labtest getSingle(int id) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[] {ID, NAME, PRICE, DESCRIPTION, DEPARTMENT_ID},
                ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if(cursor != null) {
            cursor.moveToFirst();
        }

        Labtest labtest = new Labtest(cursor.getInt(0), cursor.getString(1),
                cursor.getInt(2), cursor.getString(3), cursor.getInt(4));
        cursor.close();
        db.close();
        return labtest;
    }

    public void add(Labtest labtest) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, labtest.getName());
        values.put(PRICE, labtest.getPrice());
        values.put(DESCRIPTION, labtest.getDescription());
        values.put(DEPARTMENT_ID, labtest.getDepartmentId());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public int update(Labtest labtest) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, labtest.getName());
        values.put(PRICE, labtest.getPrice());
        values.put(DESCRIPTION, labtest.getDescription());
        values.put(DEPARTMENT_ID, labtest.getDepartmentId());

        return db.update(TABLE_NAME, values, ID + "=?",
                new String[] {String.valueOf(labtest.getId())});
    }

    public int delete(int id) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        return db.delete(TABLE_NAME, ID + "=?", new String[] {String.valueOf(id)});
    }
}
