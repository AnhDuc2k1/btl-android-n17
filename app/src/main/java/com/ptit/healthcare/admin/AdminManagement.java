package com.ptit.healthcare.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ptit.healthcare.R;
import com.ptit.healthcare.admin.doctor.DoctorManagement;
import com.ptit.healthcare.admin.labtest.LabtestManagement;
import com.ptit.healthcare.admin.user.UserManagement;

public class AdminManagement extends AppCompatActivity {

    Button btnQuanLyBacSi;

    Button btnQuanLyNguoiDung;

    Button btnQuanLyGoiKham;

    Button btnXacNhanLichKham;

    ImageView avatarAdmin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_management);

        btnQuanLyBacSi = findViewById(R.id.btnQuanLyBacSi);
        btnQuanLyNguoiDung = findViewById(R.id.quanLyNguoiDung);
        btnQuanLyGoiKham = findViewById(R.id.quanLyGoiKham);
        btnXacNhanLichKham = findViewById(R.id.xacNhanLichKham);
        avatarAdmin = findViewById(R.id.imageView);

        btnQuanLyNguoiDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), UserManagement.class);
                startActivity(intent);
            }
        });

        btnQuanLyGoiKham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), LabtestManagement.class);
                startActivity(intent);
            }
        });

        btnQuanLyBacSi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DoctorManagement.class);
                startActivity(intent);
            }
        });

    }


}