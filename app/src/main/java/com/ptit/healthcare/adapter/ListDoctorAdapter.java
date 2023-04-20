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
import com.ptit.healthcare.entities.Doctor;

import java.util.List;

public class ListDoctorAdapter extends ArrayAdapter<Doctor> {


    public ListDoctorAdapter(Context context, List<Doctor> listBS){
        super(context, R.layout.list_view_doctor,listBS);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Doctor doctor = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_doctor,parent,false);
        }

        TextView tenBS = convertView.findViewById(R.id.textViewTenBS);
        TextView chuyenKhoa = convertView.findViewById(R.id.textViewChuyenKhoa);

        tenBS.setText(doctor.getName());
        chuyenKhoa.setText("Chuyên khoa: " + doctor.getDepartment());

        return convertView;
    }
}