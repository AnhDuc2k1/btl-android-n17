package com.ptit.healthcare.admin.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ptit.healthcare.R;
import com.ptit.healthcare.database.UserQuery;
import com.ptit.healthcare.entities.User;
import com.ptit.healthcare.utils.ValidateField;

public class UserDetail extends AppCompatActivity {

    Button btnAddNewStudent;
    EditText editTextUsername;
    EditText editTextEmail;
    EditText editTextPassword;
    EditText editTextPhoneNumber;
    EditText editTextDob;
    EditText editTextWeight;
    EditText editTextHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        btnAddNewStudent = findViewById(R.id.addNew);
        editTextUsername = findViewById(R.id.username);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        editTextPhoneNumber = findViewById(R.id.phoneNumber);
        editTextDob = findViewById(R.id.dob);
        editTextWeight = findViewById(R.id.weight);
        editTextHeight = findViewById(R.id.height);

        btnAddNewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ValidateField.validateEmail(editTextEmail.getText().toString())) {
                    Toast.makeText(UserDetail.this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
                }
                else if( !ValidateField.validatePhoneNumber(editTextPhoneNumber.getText().toString())) {
                    Toast.makeText(UserDetail.this, "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
                }
                else if(!ValidateField.validatePassword(editTextPassword.getText().toString())) {
                    Toast.makeText(UserDetail.this, "Mật khẩu phải có ít nhất 5 ký tự", Toast.LENGTH_SHORT).show();
                }
                else {
                    UserQuery userQuery = new UserQuery(getBaseContext());
                    String username = editTextUsername.getText().toString();
                    String email = editTextEmail.getText().toString();
                    String password = editTextPassword.getText().toString();
                    String phoneNumber = editTextPhoneNumber.getText().toString();
                    String dob = editTextDob.getText().toString();
                    int weight = Integer.parseInt(editTextWeight.getText().toString());
                    int height = Integer.parseInt(editTextHeight.getText().toString());

                    User user = new User(username, email, password, phoneNumber, dob, "USER", height, weight);
                    userQuery.add(user);
                    Toast.makeText(getBaseContext(), "Thêm người dùng thành công", Toast.LENGTH_SHORT).show();
                    reset();
                    setResult(RESULT_OK, null);
                    finish();
                }
            }
        });
    }

    protected void reset() {
        editTextUsername.setText("");
        editTextEmail.setText("");
        editTextPassword.setText("");
        editTextPhoneNumber.setText("");
        editTextDob.setText("");
        editTextWeight.setText("");
        editTextHeight.setText("");
    }
}