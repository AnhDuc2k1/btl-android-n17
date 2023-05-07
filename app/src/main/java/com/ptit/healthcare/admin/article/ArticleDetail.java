package com.ptit.healthcare.admin.article;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.ptit.healthcare.R;
import com.ptit.healthcare.database.ArticleQuery;
import com.ptit.healthcare.entities.Article;

public class ArticleDetail extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button btnCapNhat, btnHuy;
    CheckBox cbCapNhat;

    private boolean state = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        Intent intent=ArticleDetail.this.getIntent();

        final EditText editTextIDBB=(EditText)findViewById(R.id.edID);
        final EditText editTextTenBB=(EditText)findViewById(R.id.edTenBB);
        final EditText editTextAnh=(EditText)findViewById(R.id.edAnh);
        final EditText editTextNoiDung=(EditText)findViewById(R.id.edNoidung);
        final EditText editTextUrl=(EditText)findViewById(R.id.edUrl);

        editTextIDBB.setText(intent.getStringExtra("idBB"));
        editTextTenBB.setText(intent.getStringExtra("tenBB"));
        editTextAnh.setText(intent.getStringExtra("ANH"));
        editTextNoiDung.setText(intent.getStringExtra("DESCRIPTION"));
        editTextUrl.setText(intent.getStringExtra("URL"));

        editTextIDBB.setEnabled(false);
        editTextTenBB.setEnabled(false);
        editTextAnh.setEnabled(false);
        editTextNoiDung.setEnabled(false);
        editTextUrl.setEnabled(false);

        btnHuy=findViewById(R.id.btnHuyArticle);
        btnCapNhat=findViewById(R.id.btnCapNhatArticle);
        cbCapNhat=findViewById(R.id.checkboxCapNhatArticle);

        cbCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!state){
                    editTextIDBB.setEnabled(false);
                    editTextTenBB.setEnabled(true);
                    editTextAnh.setEnabled(true);
                    editTextNoiDung.setEnabled(true);
                    editTextUrl.setEnabled(true);
                    state=true;
                }
                else {
                    editTextIDBB.setEnabled(false);
                    editTextTenBB.setEnabled(false);
                    editTextAnh.setEnabled(false);
                    editTextNoiDung.setEnabled(false);
                    editTextUrl.setEnabled(false);
                    state=false;
                }
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Article article = new Article();
                ArticleQuery articleQuery = new ArticleQuery(getBaseContext());
                article.setId(Integer.parseInt(editTextIDBB.getText().toString()));
                article.setPathImage(editTextAnh.getText().toString());
                article.setName_article(editTextTenBB.getText().toString());
                article.setDescription(editTextNoiDung.getText().toString());
                article.setUrl(editTextUrl.getText().toString());

                int result = articleQuery.update(article);
                if (result > 0)
                {
                    Toast.makeText(getBaseContext(), "Cập nhật thông tin thành công!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getBaseContext(), "Cập nhật thông tin không thành công!", Toast.LENGTH_SHORT).show();
                }

                setResult(RESULT_OK, null);
                finish();

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}