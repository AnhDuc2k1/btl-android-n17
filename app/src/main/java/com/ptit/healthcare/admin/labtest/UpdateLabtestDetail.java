package com.ptit.healthcare.admin.labtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ptit.healthcare.R;
import com.ptit.healthcare.database.LabtestQuery;
import com.ptit.healthcare.entities.Labtest;

public class UpdateLabtestDetail extends AppCompatActivity {

    Button btnUpdate;
    Button btnCancel;

    EditText editTextUpdateName;
    EditText editTextUpdatePrice;
    EditText editTextUpdateDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_labtest_detail);

        btnUpdate = findViewById(R.id.updateLabtest);
        btnCancel = findViewById(R.id.cancelLabtest);

        editTextUpdateName = findViewById(R.id.updateNameLabtest);
        editTextUpdatePrice = findViewById(R.id.updatePrice);
        editTextUpdateDescription = findViewById(R.id.updateDescription);

        Intent intent = UpdateLabtestDetail.this.getIntent();
        int id = Integer.parseInt(intent.getStringExtra("id"));
        editTextUpdateName.setText(intent.getStringExtra("labtestName"));
        editTextUpdatePrice.setText(intent.getStringExtra("price"));
        editTextUpdateDescription.setText(intent.getStringExtra("description"));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Labtest labtest = new Labtest();
                labtest.setId(id);
                labtest.setName(editTextUpdateName.getText().toString());
                labtest.setPrice(Integer.parseInt(editTextUpdatePrice.getText().toString()));
                labtest.setDescription(editTextUpdateDescription.getText().toString());

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
}