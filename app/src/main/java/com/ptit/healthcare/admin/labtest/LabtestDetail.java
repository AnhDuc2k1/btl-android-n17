package com.ptit.healthcare.admin.labtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ptit.healthcare.R;
import com.ptit.healthcare.database.LabtestQuery;
import com.ptit.healthcare.entities.Labtest;

public class LabtestDetail extends AppCompatActivity {

    EditText editTextLabtestName;
    EditText editTextPrice;
    EditText editTextDescription;

    Button btnAddNewLabtest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labtest_detail);

        btnAddNewLabtest = findViewById(R.id.addNewLabtest);
        editTextLabtestName = findViewById(R.id.labtestName);
        editTextPrice = findViewById(R.id.price);
        editTextDescription = findViewById(R.id.description);

        btnAddNewLabtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LabtestQuery labtestQuery = new LabtestQuery(getBaseContext());
                String labtestName = editTextLabtestName.getText().toString();
                int price = Integer.parseInt(editTextPrice.getText().toString());
                String description = editTextDescription.getText().toString();

                Labtest labtest = new Labtest(labtestName, price, description);
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
}