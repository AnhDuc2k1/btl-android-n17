package com.ptit.healthcare.entities;

import java.io.Serializable;

public class Item implements Serializable {

    private int id;
    private String startDate;
    private int orderId;
    private int labtestId;
    private int doctorId;

    public Item() {
    }

    public Item(int id, String startDate, int orderId, int labtestId, int doctorId) {
        this.id = id;
        this.startDate = startDate;
        this.orderId = orderId;
        this.labtestId = labtestId;
        this.doctorId = doctorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getLabtestId() {
        return labtestId;
    }

    public void setLabtestId(int labtestId) {
        this.labtestId = labtestId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
}