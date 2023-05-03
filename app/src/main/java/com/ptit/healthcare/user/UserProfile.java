package com.ptit.healthcare.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.ptit.healthcare.R;
import com.ptit.healthcare.admin.user.UserDetail;
import com.ptit.healthcare.database.UserQuery;
import com.ptit.healthcare.entities.User;
import com.ptit.healthcare.user.authentication.Login;
import com.ptit.healthcare.utils.ValidateField;

public class UserProfile extends AppCompatActivity {
    Button btnCapNhat, btnCancel;
    CheckBox cbCapNhat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        Intent intent= UserProfile.this.getIntent();


        final EditText editTextUsername =(EditText)findViewById(R.id.edUsername3);
        final EditText editTextEmail = (EditText)findViewById(R.id.edEmail);
        final EditText editTextPhoneNum =(EditText)findViewById(R.id.edPhoneNum3);
        final EditText editTextPass =(EditText)findViewById(R.id.edPassword3);
        final EditText editTextDob =(EditText)findViewById(R.id.edDob);
        final EditText editTextHeight =(EditText)findViewById(R.id.edHeight);
        final EditText editTextWeight =(EditText)findViewById(R.id.edWeight);

        String username = intent.getStringExtra("username");

        int id = Integer.parseInt(intent.getStringExtra("id").toString());
        editTextUsername.setText(username);
        editTextEmail.setText(intent.getStringExtra("email"));
        editTextPhoneNum.setText(intent.getStringExtra("phoneNumber"));
        editTextPass.setText(intent.getStringExtra("password"));
        editTextDob.setText(intent.getStringExtra("dob"));
        editTextHeight.setText(intent.getStringExtra("height"));
        editTextWeight.setText(intent.getStringExtra("weight"));


        editTextUsername.setEnabled(false);
        editTextEmail.setEnabled(false);
        editTextPhoneNum.setEnabled(false);
        editTextPass.setEnabled(false);
        editTextDob.setEnabled(false);
        editTextHeight.setEnabled(false);
        editTextWeight.setEnabled(false);

        btnCancel=(Button) findViewById(R.id.btnCancel);
        btnCapNhat= (Button) findViewById(R.id.btnCapNhat);
        cbCapNhat= (CheckBox) findViewById(R.id.checkboxCapNhat);

        cbCapNhat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editTextUsername.setEnabled(isChecked);
                editTextEmail.setEnabled(isChecked);
                editTextPhoneNum.setEnabled(isChecked);
                editTextPass.setEnabled(isChecked);
                editTextDob.setEnabled(isChecked);
                editTextHeight.setEnabled(isChecked);
                editTextWeight.setEnabled(isChecked);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                intent.putExtra("idUser", String.valueOf(id));
                intent.putExtra("username", username);

                startActivity(intent);
            }
        });

        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ValidateField.validateEmail(editTextEmail.getText().toString())) {
                    Toast.makeText(UserProfile.this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
                }
                else if( !ValidateField.validatePhoneNumber(editTextPhoneNum.getText().toString())) {
                    Toast.makeText(UserProfile.this, "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
                }
                else if(!ValidateField.validatePassword(editTextPass.getText().toString())) {
                    Toast.makeText(UserProfile.this, "Mật khẩu phải có ít nhất 5 ký tự", Toast.LENGTH_SHORT).show();
                }
                else {
                    User user = new User();
                    UserQuery db = new UserQuery(getBaseContext());
                    user.setId(id);
                    user.setUsername(editTextUsername.getText().toString());
                    user.setEmail(editTextEmail.getText().toString());
                    user.setPassword(editTextPass.getText().toString());
                    user.setPhoneNumber(editTextPhoneNum.getText().toString());
                    user.setDob(editTextDob.getText().toString());
                    user.setWeight(Integer.parseInt(editTextWeight.getText().toString()));
                    user.setHeight(Integer.parseInt(editTextHeight.getText().toString()));
                    int result = db.update(user);
                    if (result > 0) {
                        Toast.makeText(getBaseContext(), "Cập nhật thông tin thành công!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getBaseContext(), "Cập nhật thông tin không thành công!", Toast.LENGTH_SHORT).show();
                    }
                    setResult(RESULT_OK, null);
                    finish();
                }
            }
        });
    }
}