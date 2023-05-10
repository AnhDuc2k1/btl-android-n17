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
import android.widget.Toast;

import com.ptit.healthcare.R;
import com.ptit.healthcare.database.ExaminationScheduleQuery;
import com.ptit.healthcare.entities.Item;
import com.ptit.healthcare.entities.ExaminationSchedule;
import com.ptit.healthcare.user.Home;
import com.ptit.healthcare.utils.CurrentDateTime;
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

        tvGioKham.setText("06:00");
        tvNgayKham.setText(CurrentDateTime.getCurrentDate());

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
                ExaminationSchedule schedule = (ExaminationSchedule)intent.getSerializableExtra("schedule");

                ExaminationScheduleQuery query = new ExaminationScheduleQuery(getBaseContext());

                String time = tvGioKham.getText().toString();
                String date = tvNgayKham.getText().toString();

                schedule.setExaminationTime(time);
                schedule.setExaminationDate(date);

                if (!query.getScheduleByExamTime(schedule.getLabtestId(), schedule.getDoctorId(),time, date)){
                    Toast.makeText(getBaseContext(),"Đặt lịch khám không thành công do thời gian bị trùng!",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    query.add(schedule);

                    Toast.makeText(getBaseContext(),"Đặt lịch khám thành công",Toast.LENGTH_SHORT).show();

                    Intent homeIntent = new Intent(getApplicationContext(), Home.class);
                    homeIntent.putExtra("idUser", intent.getStringExtra("userId"));
                    homeIntent.putExtra("username", intent.getStringExtra("username"));
                    startActivity(homeIntent);
                }
            }
        });

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month ++;
        if (month < 10) {
            String date = dayOfMonth +"/0" +month + "/" + year;
            tvNgayKham.setText(date);
        }
        else{
            String date = dayOfMonth +"/" +month + "/" + year;
            tvNgayKham.setText(date);
        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String m = String.valueOf(minute);
        if (minute < 10) {
            m = "0" + m;
        }
        if (hourOfDay < 10) {
            tvGioKham.setText("0" + hourOfDay + ":" + m);
        }
        else{
            tvGioKham.setText(hourOfDay + ":" + m);
        }
    }
}