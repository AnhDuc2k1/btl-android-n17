package com.ptit.healthcare.entities;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Doctor implements Serializable {

    private int id;
    private String name;
    private String department;
    private String phoneNumber;
    private int experience;

    public Doctor() {
    }

    public Doctor(String name, String department, String phoneNumber, int experience) {
        this.name = name;
        this.department = department;
        this.phoneNumber = phoneNumber;
        this.experience = experience;
    }

    public Doctor(int id, String name, String department, String phoneNumber, int experience) {
        this.id = id;
        this.name = name;
        this.department = department;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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
        return "Name:" +getName();
    }
}
