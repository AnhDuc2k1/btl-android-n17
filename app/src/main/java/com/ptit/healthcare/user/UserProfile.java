package com.ptit.healthcare.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ptit.healthcare.R;
import com.ptit.healthcare.admin.doctor.DoctorDetail;

public class UserProfile extends AppCompatActivity {
    Button xemthongtin, dangxuat;
    EditText username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        Intent userIntent= UserProfile.this.getIntent();
        String usernameIntent = userIntent.getStringExtra("username");

        username = (EditText) findViewById(R.id.edUsername3);
        xemthongtin = (Button) findViewById(R.id.btnUserinfo);
        dangxuat = (Button) findViewById(R.id.btnLogout);


        username.setText(usernameIntent);
        dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
    }
}