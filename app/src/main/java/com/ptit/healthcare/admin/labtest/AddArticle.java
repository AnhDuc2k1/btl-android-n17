package com.ptit.healthcare.admin.labtest;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ptit.healthcare.R;
import com.ptit.healthcare.database.ArticleQuery;
import com.ptit.healthcare.entities.Article;


public class AddArticle extends AppCompatActivity {

    Button btnThemBaiBao;

    EditText themTenBaiBao, themNoiDung, themAnh, themDuongDan;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);
        btnThemBaiBao = findViewById(R.id.btnThemBaibao);

        btnThemBaiBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArticleQuery articleQuery = new ArticleQuery(getBaseContext());
                String name = themTenBaiBao.getText().toString();
                String description = themNoiDung.getText().toString();
                String pathImage = themAnh.getText().toString();
                String toUrl = themDuongDan.getText().toString();

                Article newArticle = new Article(name, description, pathImage, toUrl);

                articleQuery.add(newArticle);
                Toast.makeText(getBaseContext(),"Thêm bài báo thành công",Toast.LENGTH_SHORT).show();
                reset();
                setResult(RESULT_OK, null);
                finish();
            }
        });
    }

    protected  void reset()
    {
        themTenBaiBao.setText("");
        themNoiDung.setText("");
        themAnh.setText("");
        themDuongDan.setText("");
    }

}