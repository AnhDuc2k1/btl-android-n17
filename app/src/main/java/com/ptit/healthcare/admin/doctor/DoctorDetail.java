package com.ptit.healthcare.admin.doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.ptit.healthcare.R;
import com.ptit.healthcare.database.DoctorQuery;
import com.ptit.healthcare.entities.Doctor;

public class DoctorDetail extends AppCompatActivity {

    Button btnCapNhat, btnHuy;
    CheckBox cbCapNhat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);

        Intent intent= DoctorDetail.this.getIntent();

        final EditText editTextIDBS =(EditText)findViewById(R.id.edID);
        final EditText editTextTenBS =(EditText)findViewById(R.id.edTenBS);
        final EditText editTextChuyenKhoa = (EditText)findViewById(R.id.edChuyenKhoa);
        final EditText editTextSDT =(EditText)findViewById(R.id.edSDT);
        final EditText editTextKinhNghiem =(EditText)findViewById(R.id.edKinhNghiem);

        editTextIDBS.setText(intent.getStringExtra("idBS"));
        editTextTenBS.setText(intent.getStringExtra("tenBS"));
        editTextChuyenKhoa.setText(intent.getStringExtra("chuyenKhoa"));
        editTextSDT.setText(intent.getStringExtra("SDT"));
        editTextKinhNghiem.setText(intent.getStringExtra("kinhNghiem"));

        editTextIDBS.setEnabled(false);
        editTextTenBS.setEnabled(false);
        editTextChuyenKhoa.setEnabled(false);
        editTextSDT.setEnabled(false);
        editTextKinhNghiem.setEnabled(false);

        btnHuy=findViewById(R.id.btnHuy);
        btnCapNhat=findViewById(R.id.btnCapNhat);
        cbCapNhat=findViewById(R.id.checkboxCapNhat);

        cbCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextIDBS.setEnabled(false);
                editTextTenBS.setEnabled(true);
                editTextChuyenKhoa.setEnabled(true);
                editTextSDT.setEnabled(true);
                editTextKinhNghiem.setEnabled(true);
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
                doctor.setDepartment(editTextChuyenKhoa.getText().toString());
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
}