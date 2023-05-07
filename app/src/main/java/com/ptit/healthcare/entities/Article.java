package com.ptit.healthcare.entities;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Article implements Serializable {
    private int id;
    private String pathImage;
    private String name_article;
    private String description;
    private String url;

    public Article(){

    }

    public Article(String pathImage, String name_article, String description, String url){
        this.pathImage=pathImage;
        this.name_article=name_article;
        this.description=description;
        this.url=url;
    }


    public Article(int id, String pathImage, String name_article, String description, String url){
        this.id=id;
        this.pathImage=pathImage;
        this.name_article=name_article;
        this.description=description;
        this.url=url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public String getName_article() {
        return name_article;
    }

    public void setName_article(String name_article) {
        this.name_article = name_article;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @NonNull
    @Override
    public String toString() {
        return "Name: " + getName_article();
    }
}
