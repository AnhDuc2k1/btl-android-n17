package com.ptit.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.ptit.healthcare.admin.user.UserManagement;
import com.ptit.healthcare.database.UserQuery;
import com.ptit.healthcare.entities.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnQuanLyBacSi;

    Button btnQuanLyNguoiDung;

    Button btnQuanLyGoiKham;

    Button btnXacNhanLichKham;

    ImageView avatarAdmin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnQuanLyBacSi = findViewById(R.id.quanLyBacSi);
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

    }


}