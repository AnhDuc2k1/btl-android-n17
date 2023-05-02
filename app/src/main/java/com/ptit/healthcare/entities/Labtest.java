package com.ptit.healthcare.entities;

public class Labtest {

    private int id;

    private String name;

    private int price;

    private String description;

    private int departmentId;

    public Labtest() {

    }

    public Labtest(String name, int price, String description, int departmentId) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.departmentId = departmentId;
    }

    public Labtest(int id, String name, int price, String description, int departmentId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.departmentId = departmentId;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "Labtest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
