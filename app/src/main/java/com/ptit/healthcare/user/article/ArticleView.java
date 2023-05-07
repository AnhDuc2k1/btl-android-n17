package com.ptit.healthcare.user.article;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ptit.healthcare.R;
import com.ptit.healthcare.adapter.ListArticleAdapter;
import com.ptit.healthcare.database.ArticleQuery;
import com.ptit.healthcare.entities.Article;

import java.util.List;

public class ArticleView extends AppCompatActivity {


    ListView dsBaiBao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_view);

        dsBaiBao=findViewById(R.id.userDanhSachBaiBao);

        loadListBaiBao();
    }

    private void loadListBaiBao() {

        ArticleQuery articleQuery=new ArticleQuery(getBaseContext());
        List<Article> articleList = articleQuery.getAll();

        ListArticleAdapter listArticleAdapter = new ListArticleAdapter(this, articleList);

        dsBaiBao.setAdapter(listArticleAdapter);
        dsBaiBao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                doOpenChildActivity(articleList.get(position));
            }
        });
    }

    private void doOpenChildActivity(Article article) {
        Intent intent= ArticleView.this.getIntent();

        Intent articleInfoIntent = new Intent(this, ArticleInfo.class);
        articleInfoIntent.putExtra("userId", intent.getStringExtra("userId"));
        articleInfoIntent.putExtra("username", intent.getStringExtra("username"));
        articleInfoIntent.putExtra("idBB", String.valueOf(article.getId()));
        articleInfoIntent.putExtra("tenBB", article.getName_article());
        articleInfoIntent.putExtra("ANH", article.getPathImage());
        articleInfoIntent.putExtra("DESCRIPTION", article.getDescription());
        articleInfoIntent.putExtra("URL", article.getUrl());
        startActivity(articleInfoIntent);
    }

}