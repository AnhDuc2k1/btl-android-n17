package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText phonenumber, password;
    Button login, backtoRegister;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phonenumber = (EditText) findViewById(R.id.edPhoneNum1);
        password = (EditText) findViewById(R.id.edPassword1);
        login = (Button) findViewById(R.id.btnLogin);
        backtoRegister = (Button) findViewById(R.id.backRegister);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phone = phonenumber.getText().toString();
                String pass = password.getText().toString();

                if(phone.equals("")||pass.equals(""))
                    Toast.makeText(Login.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkphonepass = DB.checkphonenumberpassword(phone,pass);
                    if(checkphonepass==true){
                        Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Home.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Login.this, "Thông tin đăng nhập không hợp lệ", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        backtoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });
    }
}