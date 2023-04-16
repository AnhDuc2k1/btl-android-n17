package com.example.baibao;

public class HealthData {
    private int id;
    private String title;
    private int image;
    private String description;

    public HealthData(int id, String title, int image, String description) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }
}

