package com.ptit.healthcare.admin.labtest;

import androidx.appcompat.app.AppCompatActivity;

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
import com.ptit.healthcare.entities.Department;
import com.ptit.healthcare.entities.Labtest;

public class LabtestDetail extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText editTextLabtestName;
    EditText editTextPrice;
    EditText editTextDescription;
    Button btnAddNewLabtest;
    Spinner spinnerLabtestDepartMent;
    String chuyenKhoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labtest_detail);

        btnAddNewLabtest = findViewById(R.id.addNewLabtest);
        editTextLabtestName = findViewById(R.id.labtestName);
        editTextPrice = findViewById(R.id.price);
        editTextDescription = findViewById(R.id.description);
        spinnerLabtestDepartMent = findViewById(R.id.labtestDepartMent);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.department, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLabtestDepartMent.setAdapter(adapter);
        spinnerLabtestDepartMent.setOnItemSelectedListener(this);

        btnAddNewLabtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LabtestQuery labtestQuery = new LabtestQuery(getBaseContext());
                DepartmentQuery departmentQuery = new DepartmentQuery(getBaseContext());

                String labtestName = editTextLabtestName.getText().toString();
                int price = Integer.parseInt(editTextPrice.getText().toString());
                String description = editTextDescription.getText().toString();
                int departmentId = departmentQuery.getDepartmentByName(chuyenKhoa).getId();

                Labtest labtest = new Labtest(labtestName, price, description, departmentId);
                labtestQuery.add(labtest);
                Toast.makeText(getBaseContext(), "Thêm gói khám thành công", Toast.LENGTH_SHORT).show();
                reset();
                setResult(RESULT_OK, null);
                finish();
            }

        });
    }

    private void reset() {
        editTextPrice.setText("");
        editTextLabtestName.setText("");
        editTextDescription.setText("");
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