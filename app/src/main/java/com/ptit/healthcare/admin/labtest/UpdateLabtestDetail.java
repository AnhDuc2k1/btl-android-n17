package com.ptit.healthcare.admin.labtest;

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
import com.ptit.healthcare.database.LabtestQuery;
import com.ptit.healthcare.entities.Labtest;

public class UpdateLabtestDetail extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button btnUpdate;
    Button btnCancel;

    EditText editTextUpdateName;
    EditText editTextUpdatePrice;
    EditText editTextUpdateDescription;
    Spinner spinnerLabtestDepartMent;
    String chuyenKhoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_labtest_detail);

        btnUpdate = findViewById(R.id.updateLabtest);
        btnCancel = findViewById(R.id.cancelLabtest);

        editTextUpdateName = findViewById(R.id.updateNameLabtest);
        editTextUpdatePrice = findViewById(R.id.updatePrice);
        editTextUpdateDescription = findViewById(R.id.updateDescription);
        spinnerLabtestDepartMent = findViewById(R.id.updateLabtestDepartMent);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.department, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLabtestDepartMent.setAdapter(adapter);
        spinnerLabtestDepartMent.setOnItemSelectedListener(this);

        Intent intent = UpdateLabtestDetail.this.getIntent();
        int id = Integer.parseInt(intent.getStringExtra("id"));
        editTextUpdateName.setText(intent.getStringExtra("labtestName"));
        editTextUpdatePrice.setText(intent.getStringExtra("price"));
        editTextUpdateDescription.setText(intent.getStringExtra("description"));
        spinnerLabtestDepartMent.setSelection(adapter.getPosition(intent.getStringExtra("department")));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DepartmentQuery departmentQuery = new DepartmentQuery(getBaseContext());

                Labtest labtest = new Labtest();
                labtest.setId(id);
                labtest.setName(editTextUpdateName.getText().toString());
                labtest.setPrice(Integer.parseInt(editTextUpdatePrice.getText().toString()));
                labtest.setDescription(editTextUpdateDescription.getText().toString());
                labtest.setDepartmentId(departmentQuery.getDepartmentByName(chuyenKhoa).getId());

                LabtestQuery db = new LabtestQuery(getBaseContext());

                int statusCode = db.update(labtest);
                if(statusCode > 0) {
                    Toast.makeText(getBaseContext(), "Cập nhật gói khám thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getBaseContext(), "Vui long thử lại", Toast.LENGTH_SHORT).show();
                }

                setResult(RESULT_OK, null);
                finish();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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