package com.ptit.healthcare.entities;


import androidx.annotation.NonNull;

import java.io.Serializable;

public class ExaminationSchedule implements Serializable {

    private int id;
    private String examinationTime;
    private String examinationDate;
    private String status;
    private int price;
    private int userId;
    private int labtestId;
    private int doctorId;

    public ExaminationSchedule() {
    }

    public ExaminationSchedule(int id, String examinationTime, String examinationDate, String status,
                               int price, int userId, int labtestId, int doctorId) {
        this.id = id;
        this.examinationTime = examinationTime;
        this.examinationDate = examinationDate;
        this.status = status;
        this.price = price;
        this.userId = userId;
        this.labtestId = labtestId;
        this.doctorId = doctorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExaminationTime() {
        return examinationTime;
    }

    public void setExaminationTime(String examinationTime) {
        this.examinationTime = examinationTime;
    }

    public String getExaminationDate() {
        return examinationDate;
    }

    public void setExaminationDate(String examinationDate) {
        this.examinationDate = examinationDate;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @NonNull
    @Override
    public String toString() {
        return "time: " + examinationTime + " date: " + examinationDate + " status: " + status
                + " userid: " + userId + " doctorid: " + doctorId +" labtestid: "+labtestId;
    }
}
