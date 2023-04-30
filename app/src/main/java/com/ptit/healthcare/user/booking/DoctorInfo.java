package com.ptit.healthcare.user.booking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ptit.healthcare.R;
import com.ptit.healthcare.entities.Item;
import com.ptit.healthcare.entities.Order;
import com.ptit.healthcare.utils.CurrentDateTime;

public class DoctorInfo extends AppCompatActivity {

    TextView tvTenBs, tvChuyenKhoa, tvSDT, tvKinhNghiem;
    Button btnChonBS, btnQuayLai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_info);

        tvTenBs = findViewById(R.id.userTVTenBS);
        tvChuyenKhoa = findViewById(R.id.userTVChuyenKhoa);
        tvSDT = findViewById(R.id.userTVSDT);
        tvKinhNghiem = findViewById(R.id.userTVKinhNghiem);
        btnChonBS = findViewById(R.id.userBtnQLDSBS);
        btnQuayLai = findViewById(R.id.userBtnChonBSi);

        Intent intent = DoctorInfo.this.getIntent();

        tvTenBs.setText(intent.getStringExtra("doctorName"));
        tvChuyenKhoa.setText(intent.getStringExtra("department"));
        tvSDT.setText(intent.getStringExtra("phoneNumber"));
        tvKinhNghiem.setText(intent.getStringExtra("experience"));

        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnChonBS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Order order = (Order)intent.getSerializableExtra("order");
                Item item = (Item)intent.getSerializableExtra("item");

                item.setDoctorId(Integer.valueOf(intent.getStringExtra("doctorId")));

                Intent bookingIntent = new Intent(v.getContext(), Booking.class);
                bookingIntent.putExtra("order", order);
                bookingIntent.putExtra("item", item);

                startActivity(bookingIntent);
            }
        });
    }


}