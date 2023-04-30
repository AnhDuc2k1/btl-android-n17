package com.ptit.healthcare.user.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ptit.healthcare.R;
import com.ptit.healthcare.database.UserQuery;

public class Register extends AppCompatActivity {
    EditText phonenumber, username, password, repassword;
    Button register, backtoLogin;
    UserQuery userQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = (EditText) findViewById(R.id.edUsername1);
        phonenumber = (EditText) findViewById(R.id.edPhoneNum1);
        password = (EditText) findViewById(R.id.edPassword3);
        repassword = (EditText) findViewById(R.id.edRePassword);
        register = (Button) findViewById(R.id.btnRegister);
        backtoLogin = (Button) findViewById(R.id.btnBackLogin);
        userQuery = new UserQuery(getBaseContext());

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String phone = phonenumber.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if (user.equals("") || phone.equals("") || pass.equals("") || repass.equals(""))
                    Toast.makeText(Register.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(repass)) {
                        Boolean checkphone = userQuery.checkphonenumber(phone);
                        if (checkphone == false) {
                            Boolean insert = userQuery.insertData(user, phone, pass);
                            if (insert == true) {
                                Toast.makeText(Register.this, "Đăng ký tài khoản thành công", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Login.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(Register.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Register.this, "Người dùng đã tồn tại. Vui lòng đăng nhập!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Register.this, "Các mật khẩu đã nhập không khớp. Hãy nhập lại!", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        backtoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
    }
}