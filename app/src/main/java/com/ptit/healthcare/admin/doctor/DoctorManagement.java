package com.ptit.healthcare.admin.doctor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.ptit.healthcare.R;
import com.ptit.healthcare.adapter.ListDoctorAdapter;
import com.ptit.healthcare.database.DoctorQuery;
import com.ptit.healthcare.entities.Doctor;

import java.util.List;

public class DoctorManagement extends AppCompatActivity {

    Button btnThemBS, btnTimKiem;
    EditText editTextTimKiemBS;
    ListView listViewBS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_management);
        btnThemBS = findViewById(R.id.themBacSi);
        btnTimKiem = findViewById(R.id.timKiemBacSi);
        listViewBS = findViewById(R.id.danhSachBacSi);
        editTextTimKiemBS = findViewById(R.id.editTextTimKiemBS);

        LoadListBS();
        
        btnThemBS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doOpenAddActivity();
            }
        });
        
        btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextTimKiemBS.getText().toString();
                loadListBSFindByName(name);
            }
        });
    }

    protected void LoadListBS() {
        DoctorQuery doctorQuery = new DoctorQuery(getBaseContext());
        final List<Doctor> listBS = doctorQuery.getAll();

        ListDoctorAdapter adapter = new ListDoctorAdapter(DoctorManagement.this, listBS);
        listViewBS.setAdapter(adapter);

        listViewBS.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Doctor doctor = listBS.get(i);
                doOpenChildActivity(doctor);
            }
        });

        listViewBS.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                return false;
            }
        });
        registerForContextMenu(listViewBS);
    }

    public void loadListBSFindByName(String name){
        DoctorQuery doctorQuery = new DoctorQuery(getBaseContext());
        final List<Doctor> listBS = doctorQuery.findDoctorByName(name);

        ListDoctorAdapter adapter = new ListDoctorAdapter(DoctorManagement.this, listBS);
        listViewBS.setAdapter(adapter);

        listViewBS.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Doctor doctor = listBS.get(i);
                doOpenChildActivity(doctor);
            }
        });

        listViewBS.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                return false;
            }
        });
        registerForContextMenu(listViewBS);
    }

    public void doOpenChildActivity(Doctor doctor) {
        Intent intentDoctorDetail = new Intent(this, DoctorDetail.class);

        intentDoctorDetail.putExtra("idBS", Integer.toString(doctor.getId()));
        intentDoctorDetail.putExtra("tenBS", doctor.getName());
        intentDoctorDetail.putExtra("chuyenKhoa", doctor.getDepartment());
        intentDoctorDetail.putExtra("SDT", doctor.getPhoneNumber());
        intentDoctorDetail.putExtra("kinhNghiem", Integer.toString(doctor.getExperience()));
        startActivityForResult(intentDoctorDetail, 2);
//        startActivity(intentDoctorDetail);
    }

    public void doOpenAddActivity() {
        Intent intentAddDoctor = new Intent(this, AddDoctor.class);
//        startActivity(intentAddDoctor);
        startActivityForResult(intentAddDoctor, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((resultCode == RESULT_OK) && (requestCode == 1)) {
            Intent intentRefresh = new Intent(this, DoctorManagement.class);

            startActivity(intentRefresh);
            this.finish();
        }

        if ((resultCode == RESULT_OK) && (requestCode == 2)) {
            Intent intentRefresh = new Intent(this, DoctorManagement.class);

            startActivity(intentRefresh);
            this.finish();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.danhSachBacSi) {
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.example_menu, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if (item.getItemId() == R.id.menuDelete) {
            DoctorQuery doctorQuery = new DoctorQuery(getBaseContext());
            Doctor doctor = (Doctor) listViewBS.getAdapter().getItem(info.position);

            int doctorId = doctor.getId();

            Toast.makeText(DoctorManagement.this, "You are deleting idDoctor: " +
                    String.valueOf(doctorId), Toast.LENGTH_SHORT).show();
            doctorQuery.delete(doctorId);

            Intent intentRefresh = new Intent(this, DoctorManagement.class);
            startActivity(intentRefresh);
            this.finish();
        }
        return super.onContextItemSelected(item);
    }
}