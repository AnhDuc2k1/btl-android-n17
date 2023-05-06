package com.ptit.healthcare.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ptit.healthcare.R;
import com.ptit.healthcare.database.DepartmentQuery;
import com.ptit.healthcare.database.LabtestQuery;
import com.ptit.healthcare.entities.Department;
import com.ptit.healthcare.entities.Doctor;
import com.ptit.healthcare.entities.ExaminationSchedule;
import com.ptit.healthcare.entities.Labtest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListBookingHistoryAdapter extends ArrayAdapter<ExaminationSchedule> {


    public ListBookingHistoryAdapter(Context context, List<ExaminationSchedule> listSchedule){
        super(context, R.layout.list_view_booking_history,listSchedule);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ExaminationSchedule schedule = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_booking_history,parent,false);
        }

        LabtestQuery labtestQuery = new LabtestQuery(convertView.getContext());

        TextView ngayKham = convertView.findViewById(R.id.examinationDay);
        TextView thangKham = convertView.findViewById(R.id.examinationMonth);
        TextView namKham = convertView.findViewById(R.id.examinationYear);

        TextView tenGoiKham = convertView.findViewById(R.id.nameOfLabtest);
        TextView gioKham = convertView.findViewById(R.id.examinationTime);
        TextView tongTien = convertView.findViewById(R.id.total);

        TextView trangThai = convertView.findViewById(R.id.bookingStatus);

        List<String> date = Arrays.asList(schedule.getExaminationDate().split("/"));

        ngayKham.setText(date.get(0));
        thangKham.setText("T" + date.get(1));
        namKham.setText(date.get(2));

        Labtest labtest = labtestQuery.getSingle(schedule.getLabtestId());

        tenGoiKham.setText(labtest.getName());
        gioKham.setText("Giờ khám: " + schedule.getExaminationTime());
        tongTien.setText("Tổng tiền: " + schedule.getPrice());

        if (schedule.getStatus().equals("Huỷ")){
            trangThai.setTextColor(Color.parseColor("#FF0000"));
        }
        else {
            trangThai.setTextColor(Color.parseColor("#32CD32"));
        }
        trangThai.setText(schedule.getStatus());

        return convertView;
    }
}