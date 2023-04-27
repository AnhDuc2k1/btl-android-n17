package com.ptit.healthcare.user;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.ptit.healthcare.R;

public class Home extends AppCompatActivity {
    EditText username;
    ImageButton profile, service, history, news, aboutUs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent userIntent = Home.this.getIntent();
        String usernameIntent = userIntent.getStringExtra("username");
        int id = Integer.valueOf(userIntent.getStringExtra("userId"));


        username = (EditText) findViewById(R.id.edUsername2);
        profile = (ImageButton) findViewById(R.id.btnProfile);
        service = (ImageButton) findViewById(R.id.btnService);
        history = (ImageButton) findViewById(R.id.btnHistory);
        news = (ImageButton) findViewById(R.id.btnNews);
        aboutUs = (ImageButton) findViewById(R.id.btnUserinfo);

        username.setText(usernameIntent);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UserProfile.class);
                startActivity(intent);
            }
        });

    }
}