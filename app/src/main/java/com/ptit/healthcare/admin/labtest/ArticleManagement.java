package com.ptit.healthcare.admin.labtest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.ptit.healthcare.R;
import com.ptit.healthcare.adapter.ListArticleAdapter;
import com.ptit.healthcare.database.ArticleQuery;
import com.ptit.healthcare.entities.Article;

import java.util.List;

public class ArticleManagement extends AppCompatActivity {

    Button btnThemBaiBao;
    ListView listViewArticle;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_view);

        listViewArticle = findViewById(R.id.listViewArticle);

        LoadListArticle();

        btnThemBaiBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doOpenAddActivity();
            }
        });

    }

    protected void LoadListArticle() {
        ArticleQuery articleQuery = new ArticleQuery(getBaseContext());
        final List<Article> listArticle = articleQuery.getAll();

        ListArticleAdapter adapter = new ListArticleAdapter(ArticleManagement.this, listArticle);
        listViewArticle.setAdapter(adapter);

        listViewArticle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Article doctor = listArticle.get(i);
                doOpenChildActivity(doctor);
            }
        });

        listViewArticle.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                return false;
            }
        });
        registerForContextMenu(listViewArticle);
    }



    public void doOpenChildActivity(Article article) {
        Intent intentArticleDetail= new Intent(this, ViewArticleDetail.class);

        intentArticleDetail.putExtra("idAr", Integer.toString(article.getId()));
        intentArticleDetail.putExtra("pathImage", article.getPathImage());
        intentArticleDetail.putExtra("name", article.getName());
        intentArticleDetail.putExtra("description", article.getDescription());
        intentArticleDetail.putExtra("goToUrl", article.getGoToUrl());
        startActivityForResult(intentArticleDetail, 2);
//        startActivity(intentArticleDetail);
    }

    public void doOpenAddActivity() {
        Intent intentAddDoctor = new Intent(this, AddArticle.class);
//        startActivity(intentAddDoctor);
        startActivityForResult(intentAddDoctor, 1);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if ((resultCode == RESULT_OK) && (requestCode == 1)) {
//            Intent intentRefresh = new Intent(this, DoctorManagement.class);
//
//            startActivity(intentRefresh);
//            this.finish();
//        }
//
//        if ((resultCode == RESULT_OK) && (requestCode == 2)) {
//            Intent intentRefresh = new Intent(this, DoctorManagement.class);
//
//            startActivity(intentRefresh);
//            this.finish();
//        }
//    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.listViewArticle) {
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.example_menu, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if (item.getItemId() == R.id.menuDelete) {
            ArticleQuery articleQuery = new ArticleQuery(getBaseContext());
            Article article = (Article) listViewArticle.getAdapter().getItem(info.position);

            int articleId = article.getId();

            Toast.makeText(ArticleManagement.this, "You are deleting idArticle: " +
                    String.valueOf(articleId), Toast.LENGTH_SHORT).show();
            articleQuery.delete(articleId);

            Intent intentRefresh = new Intent(this, ArticleManagement.class);
            startActivity(intentRefresh);
            this.finish();
        }
        return super.onContextItemSelected(item);
    }
}