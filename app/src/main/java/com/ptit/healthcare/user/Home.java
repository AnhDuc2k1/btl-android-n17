package com.ptit.healthcare.user;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.ptit.healthcare.R;
import com.ptit.healthcare.database.UserQuery;
import com.ptit.healthcare.entities.User;
import com.ptit.healthcare.user.booking.BookingLabtest;

public class Home extends AppCompatActivity {
    EditText username;
    ImageButton profile, service, history, news, aboutUs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent userIntent = Home.this.getIntent();
        String usernameIntent = userIntent.getStringExtra("username");
        int id = Integer.valueOf(userIntent.getStringExtra("idUser"));

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
                UserQuery userQuery = new UserQuery(getBaseContext());
                User user = userQuery.getSingle(id);
                intent.putExtra("id", String.valueOf(user.getId()));
                intent.putExtra("username", user.getUsername());
                intent.putExtra("email", user.getEmail());
                intent.putExtra("password", user.getPassword());
                intent.putExtra("phoneNumber", user.getPhoneNumber());
                intent.putExtra("dob", user.getDob());
                intent.putExtra("height", String.valueOf(user.getHeight()));
                intent.putExtra("weight", String.valueOf(user.getWeight()));
                startActivity(intent);
            }
        });

        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BookingLabtest.class);
                intent.putExtra("userId", String.valueOf(id));
                intent.putExtra("username", usernameIntent);
                startActivity(intent);
            }
        });

    }
}