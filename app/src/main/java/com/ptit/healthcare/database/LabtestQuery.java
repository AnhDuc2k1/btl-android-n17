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
                labtests.add(labtest);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return labtests;
    }

    public Labtest getSingle(int id) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[] {ID, NAME, PRICE, DESCRIPTION}, ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if(cursor != null) {
            cursor.moveToFirst();
        }

        Labtest labtest = new Labtest(cursor.getInt(0), cursor.getString(1),
                cursor.getInt(2), cursor.getString(3));
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

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public int update(Labtest labtest) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, labtest.getName());
        values.put(PRICE, labtest.getPrice());
        values.put(DESCRIPTION, labtest.getDescription());

        return db.update(TABLE_NAME, values, ID + "=?",
                new String[] {String.valueOf(labtest.getId())});
    }

    public int delete(int id) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        return db.delete(TABLE_NAME, ID + "=?", new String[] {String.valueOf(id)});
    }
}
