package com.ptit.healthcare.user.booking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.ptit.healthcare.R;
import com.ptit.healthcare.entities.Item;
import com.ptit.healthcare.entities.Order;
import com.ptit.healthcare.utils.DatePickerFragment;
import com.ptit.healthcare.utils.TimePickerFragment;

public class Booking extends AppCompatActivity  implements DatePickerDialog.OnDateSetListener,
                                                            TimePickerDialog.OnTimeSetListener{

    TextView tvNgayKham, tvGioKham;
    Button btnDatLich, btnChonNgay, btnChonGio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        tvNgayKham = findViewById(R.id.tvNgayKham);
        tvGioKham = findViewById(R.id.tvGioKham);
        btnDatLich = findViewById(R.id.userBtnDatLich);
        btnChonNgay = findViewById(R.id.userBtnChonNgay);
        btnChonGio = findViewById(R.id.userBtnChonGio);

        btnChonNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        btnChonGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        btnDatLich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Booking.this.getIntent();

                Order order = (Order)intent.getSerializableExtra("order");
                Item item = (Item)intent.getSerializableExtra("item");

                String startDate = tvGioKham.getText() +" - "+ tvNgayKham.getText();
                item.setStartDate(startDate);


            }
        });

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month ++;
        String date = dayOfMonth +"/" +month + "/" + year;
        tvNgayKham.setText(date);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if (hourOfDay > 12){
            hourOfDay -= 12;
            tvGioKham.setText(hourOfDay + ":" + minute + " PM");
        }
        else {
            tvGioKham.setText(hourOfDay + ":" + minute + " AM");
        }
    }
}