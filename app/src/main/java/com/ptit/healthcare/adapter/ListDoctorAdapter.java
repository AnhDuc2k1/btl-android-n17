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
import com.ptit.healthcare.database.DepartmentQuery;
import com.ptit.healthcare.entities.Department;
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
        TextView kinhNghiem = convertView.findViewById(R.id.textViewKinhNghiem);

        DepartmentQuery departmentQuery = new DepartmentQuery(convertView.getContext());
        Department department = departmentQuery.getSingle(doctor.getDepartmentId());

        tenBS.setText("Bác sĩ " + doctor.getName());
        chuyenKhoa.setText("Chuyên " + department.getName());
        kinhNghiem.setText("Kinh nghiệm: " + doctor.getExperience() + " năm");

        return convertView;
    }
}