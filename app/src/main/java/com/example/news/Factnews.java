package com.example.news;

import java.io.Serializable;

public class Factnews implements Serializable {

    private String pathImage;

    private String name;

    private String description;

    private String map;

    public Factnews() {

    }

    public Factnews(String pathImage, String name, String description, String map) {
        this.pathImage = pathImage;
        this.name = name;
        this.description = description;
        this.map = map;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }
}
