package com.ptit.healthcare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ptit.healthcare.R;
import com.ptit.healthcare.database.ExaminationScheduleQuery;
import com.ptit.healthcare.database.LabtestQuery;
import com.ptit.healthcare.database.UserQuery;
import com.ptit.healthcare.entities.ExaminationSchedule;
import com.ptit.healthcare.entities.Labtest;
import com.ptit.healthcare.entities.User;

import java.util.List;

public class ListScheduleAdapter extends ArrayAdapter<ExaminationSchedule> {

    public ListScheduleAdapter(Context context, List<ExaminationSchedule> listSchedule) {
        super(context, R.layout.list_view_schedule, listSchedule);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ExaminationSchedule schedule = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_schedule, parent, false);
        }

        TextView textViewPatientName = convertView.findViewById(R.id.textViewPatient);
        TextView textViewLabtest = convertView.findViewById(R.id.textViewLabtest);
        TextView textViewDateTime = convertView.findViewById(R.id.textViewDateTime);

        ExaminationScheduleQuery scheduleQuery = new ExaminationScheduleQuery(convertView.getContext());
        ExaminationSchedule schedule1 = scheduleQuery.getSingle(schedule.getId());
        UserQuery userQuery = new UserQuery(convertView.getContext());
        User user = userQuery.getSingle(schedule1.getUserId());
        LabtestQuery labtestQuery = new LabtestQuery(convertView.getContext());
        Labtest labtest = labtestQuery.getSingle(schedule1.getLabtestId());

        textViewPatientName.setText("Bệnh nhân: " + user.getUsername());
        textViewLabtest.setText(labtest.getName());
        textViewDateTime.setText(schedule1.getExaminationTime() + " ngày " + schedule1.getExaminationDate());

        return convertView;
    }
}
