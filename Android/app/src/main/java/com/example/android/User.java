package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class User extends AppCompatActivity {
    EditText username, phone, pass;
    Button edit, logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        username = (EditText) findViewById(R.id.edUsername2);
        phone = (EditText) findViewById(R.id.edPhoneNum2);
        pass = (EditText) findViewById(R.id.edPassword2);
        edit = (Button) findViewById(R.id.btnEdit);
        logout= (Button) findViewById(R.id.btnLogout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
    }
}