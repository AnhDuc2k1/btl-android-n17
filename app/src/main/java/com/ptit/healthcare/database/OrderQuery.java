package com.ptit.healthcare.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ptit.healthcare.entities.Doctor;
import com.ptit.healthcare.entities.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderQuery {
    private Context context;
    private final DatabaseHelper databaseHelper;
    private static final String TABLE_NAME = "orders";
    private static final String ID = "id";
    private static final String ORDER_DATE = "orderDate";
    private static final String STATUS = "status";
    private static final String USER_ID = "userID";

    public OrderQuery(Context context) {
        this.context = context;
        this.databaseHelper = new DatabaseHelper(context);
    }

    public List<Order> getAll() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE status =" + "'Đang xử lý...'";
        List<Order> orderList = new ArrayList<>();

        Cursor cursor = db.rawQuery(sqlQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Order order = new Order();
                order.setId(cursor.getInt(0));
                order.setOrderDate(cursor.getString(1));
                order.setStatus(cursor.getString(2));
                order.setUserId(cursor.getInt(3));

                orderList.add(order);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return orderList;
    }

    public Order getSingle(int id) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{ID, ORDER_DATE, STATUS, USER_ID}, ID + "=?",
                new String[]{String.valueOf(id)},null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Order order = new Order(cursor.getInt(0), cursor.getString(1),
                cursor.getString(2), cursor.getInt(3));

        cursor.close();
        db.close();

        return order;
    }

    public void insert(Order order) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ORDER_DATE, order.getOrderDate());
        values.put(STATUS, order.getStatus());
        values.put(USER_ID, order.getUserId());


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
