package com.example.baibao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class afterClick extends AppCompatActivity {

    TextView tieude;
    ImageView imageView;
    TextView baibaocuthe;

    @Override
    @SuppressWarnings({"unchecked", "deprecation"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_click);


        Detail detail = (Detail) getIntent().getSerializableExtra("detail");
//
        tieude = (TextView) findViewById(R.id.name);
        imageView = (ImageView) findViewById(R.id.imageView);
        baibaocuthe = (TextView) findViewById(R.id.description);

        //final Detail detail = (Detail) getIntent().getSerializableExtra("detail");
        tieude.setText(detail.getName());

        baibaocuthe.setText(detail.getDescription());
//        imageView.setImageResource(getResources().getIdentifier(detail.getPathImage(), "drawable", getPackageName()));
    }
}