package com.ptit.healthcare.admin.labtest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.ptit.healthcare.adapter.ListLabtestAdapter;
import com.ptit.healthcare.database.LabtestQuery;
import com.ptit.healthcare.entities.Labtest;

import java.util.List;

public class LabtestManagement extends AppCompatActivity {

    Button btnAddNewLabtest;

    Button btnSearchLabtestButton;

    EditText editTextSearchLabtestField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labtest_management);

        btnAddNewLabtest = findViewById(R.id.addLabtest);
        btnSearchLabtestButton = findViewById(R.id.searchLabtestButton);
        editTextSearchLabtestField = findViewById(R.id.searchLabtestField);

        btnAddNewLabtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), LabtestDetail.class);
                startActivityForResult(intent, 1);
            }
        });
        loadListLabtest();

        btnSearchLabtestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextSearchLabtestField.getText().toString();
                loadListLabtestByName(name);
            }
        });

        editTextSearchLabtestField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loadListLabtest();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    protected void loadListLabtest() {
        LabtestQuery db = new LabtestQuery(getBaseContext());
        List<Labtest> labtestList = db.getAll();
        ListView listLabtestView = (ListView) findViewById(R.id.listLabtest);
        ListLabtestAdapter labtestAdapter = new ListLabtestAdapter(this, labtestList);
        listLabtestView.setAdapter(labtestAdapter);

        listLabtestView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                doOpenChildActivity(labtestList.get(position));
            }
        });

        listLabtestView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return false;
            }
        });

        registerForContextMenu(listLabtestView);
    }

    protected void loadListLabtestByName(String name) {
        LabtestQuery db = new LabtestQuery(getBaseContext());
        List<Labtest> labtestList = db.getAllLabtestByName(name);
        ListView listLabtestView = (ListView) findViewById(R.id.listLabtest);
        ListLabtestAdapter labtestAdapter = new ListLabtestAdapter(this, labtestList);
        listLabtestView.setAdapter(labtestAdapter);

        listLabtestView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                doOpenChildActivity(labtestList.get(position));
            }
        });

        listLabtestView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return false;
            }
        });

        registerForContextMenu(listLabtestView);
    }

    private void doOpenChildActivity(Labtest labtest) {
        Intent updateLabtestIntent = new Intent(this, UpdateLabtestDetail.class);
        updateLabtestIntent.putExtra("id", String.valueOf(labtest.getId()));
        updateLabtestIntent.putExtra("labtestName", labtest.getName());
        updateLabtestIntent.putExtra("price", String.valueOf(labtest.getPrice()));
        updateLabtestIntent.putExtra("description", String.valueOf(labtest.getDescription()));
        startActivityForResult(updateLabtestIntent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if((resultCode == RESULT_OK) && (requestCode == 1)) {
            Intent refresh = new Intent(this, LabtestManagement.class);
            startActivity(refresh);
            this.finish();
        }

        if((resultCode == RESULT_OK) && (requestCode == 2)) {
            Intent refresh = new Intent(this, LabtestManagement.class);
            startActivity(refresh);
            this.finish();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        if(view.getId() == R.id.listLabtest) {
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.example_menu, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if(item.getItemId() == R.id.menuDelete) {
            LabtestQuery db = new LabtestQuery(getBaseContext());
            ListView listViewLabtest = (ListView) findViewById(R.id.listLabtest);
            Labtest labtest = (Labtest) listViewLabtest.getAdapter().getItem(info.position);
            int id = labtest.getId();
            Toast.makeText(LabtestManagement.this, "Xóa gói khám với ID: " + String.valueOf(id),
                    Toast.LENGTH_SHORT).show();
            db.delete(id);
            Intent refresh = new Intent(this, LabtestManagement.class);
            startActivity(refresh);
            this.finish();
        }

        return super.onContextItemSelected(item);
    }
}