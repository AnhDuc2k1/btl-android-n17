package com.example.baibao;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.ParcelableSpan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class Detail extends AppCompatActivity implements Serializable {

    private String pathImage;

    private String tieude;

    private String description;


    public Detail() {

    }

    public Detail(String pathImage, String tieude, String description) {
        this.pathImage = pathImage;
        this.tieude = tieude;
        this.description = description;
    }
    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public String getName() {
        return tieude;
    }

    public void setName(String name) {
        this.tieude = tieude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}