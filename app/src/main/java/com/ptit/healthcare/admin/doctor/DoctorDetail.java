package com.ptit.healthcare.admin.doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ptit.healthcare.R;
import com.ptit.healthcare.database.DoctorQuery;
import com.ptit.healthcare.entities.Doctor;

public class DoctorDetail extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button btnCapNhat, btnHuy;
    CheckBox cbCapNhat;
    Spinner spinnerChuyenKhoa;
    String chuyenKhoa1;

    private boolean state = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);

        Intent intent= DoctorDetail.this.getIntent();

        final EditText editTextIDBS =(EditText)findViewById(R.id.edID);
        final EditText editTextTenBS =(EditText)findViewById(R.id.edUsername3);
        spinnerChuyenKhoa = (Spinner) findViewById(R.id.spinnerChuyenKhoa);
        final EditText editTextSDT =(EditText)findViewById(R.id.edPhoneNum3);
        final EditText editTextKinhNghiem =(EditText)findViewById(R.id.edKinhNghiem);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.department, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerChuyenKhoa.setAdapter(adapter);
        spinnerChuyenKhoa.setOnItemSelectedListener(this);



        editTextIDBS.setText(intent.getStringExtra("idBS"));
        editTextTenBS.setText(intent.getStringExtra("tenBS"));
        spinnerChuyenKhoa.setSelection(adapter.getPosition(intent.getStringExtra("chuyenKhoa")));
        editTextSDT.setText(intent.getStringExtra("SDT"));
        editTextKinhNghiem.setText(intent.getStringExtra("kinhNghiem"));

        editTextIDBS.setEnabled(false);
        editTextTenBS.setEnabled(false);
        spinnerChuyenKhoa.setEnabled(false);
        editTextSDT.setEnabled(false);
        editTextKinhNghiem.setEnabled(false);

        btnHuy=findViewById(R.id.btnHuy);
        btnCapNhat=findViewById(R.id.btnCapNhat);
        cbCapNhat=findViewById(R.id.checkboxCapNhat);

        cbCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!state) {
                    editTextIDBS.setEnabled(false);
                    editTextTenBS.setEnabled(true);
                    spinnerChuyenKhoa.setEnabled(true);
                    editTextSDT.setEnabled(true);
                    editTextKinhNghiem.setEnabled(true);
                    state = true;
                }
                else {
                    editTextIDBS.setEnabled(false);
                    editTextTenBS.setEnabled(false);
                    spinnerChuyenKhoa.setEnabled(false);
                    editTextSDT.setEnabled(false);
                    editTextKinhNghiem.setEnabled(false);
                    state = false;
                }
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Doctor doctor = new Doctor();
                DoctorQuery doctorQuery = new DoctorQuery(getBaseContext());
                doctor.setId(Integer.parseInt(editTextIDBS.getText().toString()));
                doctor.setName(editTextTenBS.getText().toString());
                doctor.setDepartment(chuyenKhoa1);
                doctor.setPhoneNumber(editTextSDT.getText().toString());
                doctor.setExperience(Integer.parseInt(editTextKinhNghiem.getText().toString()));

                int result = doctorQuery.update(doctor);
                if (result > 0)
                {
                    Toast.makeText(getBaseContext(), "Cập nhật thông tin thành công!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getBaseContext(), "Cập nhật thông tin không thành công!", Toast.LENGTH_SHORT).show();
                }

                setResult(RESULT_OK, null);
                finish();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        chuyenKhoa1 = text;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}