package com.ptit.healthcare.admin.user;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.ptit.healthcare.R;
import com.ptit.healthcare.adapter.ListUserAdapter;
import com.ptit.healthcare.database.UserQuery;
import com.ptit.healthcare.entities.User;

import java.util.List;

public class UserManagement extends AppCompatActivity {

    Button btnAddNewUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_management);
        btnAddNewUser = findViewById(R.id.addNewUser);

        btnAddNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), UserDetail.class);
                startActivityForResult(intent, 1);
            }
        });
        loadListUser();
    }


    protected void loadListUser() {
        UserQuery userQuery = new UserQuery(getBaseContext());
        List<User> userList = userQuery.getAll();
        ListView listUserView = (ListView) findViewById(R.id.listUser);
        ListUserAdapter userAdapter = new ListUserAdapter(this, userList);
        listUserView.setAdapter(userAdapter);

        listUserView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                doOpenChildActivity(userList.get(position));
            }
        });

        listUserView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return false;
            }
        });

        registerForContextMenu(listUserView);
    }

    private void doOpenChildActivity(User user) {
        Intent updateUserIntent = new Intent(this, UpdateUserDetail.class);
        updateUserIntent.putExtra("id", String.valueOf(user.getId()));
        updateUserIntent.putExtra("username", user.getUsername());
        updateUserIntent.putExtra("email", user.getEmail());
        updateUserIntent.putExtra("password", user.getPassword());
        updateUserIntent.putExtra("phoneNumber", user.getPhoneNumber());
        updateUserIntent.putExtra("dob", user.getDob());
        updateUserIntent.putExtra("height", String.valueOf(user.getHeight()));
        updateUserIntent.putExtra("weight", String.valueOf(user.getWeight()));
        startActivityForResult(updateUserIntent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((resultCode == RESULT_OK) && (requestCode == 1)) {
            Intent refresh = new Intent(this, UserManagement.class);
            startActivity(refresh);
            this.finish();
        }
        if ((resultCode == RESULT_OK) && (requestCode == 2)) {
            Intent refresh = new Intent(this, UserManagement.class);
            startActivity(refresh);
            this.finish();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        if(view.getId() == R.id.listUser) {
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.example_menu, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if(item.getItemId() == R.id.menuDelete) {
            UserQuery db = new UserQuery(getBaseContext());
            ListView listViewUser = (ListView) findViewById(R.id.listUser);
            User user = (User) listViewUser.getAdapter().getItem(info.position);
            int id = user.getId();
            Toast.makeText(UserManagement.this, "Xóa user với ID: " +
                    String.valueOf(id), Toast.LENGTH_SHORT).show();
            int idDelete = db.delete(id);
            Intent refresh = new Intent(this, UserManagement.class);
            startActivity(refresh);
            this.finish();
        }
        return super.onContextItemSelected(item);
    }
}