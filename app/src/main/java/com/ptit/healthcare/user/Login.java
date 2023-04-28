package com.ptit.healthcare.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ptit.healthcare.R;
import com.ptit.healthcare.admin.AdminManagement;
import com.ptit.healthcare.database.DatabaseHelper;
import com.ptit.healthcare.database.UserQuery;
import com.ptit.healthcare.entities.User;

public class Login extends AppCompatActivity {
    EditText phonenumber, password;
    Button login, backtoRegister;
    UserQuery userQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phonenumber = (EditText) findViewById(R.id.edPhoneNum2);
        password = (EditText) findViewById(R.id.edPassword2);
        login = (Button) findViewById(R.id.btnLogin);
        backtoRegister = (Button) findViewById(R.id.btnBackRegister);
        userQuery = new UserQuery(getBaseContext());

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phone = phonenumber.getText().toString();
                String pass = password.getText().toString();

                if (phone.equals("") || pass.equals(""))
                    Toast.makeText(Login.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkphonepass = userQuery.checkphonenumberpassword(phone, pass);
                    if (checkphonepass == true) {
                        Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        User user1 = userQuery.getUserByPhone(phone);
                        Intent intent;
                        if(!user1.getRoles().equals("ADMIN")) {
                            intent = new Intent(view.getContext(), Home.class);
                            intent.putExtra("idUser", Integer.toString(user1.getId()));
                            intent.putExtra("username", user1.getUsername());
                        }
                        else {
                            intent = new Intent(view.getContext(), AdminManagement.class);
                        }
                        startActivity(intent);

                    } else {
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