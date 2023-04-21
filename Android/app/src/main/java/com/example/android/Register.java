package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText phonenumber, username, password, repassword;
    Button register, backtoLogin;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        phonenumber = (EditText) findViewById(R.id.edPhoneNum);
        username = (EditText) findViewById(R.id.edUsername);
        password = (EditText) findViewById(R.id.edPassword);
        repassword = (EditText) findViewById(R.id.edRePassword);
        register = (Button) findViewById(R.id.btnRegister);
        backtoLogin = (Button) findViewById(R.id.backLogin);
        DB = new DBHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String phone = phonenumber.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("")||phone.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(Register.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkphone = DB.checkphonenumber(phone);
                        if(checkphone==false){
                            Boolean insert = DB.insertData(phone,user,pass);
                            if(insert==true){
                                Toast.makeText(Register.this, "Đăng ký tài khoản thành công", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Home.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(Register.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                            }
                        } else{
                            Toast.makeText(Register.this, "Người dùng đã tồn tại. Vui lòng đăng nhập!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
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