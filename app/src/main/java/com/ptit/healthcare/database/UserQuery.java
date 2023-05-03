package com.ptit.healthcare.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ptit.healthcare.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserQuery {

    private Context context;

    private final DatabaseHelper databaseHelper;

    private static final String TABLE_NAME = "users";

    private static final String ID = "id";

    private static final String NAME = "username";

    private static final String EMAIL = "email";

    private static final String PASSWORD = "pass";

    private static final String PHONE_NUMBER = "phoneNumber";

    private static final String DOB = "dob";

    private static final String ROLES = "roles";

    private static final String HEIGHT = "height";

    private static final String WEIGHT = "weight";


    public UserQuery(Context context) {
        this.context = context;
        this.databaseHelper = new DatabaseHelper(context);
    }

    public List<User> getAll() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String sqlQuery = "SELECT * FROM " + TABLE_NAME;
        List<User> users = new ArrayList<>();

        Cursor cursor = db.rawQuery(sqlQuery, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(0));
                user.setUsername(cursor.getString(1));
                user.setEmail(cursor.getString(2));
                user.setPassword(cursor.getString(3));
                user.setPhoneNumber(cursor.getString(4));
                user.setDob(cursor.getString(5));
                user.setRoles(cursor.getString(6));
                user.setHeight(cursor.getInt(7));
                user.setWeight(cursor.getInt(8));
                users.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return users;
    }

    public List<User> findAllByName(String name) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + NAME
                + " LIKE '%" + name + "%'";

        List<User> users = new ArrayList<>();

        Cursor cursor = db.rawQuery(sqlQuery, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(0));
                user.setUsername(cursor.getString(1));
                user.setEmail(cursor.getString(2));
                user.setPassword(cursor.getString(3));
                user.setPhoneNumber(cursor.getString(4));
                user.setDob(cursor.getString(5));
                user.setRoles(cursor.getString(6));
                user.setHeight(cursor.getInt(7));
                user.setWeight(cursor.getInt(8));
                users.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return users;
    }

    public User getSingle(int id) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{ID, NAME, EMAIL, PASSWORD, PHONE_NUMBER,
                        DOB, ROLES, HEIGHT, WEIGHT},
                ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        User user = new User(cursor.getInt(0), cursor.getString(1),
                cursor.getString(2), cursor.getString(3),
                cursor.getString(4), cursor.getString(5),
                cursor.getString(6), cursor.getInt(7), cursor.getInt(8));

        cursor.close();
        db.close();

        return user;
    }

    public void add(User user) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        System.out.println(user);
        values.put(NAME, user.getUsername());
        values.put(EMAIL, user.getEmail());
        values.put(PASSWORD, user.getPassword());
        values.put(PHONE_NUMBER, user.getPhoneNumber());
        values.put(DOB, user.getDob());
        values.put(ROLES, user.getRoles());
        values.put(HEIGHT, user.getHeight());
        values.put(WEIGHT, user.getWeight());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public int update(User user) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME, user.getUsername());
        values.put(EMAIL, user.getEmail());
        values.put(PASSWORD, user.getPassword());
        values.put(PHONE_NUMBER, user.getPhoneNumber());
        values.put(DOB, user.getDob());
        values.put(ROLES, user.getRoles());
        values.put(HEIGHT, user.getHeight());
        values.put(WEIGHT, user.getWeight());

        return db.update(TABLE_NAME, values, ID + "=?",
                new String[] {String.valueOf(user.getId())});
    }

    public int delete(int id) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        return db.delete(TABLE_NAME, ID + "=?", new String[] {String.valueOf(id)});
    }
    public boolean insertData(String username, String phoneNumber, String pass){
        SQLiteDatabase MyDB = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("phoneNumber", phoneNumber);
        contentValues.put("pass", pass);
        contentValues.put("roles", "ADMIN");
        long result = MyDB.insert("users", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }
    public boolean checkphonenumber(String phoneNumber){
        SQLiteDatabase MyDB = databaseHelper.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where phoneNumber = ?", new String[]{phoneNumber});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public boolean checkphonenumberpassword(String phoneNumber, String pass){
        SQLiteDatabase MyDB = databaseHelper.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where phoneNumber=? and pass = ?", new String[]{phoneNumber, pass});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public User getUserByPhone(String phone) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{ID, NAME, EMAIL, PASSWORD, PHONE_NUMBER,
                        DOB, ROLES, HEIGHT, WEIGHT},
                PHONE_NUMBER + "=?",
                new String[]{String.valueOf(phone)},
                null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        User user = new User(cursor.getInt(0), cursor.getString(1),
                cursor.getString(2), cursor.getString(3),
                cursor.getString(4), cursor.getString(5),
                cursor.getString(6), cursor.getInt(7), cursor.getInt(8));

        cursor.close();
        db.close();

        return user;
    }

}
