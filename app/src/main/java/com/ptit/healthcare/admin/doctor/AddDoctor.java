package com.ptit.healthcare.admin.doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ptit.healthcare.R;
import com.ptit.healthcare.database.DepartmentQuery;
import com.ptit.healthcare.database.DoctorQuery;
import com.ptit.healthcare.entities.Doctor;
import com.ptit.healthcare.utils.ValidateField;

public class AddDoctor extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button btnThemBS;
    EditText editTextTenBS , editTextSDT, editTextKinhNghiem;
    Spinner spinnerChuyenKhoa;
    String chuyenKhoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);
        btnThemBS = findViewById(R.id.btnThemBS);
        editTextTenBS = findViewById(R.id.editTextTenBS);
        spinnerChuyenKhoa = findViewById(R.id.editTextChuyenKhoa);
        editTextSDT = findViewById(R.id.editTextSDT);
        editTextKinhNghiem = findViewById(R.id.editTextKinhNghiem);




        // create spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.department, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerChuyenKhoa.setAdapter(adapter);
        spinnerChuyenKhoa.setOnItemSelectedListener(this);

        btnThemBS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoctorQuery doctorQuery = new DoctorQuery(getBaseContext());
                DepartmentQuery departmentQuery = new DepartmentQuery(getBaseContext());

                if (editTextTenBS.getText().toString().isEmpty()) {
                    editTextTenBS.setError("Vui Lòng điền tên");
                    return;
                }

                if (editTextSDT.getText().toString().isEmpty() || !ValidateField.validatePhoneNumber(editTextSDT.getText().toString())) {
                    editTextSDT.setError("Vui lòng điền SDT");
                    return;
                }


                if (editTextKinhNghiem.getText().toString().isEmpty()) {
                    editTextKinhNghiem.setError("Vui lòng điền số năm kinh nghiệm");
                    return;
                }

                String name = editTextTenBS.getText().toString();
                String phoneNumber = editTextSDT.getText().toString();
                int experience = Integer.parseInt(editTextKinhNghiem.getText().toString());
                int departmentId = departmentQuery.getDepartmentByName(chuyenKhoa).getId();





                Doctor newDoctor = new Doctor(name, phoneNumber, experience, departmentId);

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
        editTextSDT.setText("");
        editTextKinhNghiem.setText("");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        chuyenKhoa = text;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}