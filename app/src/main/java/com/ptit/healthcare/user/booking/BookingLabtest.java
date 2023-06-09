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
import com.ptit.healthcare.adapter.ListLabtestAdapter;
import com.ptit.healthcare.admin.doctor.DoctorManagement;
import com.ptit.healthcare.admin.labtest.LabtestDetail;
import com.ptit.healthcare.database.DoctorQuery;
import com.ptit.healthcare.database.LabtestQuery;
import com.ptit.healthcare.entities.Doctor;
import com.ptit.healthcare.entities.Labtest;

import java.util.List;

public class BookingLabtest extends AppCompatActivity {

    EditText edTKGoiKham;
    Button btnTimKiem;
    ListView listGoiKham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_labtest);
        edTKGoiKham = findViewById(R.id.editTextTimKiemDvKham);
        btnTimKiem = findViewById(R.id.btnTimKiemDvKham);
        listGoiKham = findViewById(R.id.listGoiKham);

        loadListLabtest();

        btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edTKGoiKham.getText().toString();
                loadListLabtestByName(name);
            }
        });

        edTKGoiKham.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loadListLabtest();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    protected void loadListLabtest() {
        LabtestQuery labtestQuery = new LabtestQuery(getBaseContext());
        List<Labtest> labtestList = labtestQuery.getAll();

        ListLabtestAdapter labtestAdapter = new ListLabtestAdapter(this, labtestList);

        listGoiKham.setAdapter(labtestAdapter);
        listGoiKham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                doOpenChildActivity(labtestList.get(position));
            }
        });
    }

    protected void loadListLabtestByName(String name) {
        LabtestQuery db = new LabtestQuery(getBaseContext());
        List<Labtest> labtestList = db.getAllLabtestByName(name);

        ListLabtestAdapter labtestAdapter = new ListLabtestAdapter(this, labtestList);
        listGoiKham.setAdapter(labtestAdapter);

        listGoiKham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                doOpenChildActivity(labtestList.get(position));
            }
        });
    }

    private void doOpenChildActivity(Labtest labtest) {
        Intent homeIntent = BookingLabtest.this.getIntent();
        int userId =  Integer.valueOf(homeIntent.getStringExtra("userId"));
        String username = homeIntent.getStringExtra("username");

        Intent labtestDetailIntent = new Intent(this, LabtestInfo.class);

        labtestDetailIntent.putExtra("userId", String.valueOf(userId));
        labtestDetailIntent.putExtra("username", username);
        labtestDetailIntent.putExtra("labtestId", String.valueOf(labtest.getId()));
        labtestDetailIntent.putExtra("labtestName", labtest.getName());
        labtestDetailIntent.putExtra("price", String.valueOf(labtest.getPrice()));
        labtestDetailIntent.putExtra("description", String.valueOf(labtest.getDescription()));
        labtestDetailIntent.putExtra("departmentId", String.valueOf(labtest.getDepartmentId()));
        startActivity(labtestDetailIntent);
    }
}