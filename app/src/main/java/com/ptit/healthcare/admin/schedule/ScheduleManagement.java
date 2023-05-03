package com.ptit.healthcare.admin.schedule;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ptit.healthcare.R;
import com.ptit.healthcare.adapter.ListScheduleAdapter;
import com.ptit.healthcare.admin.labtest.LabtestManagement;
import com.ptit.healthcare.database.ExaminationScheduleQuery;
import com.ptit.healthcare.entities.ExaminationSchedule;

import java.util.List;

public class ScheduleManagement extends AppCompatActivity {

    ListView listViewSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_management);
        listViewSchedule = findViewById(R.id.listSchedule);

        loadListSchedule();
    }

    protected void loadListSchedule() {
        ExaminationScheduleQuery scheduleQuery = new ExaminationScheduleQuery(getBaseContext());
        List<ExaminationSchedule> scheduleList = scheduleQuery.getAllByStatus();
        ListScheduleAdapter adapter = new ListScheduleAdapter(ScheduleManagement.this, scheduleList);
        listViewSchedule.setAdapter(adapter);

        listViewSchedule.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ExaminationSchedule examinationSchedule = scheduleList.get(position);
                doOpenChildActivity(examinationSchedule);
            }
        });

        listViewSchedule.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return false;
            }
        });

        registerForContextMenu(listViewSchedule);
    }

    private void doOpenChildActivity(ExaminationSchedule examinationSchedule) {
        Intent intentScheduleDetail = new Intent(this, ScheduleDetail.class);

        intentScheduleDetail.putExtra("id", String.valueOf(examinationSchedule.getId()));
        intentScheduleDetail.putExtra("userId", String.valueOf(examinationSchedule.getUserId()));
        intentScheduleDetail.putExtra("labtestId", String.valueOf(examinationSchedule.getLabtestId()));
        intentScheduleDetail.putExtra("doctorId", String.valueOf(examinationSchedule.getDoctorId()));
        intentScheduleDetail.putExtra("time", examinationSchedule.getExaminationTime());
        intentScheduleDetail.putExtra("date", examinationSchedule.getExaminationDate());

        startActivityForResult(intentScheduleDetail, 2);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if((resultCode == RESULT_OK) && (requestCode == 1)) {
            Intent refresh = new Intent(this, ScheduleManagement.class);
            startActivity(refresh);
            this.finish();
        }

        if((resultCode == RESULT_OK) && (requestCode == 2)) {
            Intent refresh = new Intent(this, ScheduleManagement.class);
            startActivity(refresh);
            this.finish();
        }
    }
}