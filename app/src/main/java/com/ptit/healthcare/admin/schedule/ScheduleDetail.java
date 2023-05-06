package com.ptit.healthcare.admin.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ptit.healthcare.R;
import com.ptit.healthcare.database.DepartmentQuery;
import com.ptit.healthcare.database.DoctorQuery;
import com.ptit.healthcare.database.ExaminationScheduleQuery;
import com.ptit.healthcare.database.LabtestQuery;
import com.ptit.healthcare.database.UserQuery;
import com.ptit.healthcare.entities.Department;
import com.ptit.healthcare.entities.Doctor;
import com.ptit.healthcare.entities.ExaminationSchedule;
import com.ptit.healthcare.entities.Labtest;
import com.ptit.healthcare.entities.User;

public class ScheduleDetail extends AppCompatActivity {

    TextView textViewPatientName;
    TextView textViewPatientPhone;
    TextView textViewDateTime;
    TextView textViewDoctorName;
    TextView textViewDepartment;
    TextView textViewExperience;
    TextView textViewlabtestName;
    TextView textViewDescriptionLabtest;
    Button btnConfirmSchedule, btnCancelSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_detail);

        textViewPatientName = findViewById(R.id.textViewPatientName);
        textViewPatientPhone = findViewById(R.id.textViewPatientPhone);
        textViewDateTime = findViewById(R.id.textViewDateTime);
        textViewDoctorName = findViewById(R.id.textViewDoctorName);
        textViewDepartment = findViewById(R.id.textViewDepartment);
        textViewExperience = findViewById(R.id.textViewExperience);
        textViewlabtestName = findViewById(R.id.textViewlabtestName);
        textViewDescriptionLabtest = findViewById(R.id.textViewDescriptionLabtest);
        btnConfirmSchedule = findViewById(R.id.confirmSchedule);
        btnCancelSchedule = findViewById(R.id.cancelSchedule);

        Intent intent = ScheduleDetail.this.getIntent();

        int id = Integer.parseInt(intent.getStringExtra("id"));
        int userId = Integer.parseInt(intent.getStringExtra("userId"));
        int labtestId = Integer.parseInt(intent.getStringExtra("labtestId"));
        int doctorId = Integer.parseInt(intent.getStringExtra("doctorId"));
        String time = intent.getStringExtra("time");
        String date = intent.getStringExtra("date");

        UserQuery userQuery = new UserQuery(getBaseContext());
        User user = userQuery.getSingle(userId);

        LabtestQuery labtestQuery = new LabtestQuery(getBaseContext());
        Labtest labtest = labtestQuery.getSingle(labtestId);

        DoctorQuery doctorQuery = new DoctorQuery(getBaseContext());
        Doctor doctor = doctorQuery.getSingle(doctorId);

        DepartmentQuery departmentQuery = new DepartmentQuery(getBaseContext());
        Department department = departmentQuery.getSingle(doctor.getDepartmentId());

        ExaminationScheduleQuery scheduleQuery = new ExaminationScheduleQuery(getBaseContext());
        ExaminationSchedule schedule = scheduleQuery.getSingle(id);

        textViewPatientName.setText("Bệnh nhân: " + user.getUsername());
        textViewPatientPhone.setText("Số điện thoại: " + user.getPhoneNumber());
        textViewDateTime.setText(time + " ngày " + date);
        textViewDoctorName.setText("Tên bác sĩ: " + doctor.getName());
        textViewDepartment.setText("Chuyên khoa: " + department.getName());
        textViewExperience.setText("Số năm kinh nghiệm: " + doctor.getExperience());
        textViewlabtestName.setText(labtest.getName());
        textViewDescriptionLabtest.setText(labtest.getDescription());

        btnConfirmSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scheduleQuery.update(id, "Xác nhận");
                Intent intent1 = new Intent(v.getContext(), ScheduleManagement.class);
                startActivity(intent1);
                Toast.makeText(getBaseContext(),"Xác nhận lịch khám thành công",Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK, null);
                finish();
            }
        });

        btnCancelSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scheduleQuery.update(id, "Hủy");
                Intent intent1 = new Intent(v.getContext(), ScheduleManagement.class);
                startActivity(intent1);
                Toast.makeText(getBaseContext(),"Hủy lịch khám thành công",Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK, null);
                finish();
            }
        });
    }
}