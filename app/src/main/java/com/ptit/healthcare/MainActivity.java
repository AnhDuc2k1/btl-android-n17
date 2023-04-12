package com.ptit.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.ptit.healthcare.database.DatabaseHelper;
import com.ptit.healthcare.entities.Labtest;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button quanLyBacSi;
    Button quanLyLichKham;
    Button quanLyNguoiDung;
    Button quanLyGoiKham;
    Button xacNhanLichKham;
    ImageView avatarAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}