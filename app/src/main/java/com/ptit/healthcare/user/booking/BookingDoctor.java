package com.ptit.healthcare.user.booking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.ptit.healthcare.R;
import com.ptit.healthcare.adapter.ListDoctorAdapter;
import com.ptit.healthcare.admin.doctor.DoctorManagement;
import com.ptit.healthcare.database.DoctorQuery;
import com.ptit.healthcare.entities.Doctor;
import com.ptit.healthcare.entities.Item;
import com.ptit.healthcare.entities.Order;

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

        loadListBacSi();

        btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edTKBacSi.getText().toString();
                loadListBSFindByName(name);
            }
        });
    }

    protected void loadListBacSi() {
        DoctorQuery doctorQuery = new DoctorQuery(getBaseContext());
        List<Doctor> doctorList = doctorQuery.getAll();

        ListDoctorAdapter listDoctorAdapter = new ListDoctorAdapter(this, doctorList);

        dsBacSi.setAdapter(listDoctorAdapter);
        dsBacSi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                doOpenChildActivity(doctorList.get(position));
            }
        });
    }

    public void loadListBSFindByName(String name) {
        DoctorQuery doctorQuery = new DoctorQuery(getBaseContext());
        final List<Doctor> doctorList = doctorQuery.findDoctorByName(name);

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

        Order order = (Order)intent.getSerializableExtra("order");
        Item item = (Item)intent.getSerializableExtra("item");

        Intent doctorInfoIntent = new Intent(this, DoctorInfo.class);

        doctorInfoIntent.putExtra("order", order);
        doctorInfoIntent.putExtra("item", item);

        doctorInfoIntent.putExtra("doctorId", String.valueOf(doctor.getId()));
        doctorInfoIntent.putExtra("doctorName", doctor.getName());
        doctorInfoIntent.putExtra("department", doctor.getDepartment());
        doctorInfoIntent.putExtra("phoneNumber", doctor.getPhoneNumber());
        doctorInfoIntent.putExtra("experience", Integer.toString(doctor.getExperience()));

        startActivity(doctorInfoIntent);
    }
}