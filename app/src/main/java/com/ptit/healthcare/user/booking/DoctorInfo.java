package com.ptit.healthcare.user.booking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ptit.healthcare.R;
import com.ptit.healthcare.entities.Item;
import com.ptit.healthcare.entities.ExaminationSchedule;

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
        btnChonBS = findViewById(R.id.userBtnChonBSi);
        btnQuayLai = findViewById(R.id.userBtnQLDSBS);

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

                ExaminationSchedule schedule = (ExaminationSchedule)intent.getSerializableExtra("schedule");

                schedule.setDoctorId(Integer.valueOf(intent.getStringExtra("doctorId")));

                Intent bookingIntent = new Intent(v.getContext(), Booking.class);
                bookingIntent.putExtra("schedule", schedule);
                bookingIntent.putExtra("userId", intent.getStringExtra("userId"));
                bookingIntent.putExtra("username", intent.getStringExtra("username"));
                startActivity(bookingIntent);
            }
        });
    }


}