package com.ptit.healthcare.user;

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
import com.ptit.healthcare.database.UserQuery;
import com.ptit.healthcare.entities.Doctor;
import com.ptit.healthcare.entities.User;

public class UserProfile extends AppCompatActivity {
    Button btnCapNhat, btnLogout;
    CheckBox cbCapNhat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        Intent intent= UserProfile.this.getIntent();


        final EditText editTextUsername =(EditText)findViewById(R.id.edUsername3);
        final EditText editTextEmail = (EditText)findViewById(R.id.edEmail);
        final EditText editTextPhoneNum =(EditText)findViewById(R.id.edPhoneNum3);
        final EditText editTextPass =(EditText)findViewById(R.id.edPassword3);
        final EditText editTextDob =(EditText)findViewById(R.id.edDob);
        final EditText editTextHeight =(EditText)findViewById(R.id.edHeight);
        final EditText editTextWeight =(EditText)findViewById(R.id.edWeight);

        int id = Integer.parseInt(intent.getStringExtra("id").toString());
        editTextUsername.setText(intent.getStringExtra("username"));
        editTextEmail.setText(intent.getStringExtra("email"));
        editTextPhoneNum.setText(intent.getStringExtra("phonenumber"));
        editTextPass.setText(intent.getStringExtra("password"));
        editTextDob.setText(intent.getStringExtra("dob"));
        editTextHeight.setText(intent.getStringExtra("height"));
        editTextWeight.setText(intent.getStringExtra("weight"));


        editTextUsername.setEnabled(false);
        editTextEmail.setEnabled(false);
        editTextPhoneNum.setEnabled(false);
        editTextPass.setEnabled(false);
        editTextDob.setEnabled(false);
        editTextHeight.setEnabled(false);
        editTextWeight.setEnabled(false);

        btnLogout=(Button) findViewById(R.id.btnLogout);
        btnCapNhat= (Button) findViewById(R.id.btnCapNhat);
        cbCapNhat= (CheckBox) findViewById(R.id.checkboxCapNhat);

        cbCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextUsername.setEnabled(true);
                editTextEmail.setEnabled(true);
                editTextPhoneNum.setEnabled(true);
                editTextPass.setEnabled(true);
                editTextDob.setEnabled(true);
                editTextHeight.setEnabled(true);
                editTextWeight.setEnabled(true);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });

        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                UserQuery db = new UserQuery(getBaseContext());
                user.setId(id);
                user.setUsername(editTextUsername.getText().toString());
                user.setEmail(editTextEmail.getText().toString());
                user.setPassword(editTextPass.getText().toString());
                user.setPhoneNumber(editTextPhoneNum.getText().toString());
                user.setDob(editTextDob.getText().toString());
                user.setWeight(Integer.parseInt(editTextWeight.getText().toString()));
                user.setHeight(Integer.parseInt(editTextHeight.getText().toString()));

                int result = db.update(user);
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