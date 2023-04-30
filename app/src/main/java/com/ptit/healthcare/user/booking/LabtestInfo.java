package com.ptit.healthcare.user.booking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ptit.healthcare.R;
import com.ptit.healthcare.admin.doctor.DoctorDetail;
import com.ptit.healthcare.database.DoctorQuery;
import com.ptit.healthcare.database.LabtestQuery;
import com.ptit.healthcare.entities.Doctor;
import com.ptit.healthcare.entities.Item;
import com.ptit.healthcare.entities.Labtest;
import com.ptit.healthcare.entities.Order;
import com.ptit.healthcare.utils.CurrentDateTime;

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
        tvGiaKham.setText(intent.getStringExtra("price")+"VNĐ");
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
                Order newOrder = new Order();
                newOrder.setOrderDate(CurrentDateTime.getCurrentDate());
                newOrder.setUserId(userId);
                newOrder.setStatus("Đang xử lý...");

                Item newItem = new Item();
                newItem.setLabtestId(labtestId);

                Intent bookingDoctorIntent = new Intent(v.getContext(), BookingDoctor.class);
                bookingDoctorIntent.putExtra("order", newOrder);
                bookingDoctorIntent.putExtra("item", newItem);

                startActivity(bookingDoctorIntent);
            }
        });
    }
}