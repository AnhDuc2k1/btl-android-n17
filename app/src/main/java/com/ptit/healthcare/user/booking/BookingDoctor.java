package com.ptit.healthcare.user.booking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.ptit.healthcare.R;
import com.ptit.healthcare.adapter.ListDoctorAdapter;
import com.ptit.healthcare.database.DepartmentQuery;
import com.ptit.healthcare.database.DoctorQuery;
import com.ptit.healthcare.entities.Department;
import com.ptit.healthcare.entities.Doctor;
import com.ptit.healthcare.entities.Item;
import com.ptit.healthcare.entities.ExaminationSchedule;

import java.util.List;

public class BookingDoctor extends AppCompatActivity {
    EditText edTKBacSi;
    Button btnTimKiem;
    ListView dsBacSi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_doctor);

        edTKBacSi = findViewById(R.id.userEditTextTimKiemBS);
        btnTimKiem = findViewById(R.id.userBtnTKBacSi);
        dsBacSi = findViewById(R.id.userDanhSachBacSi);

        Intent intent = BookingDoctor.this.getIntent();
        int departmentId = Integer.valueOf(intent.getStringExtra("departmentId"));

        loadListBacSi(departmentId);

        btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edTKBacSi.getText().toString();
                loadListBSFindByName(name, departmentId);
            }
        });

        edTKBacSi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loadListBacSi(departmentId);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    protected void loadListBacSi(int departmentId) {

        DoctorQuery doctorQuery = new DoctorQuery(getBaseContext());
        List<Doctor> doctorList = doctorQuery.getDoctorsJoinLabtestsByDepartmentID(departmentId);

        ListDoctorAdapter listDoctorAdapter = new ListDoctorAdapter(this, doctorList);

        dsBacSi.setAdapter(listDoctorAdapter);
        dsBacSi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                doOpenChildActivity(doctorList.get(position));
            }
        });
    }

    public void loadListBSFindByName(String name, int departmentId) {;
        DoctorQuery doctorQuery = new DoctorQuery(getBaseContext());
        final List<Doctor> doctorList = doctorQuery.getDoctorsJoinLabtestsByDepartmentIDFindByDoctorName(name, departmentId);

        ListDoctorAdapter adapter = new ListDoctorAdapter(BookingDoctor.this, doctorList);
        dsBacSi.setAdapter(adapter);
        dsBacSi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                doOpenChildActivity(doctorList.get(i));
            }
        });
    }

    private void doOpenChildActivity(Doctor doctor) {
        Intent intent= BookingDoctor.this.getIntent();

        ExaminationSchedule schedule = (ExaminationSchedule)intent.getSerializableExtra("schedule");

        DepartmentQuery departmentQuery = new DepartmentQuery(getBaseContext());
        Department department = departmentQuery.getSingle(doctor.getDepartmentId());

        Intent doctorInfoIntent = new Intent(this, DoctorInfo.class);

        doctorInfoIntent.putExtra("schedule", schedule);
        doctorInfoIntent.putExtra("userId", intent.getStringExtra("userId"));
        doctorInfoIntent.putExtra("username", intent.getStringExtra("username"));
        doctorInfoIntent.putExtra("doctorId", String.valueOf(doctor.getId()));
        doctorInfoIntent.putExtra("doctorName", doctor.getName());
        doctorInfoIntent.putExtra("department", department.getName());
        doctorInfoIntent.putExtra("phoneNumber", doctor.getPhoneNumber());
        doctorInfoIntent.putExtra("experience", Integer.toString(doctor.getExperience()));

        startActivity(doctorInfoIntent);
    }
}