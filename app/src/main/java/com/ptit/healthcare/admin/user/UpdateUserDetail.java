package com.ptit.healthcare.admin.user;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ptit.healthcare.R;
import com.ptit.healthcare.database.UserQuery;
import com.ptit.healthcare.entities.User;

public class UpdateUserDetail extends AppCompatActivity {

    EditText editTextUsername;
    EditText editTextEmail;
    EditText editTextPassword;
    EditText editTextPhoneNumber;
    EditText editTextDob;
    EditText editTextWeight;
    EditText editTextHeight;
    Button btnUpdate;
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_detail);

        Intent intent = UpdateUserDetail.this.getIntent();

        editTextUsername = (EditText) findViewById(R.id.username1);
        editTextEmail = (EditText) findViewById(R.id.email1);
        editTextPassword = (EditText) findViewById(R.id.password1);
        editTextPhoneNumber = (EditText) findViewById(R.id.phoneNumber1);
        editTextDob = (EditText) findViewById(R.id.dob1);
        editTextWeight = (EditText) findViewById(R.id.weight1);
        editTextHeight = (EditText) findViewById(R.id.height1);

        btnUpdate = (Button) findViewById(R.id.update);
        btnCancel = (Button) findViewById(R.id.cancel);

        int id = Integer.parseInt(intent.getStringExtra("id").toString());
        editTextUsername.setText(intent.getStringExtra("username"));
        editTextEmail.setText(intent.getStringExtra("email"));
        editTextPassword.setText(intent.getStringExtra("password"));
        editTextPhoneNumber.setText(intent.getStringExtra("phoneNumber"));
        editTextDob.setText(intent.getStringExtra("dob"));
        editTextWeight.setText(intent.getStringExtra("weight"));
        editTextHeight.setText(intent.getStringExtra("height"));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setId(id);
                user.setUsername(editTextUsername.getText().toString());
                user.setEmail(editTextEmail.getText().toString());
                user.setPassword(editTextPassword.getText().toString());
                user.setPhoneNumber(editTextPhoneNumber.getText().toString());
                user.setDob(editTextDob.getText().toString());
                user.setRoles("USER");
                user.setWeight(Integer.parseInt(editTextWeight.getText().toString()));
                user.setHeight(Integer.parseInt(editTextHeight.getText().toString()));

                UserQuery db = new UserQuery(getBaseContext());
                int statusCode = db.update(user);
                if(statusCode > 0) {
                    Toast.makeText(getBaseContext(), "Cập nhật user thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getBaseContext(), "Vui lòng thử lại", Toast.LENGTH_SHORT).show();
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