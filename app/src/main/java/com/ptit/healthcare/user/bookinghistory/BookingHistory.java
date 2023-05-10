package com.ptit.healthcare.user.bookinghistory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ptit.healthcare.R;
import com.ptit.healthcare.adapter.ListBookingHistoryAdapter;
import com.ptit.healthcare.adapter.ListDoctorAdapter;
import com.ptit.healthcare.admin.doctor.DoctorManagement;
import com.ptit.healthcare.database.DepartmentQuery;
import com.ptit.healthcare.database.DoctorQuery;
import com.ptit.healthcare.database.ExaminationScheduleQuery;
import com.ptit.healthcare.database.LabtestQuery;
import com.ptit.healthcare.entities.Department;
import com.ptit.healthcare.entities.Doctor;
import com.ptit.healthcare.entities.ExaminationSchedule;
import com.ptit.healthcare.entities.Labtest;
import com.ptit.healthcare.user.booking.BookingDoctor;
import com.ptit.healthcare.user.booking.DoctorInfo;

import java.nio.channels.InterruptedByTimeoutException;
import java.util.List;

public class BookingHistory extends AppCompatActivity {

    ListView listViewLSDatLich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_history);
        listViewLSDatLich = findViewById(R.id.listLSDatLich);

        loadListBookingHistory();
    }

    protected void loadListBookingHistory() {
        Intent intent = BookingHistory.this.getIntent();
        int userId = Integer.valueOf(intent.getStringExtra("userId"));

        ExaminationScheduleQuery query = new ExaminationScheduleQuery(getBaseContext());
        final List<ExaminationSchedule> listSchedule = query.getAllByUserId(userId);

        ListBookingHistoryAdapter adapter = new ListBookingHistoryAdapter(BookingHistory.this,
                                                                                listSchedule);
        listViewLSDatLich.setAdapter(adapter);

        listViewLSDatLich.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                doOpenChildActivity(listSchedule.get(i));
            }
        });
    }

    private void doOpenChildActivity(ExaminationSchedule schedule) {
        Intent intent = BookingHistory.this.getIntent();
        int userId = Integer.valueOf(intent.getStringExtra("userId"));

        Intent bookingDetailIntent = new Intent(this, BookingDetail.class);

        bookingDetailIntent.putExtra("userId", String.valueOf(userId));
        bookingDetailIntent.putExtra("labtestId", String.valueOf(schedule.getLabtestId()));
        bookingDetailIntent.putExtra("scheduleId", String.valueOf(schedule.getId()));
        bookingDetailIntent.putExtra("examinationTime", schedule.getExaminationTime());
        bookingDetailIntent.putExtra("examinationDate", schedule.getExaminationDate());
        bookingDetailIntent.putExtra("price", String.valueOf(schedule.getPrice()));
        bookingDetailIntent.putExtra("status", schedule.getStatus());
        bookingDetailIntent.putExtra("doctorId", String.valueOf(schedule.getDoctorId()));

        startActivity(bookingDetailIntent);
    }
}