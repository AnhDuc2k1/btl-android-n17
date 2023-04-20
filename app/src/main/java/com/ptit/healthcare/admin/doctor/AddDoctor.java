package com.ptit.healthcare.admin.doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ptit.healthcare.R;
import com.ptit.healthcare.database.DoctorQuery;
import com.ptit.healthcare.entities.Doctor;

public class AddDoctor extends AppCompatActivity {

    Button btnThemBS;
    EditText editTextTenBS, editTextPhongBan, editTextSDT, editTextKinhNghiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);
        btnThemBS = findViewById(R.id.btnThemBS);
        editTextTenBS = findViewById(R.id.editTextTenBS);
        editTextPhongBan = findViewById(R.id.editTextChuyenKhoa);
        editTextSDT = findViewById(R.id.editTextSDT);
        editTextKinhNghiem = findViewById(R.id.editTextKinhNghiem);

        btnThemBS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoctorQuery doctorQuery = new DoctorQuery(getBaseContext());
                String name = editTextTenBS.getText().toString();
                String department = editTextPhongBan.getText().toString();
                String phoneNumber = editTextSDT.getText().toString();
                int experience = Integer.parseInt(editTextKinhNghiem.getText().toString());

                Doctor newDoctor = new Doctor(name, department, phoneNumber, experience);

                doctorQuery.add(newDoctor);
                Toast.makeText(getBaseContext(),"Thêm bác sĩ thành công",Toast.LENGTH_SHORT).show();
                reset();
                setResult(RESULT_OK, null);
                finish();
            }
        });
    }

    protected  void reset()
    {
        editTextTenBS.setText("");
        editTextPhongBan.setText("");
        editTextSDT.setText("");
        editTextKinhNghiem.setText("");
    }

}