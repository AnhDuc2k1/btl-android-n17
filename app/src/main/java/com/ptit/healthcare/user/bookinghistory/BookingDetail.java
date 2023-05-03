package com.ptit.healthcare.user.bookinghistory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ptit.healthcare.R;
import com.ptit.healthcare.database.DepartmentQuery;
import com.ptit.healthcare.database.DoctorQuery;
import com.ptit.healthcare.database.ExaminationScheduleQuery;
import com.ptit.healthcare.database.LabtestQuery;
import com.ptit.healthcare.entities.Department;
import com.ptit.healthcare.entities.Doctor;
import com.ptit.healthcare.entities.Labtest;

public class BookingDetail extends AppCompatActivity {

    TextView tvBookingLabtestName, tvBookingExaminationTime, tvBookingExaminationDate,
            tvBookingPrice, tvBookingStatus2, tvBookingLabtestDescription, tvBookingDoctorName,
            tvBookingDoctorDepartment, tvBookingDoctorExperience, tvBookingDoctorPhoneNumber;

    Button btnBookingHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_history_detail);

        tvBookingLabtestName = findViewById(R.id.bookingLabtestName);
        tvBookingExaminationTime = findViewById(R.id.bookingExaminationTime);
        tvBookingExaminationDate = findViewById(R.id.bookingExaminationDate);
        tvBookingPrice = findViewById(R.id.bookingPrice);
        tvBookingStatus2 = findViewById(R.id.bookingStatus2);
        tvBookingLabtestDescription = findViewById(R.id.bookingLabtestDescription);
        tvBookingDoctorName = findViewById(R.id.bookingDoctorName);
        tvBookingDoctorDepartment = findViewById(R.id.bookingDoctorDepartment);
        tvBookingDoctorExperience = findViewById(R.id.bookingDoctorExperience);
        tvBookingDoctorPhoneNumber = findViewById(R.id.bookingDoctorPhoneNumber);

        Intent intent = BookingDetail.this.getIntent();

        int userId = Integer.valueOf(intent.getStringExtra("userId"));
        int labtestId = Integer.valueOf(intent.getStringExtra("labtestId"));
        int doctorId = Integer.valueOf(intent.getStringExtra("doctorId"));
        String examinationTime = intent.getStringExtra("examinationTime");
        String examinationDate = intent.getStringExtra("examinationDate");
        String price = intent.getStringExtra("price");
        String status = intent.getStringExtra("status");

        LabtestQuery labtestQuery = new LabtestQuery(getBaseContext());
        Labtest labtest = labtestQuery.getSingle(labtestId);

        DoctorQuery doctorQuery = new DoctorQuery(getBaseContext());
        Doctor doctor = doctorQuery.getSingle(doctorId);

        DepartmentQuery departmentQuery = new DepartmentQuery(getBaseContext());
        Department department = departmentQuery.getSingle(labtest.getDepartmentId());

        ExaminationScheduleQuery scheduleQuery = new ExaminationScheduleQuery(getBaseContext());

        tvBookingLabtestName.setText(labtest.getName());
        tvBookingExaminationTime.setText(examinationTime);
        tvBookingExaminationDate.setText(examinationDate);
        tvBookingPrice.setText(price);
        tvBookingStatus2.setText(status);
        tvBookingLabtestDescription.setText(labtest.getDescription());
        tvBookingDoctorName.setText(doctor.getName());
        tvBookingDoctorDepartment.setText(department.getName());
        tvBookingDoctorExperience.setText(doctor.getExperience());
        tvBookingDoctorPhoneNumber.setText(doctor.getPhoneNumber());

        if (status.equalsIgnoreCase("Đang xử lý...")){
            btnBookingHistory.setText("Hủy lịch");
            btnBookingHistory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(getApplicationContext(), BookingHistory.class);
                    intent1.putExtra("userId", String.valueOf(userId));
//                    star
                }
            });
        }
        else {
            btnBookingHistory.setText("Trở lại");
            btnBookingHistory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent2 = new Intent(getApplicationContext(), BookingHistory.class);
                    intent2.putExtra("userId", String.valueOf(userId));
                    startActivity(intent2);
                }
            });
        }
    }
}