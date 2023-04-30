package com.ptit.healthcare.entities;


import java.io.Serializable;

public class Order implements Serializable {

    private int id;
    private String orderDate;
    private String status;
    private int userId;

    public Order() {
    }

    public Order(int id, String orderDate, String status, int userId) {
        this.id = id;
        this.orderDate = orderDate;
        this.status = status;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
