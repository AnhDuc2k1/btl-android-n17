package com.ptit.healthcare.admin.article;

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
import android.widget.ListView;
import android.widget.Toast;

import com.ptit.healthcare.R;
import com.ptit.healthcare.adapter.ListArticleAdapter;
import com.ptit.healthcare.database.ArticleQuery;
import com.ptit.healthcare.entities.Article;


import java.util.List;

public class ArticleManagement extends AppCompatActivity {

    Button btnThemBB;
    ListView listViewBB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_management);
        btnThemBB=findViewById(R.id.themBaiBao);
        listViewBB=findViewById(R.id.danhSachBaiBao);

        LoadListBB();

        btnThemBB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doOpenAddActivity();
            }
        });
    }



    private void LoadListBB() {
        ArticleQuery articleQuery = new ArticleQuery(getBaseContext());
        final List<Article> listBB = articleQuery.getAll();

        ListArticleAdapter adapter = new ListArticleAdapter(ArticleManagement.this, listBB);
        listViewBB.setAdapter(adapter);

        listViewBB.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Article article = listBB.get(i);
                doOpenChildActivity(article);
            }
        });

        listViewBB.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                return false;
            }
        });
        registerForContextMenu(listViewBB);
    }


    public void doOpenChildActivity(Article article) {

        Intent intentArticleDetail = new Intent(this, ArticleDetail.class);

        intentArticleDetail.putExtra("idBB", Integer.toString(article.getId()));
        intentArticleDetail.putExtra("tenBB", article.getName_article());
        intentArticleDetail.putExtra("ANH", article.getPathImage());
        intentArticleDetail.putExtra("DESCRIPTION", article.getDescription());
        intentArticleDetail.putExtra("URL", article.getUrl());
        startActivityForResult(intentArticleDetail, 2);
//        startActivity(intentArticleDetail);
    }


    private void doOpenAddActivity() {
        Intent intentAddArticle=new Intent(this, AddArticle.class);
        startActivityForResult(intentAddArticle, 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((resultCode == RESULT_OK) && (requestCode == 1)) {
            Intent intentRefresh = new Intent(this, ArticleManagement.class);

            startActivity(intentRefresh);
            this.finish();
        }

        if ((resultCode == RESULT_OK) && (requestCode == 2)) {
            Intent intentRefresh = new Intent(this, ArticleManagement.class);

            startActivity(intentRefresh);
            this.finish();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.danhSachBaiBao) {
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.example_menu, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if (item.getItemId() == R.id.menuDelete) {
            ArticleQuery articleQuery = new ArticleQuery(getBaseContext());
            Article article = (Article) listViewBB.getAdapter().getItem(info.position);

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