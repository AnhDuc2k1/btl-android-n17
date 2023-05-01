package com.ptit.healthcare.user.booking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ptit.healthcare.R;
import com.ptit.healthcare.entities.Item;
import com.ptit.healthcare.entities.ExaminationSchedule;
import com.ptit.healthcare.utils.CurrentDateTime;
import com.ptit.healthcare.utils.FormatCurrency;

public class LabtestInfo extends AppCompatActivity {

    Button btnChonGoi, btnQuayLai;
    TextView tvTenGoiKham, tvGiaKham;
    EditText edMoTaGoiKham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labtest_info);

        Intent intent= LabtestInfo.this.getIntent();

        int userId =  Integer.valueOf(intent.getStringExtra("userId"));
        int labtestId = Integer.valueOf(intent.getStringExtra("labtestId"));

        tvTenGoiKham = findViewById(R.id.tvTenGoiKham);
        tvGiaKham = findViewById(R.id.tvGiaKham);
        edMoTaGoiKham = findViewById(R.id.edMoTaGoiKham);
        btnChonGoi = findViewById(R.id.userBtnChonGoi);
        btnQuayLai = findViewById(R.id.userBtnQLLabtest);

        tvTenGoiKham.setText(intent.getStringExtra("labtestName"));
        tvGiaKham.setText(FormatCurrency.formatCurrencyVN(Integer.valueOf(intent.getStringExtra("price"))));
        edMoTaGoiKham.setText(intent.getStringExtra("description"));

        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnChonGoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExaminationSchedule schedule = new ExaminationSchedule();

                schedule.setUserId(userId);
                schedule.setStatus("Đang xử lý...");
                schedule.setLabtestId(labtestId);

                Intent bookingDoctorIntent = new Intent(v.getContext(), BookingDoctor.class);
                bookingDoctorIntent.putExtra("schedule", schedule);
                bookingDoctorIntent.putExtra("userId", String.valueOf(userId));
                bookingDoctorIntent.putExtra("username", intent.getStringExtra("username"));

                startActivity(bookingDoctorIntent);
            }
        });
    }
}