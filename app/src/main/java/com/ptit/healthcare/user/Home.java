package com.ptit.healthcare.user;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.ptit.healthcare.R;
import com.ptit.healthcare.database.UserQuery;
import com.ptit.healthcare.entities.User;
import com.ptit.healthcare.user.authentication.Login;
import com.ptit.healthcare.user.authentication.Register;
import com.ptit.healthcare.user.booking.BookingLabtest;
import com.ptit.healthcare.user.bookinghistory.BookingHistory;

public class Home extends AppCompatActivity {
    private TextView textViewTime;

    private TextView username;
    ImageButton profile, service, history, news, aboutUs, logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Handler handler = new Handler(Looper.getMainLooper());

        Intent userIntent = Home.this.getIntent();
        String usernameIntent = userIntent.getStringExtra("username");
        int id = Integer.valueOf(userIntent.getStringExtra("idUser"));


        textViewTime = findViewById(R.id.date);
        username = findViewById(R.id.edUsername2);
        profile = (ImageButton) findViewById(R.id.btnProfile);
        service = (ImageButton) findViewById(R.id.btnService);
        history = (ImageButton) findViewById(R.id.btnHistory);
        news = (ImageButton) findViewById(R.id.btnNews);
        aboutUs = (ImageButton) findViewById(R.id.btnUserinfo);
        logout = (ImageButton) findViewById(R.id.btnLogout);

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

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BookingHistory.class);
                intent.putExtra("userId", String.valueOf(id));
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                intent.putExtra("userId", String.valueOf(id));
                intent.putExtra("username", usernameIntent);
                startActivity(intent);
            }
        });

        handler.post(new Runnable() {
            @Override
            public void run() {
                Calendar calendar = Calendar.getInstance();

                SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, 'ngày' dd 'tháng' MM 'năm' yyyy", new Locale("vi"));
                String currentTime = dateFormat.format(calendar.getTime());

                textViewTime.setText(currentTime);
                handler.postDelayed(this, 1000);
            }
        });

        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AboutUs.class);
                startActivity(intent);
            }
        });
    }
}


