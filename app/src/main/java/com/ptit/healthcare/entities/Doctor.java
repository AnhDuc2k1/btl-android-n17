package com.ptit.healthcare.entities;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Doctor implements Serializable {

    private int id;
    private String name;
    private String phoneNumber;
    private int experience;
    private int departmentId;

    public Doctor() {
    }

    public Doctor(String name, String phoneNumber, int experience, int departmentId) {
        this.name = name;
        this.departmentId = departmentId;
        this.phoneNumber = phoneNumber;
        this.experience = experience;
    }

    public Doctor(int id, String name, String phoneNumber, int experience, int departmentId) {
        this.id = id;
        this.name = name;
        this.departmentId = departmentId;
        this.phoneNumber = phoneNumber;
        this.experience = experience;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @NonNull
    @Override
    public String toString() {
        return "Name: " +getName();
    }
}
