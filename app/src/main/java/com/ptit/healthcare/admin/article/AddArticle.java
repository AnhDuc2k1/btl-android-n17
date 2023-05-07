package com.ptit.healthcare.admin.article;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ptit.healthcare.R;
import com.ptit.healthcare.database.ArticleQuery;
import com.ptit.healthcare.database.DepartmentQuery;
import com.ptit.healthcare.database.DoctorQuery;
import com.ptit.healthcare.entities.Article;
import com.ptit.healthcare.entities.Doctor;

public class AddArticle extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button btnThemBB;

    EditText editTextTenBB, editTextAnh, editTextND, editTextUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);
        btnThemBB=findViewById(R.id.btnThemBB);
        editTextTenBB=findViewById(R.id.editTextTenBB);
        editTextAnh=findViewById(R.id.editTextAnh);
        editTextND=findViewById(R.id.editTextND);
        editTextUrl=findViewById(R.id.editTextUrl);

        btnThemBB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArticleQuery articleQuery = new ArticleQuery(getBaseContext());
                String name = editTextTenBB.getText().toString();
                String image = editTextAnh.getText().toString();
                String description = editTextND.getText().toString();
                String url = editTextUrl.getText().toString();


                if (name.isEmpty()) {
                    editTextTenBB.setError("Vui lòng điền tên bài báo!");
                    return;
                }

                if (image.isEmpty()) {
                    editTextAnh.setError("Vui lòng điền đường dẫn ảnh!");
                    return;
                }


                if (description.isEmpty()) {
                    editTextND.setError("Vui lòng điền nội dung bài báo!");
                    return;
                }

                if (url.isEmpty()) {
                    editTextUrl.setError("Vui lòng điền đường dẫn bài báo!");
                    return;
                }


                Article newArticle = new Article(name, image, description, url);

                articleQuery.add(newArticle);
                Toast.makeText(getBaseContext(),"Thêm bản tin thành công",Toast.LENGTH_SHORT).show();
                reset();
                setResult(RESULT_OK, null);
                finish();
            }
        });
    }
    protected  void reset()
    {
        editTextTenBB.setText("");
        editTextAnh.setText("");
        editTextND.setText("");
        editTextUrl.setText("");
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}