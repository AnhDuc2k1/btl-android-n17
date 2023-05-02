package com.ptit.healthcare.entities;

import java.io.Serializable;

public class Article implements Serializable {

    private int id;
    private String pathImage;

    private String name_article;

    private String description;

    private String url;

    public Article() {

    }

    public Article(int id, String pathImage, String name_article, String description, String url) {
        this.id = id;
        this.pathImage = pathImage;
        this.name_article = name_article;
        this.description = description;
        this.url = url;
    }

    public Article(String name_article, String description, String pathImage, String url) {
        this.name_article=name_article;
        this.description=description;
        this.pathImage=pathImage;
        this.url = url;
    }

    public String getPathImage() {
        return pathImage;
    }

    public  void  setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public String getName() {
        return name_article;
    }

    public void setName(String name) {
        this.name_article = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGoToUrl() {
        return url;
    }


    public void setGoToUrl(String goToUrl) {
        this.url = url;
    }


    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", pathImage='" + pathImage + '\'' +
                ", name='" + name_article + '\'' +
                ", description='" + description + '\'' +
                ", goToUrl='" + url + '\'' +
                '}';
    }
}
