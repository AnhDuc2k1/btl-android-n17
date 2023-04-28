package com.example.news;

import java.io.Serializable;

public class Factnews implements Serializable {

    private String pathImage;

    private String name;

    private String description;

    private String gotourl;

    public Factnews() {

    }

    public Factnews(String pathImage, String name, String description, String gotourl) {
        this.pathImage = pathImage;
        this.name = name;
        this.description = description;
        this.gotourl = gotourl;
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

    public String getGotourl() {
        return gotourl;
    }

    public void setGotourl(String gotourl) {
        this.gotourl = gotourl;
    }
}
