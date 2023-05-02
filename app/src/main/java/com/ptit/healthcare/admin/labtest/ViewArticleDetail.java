package com.ptit.healthcare.admin.labtest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.ptit.healthcare.R;
import com.ptit.healthcare.entities.Article;


public class ViewArticleDetail extends AppCompatActivity {

    TextView name;
    TextView description;
    ImageView imageView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.button);

        final Article article = (Article) getIntent().getSerializableExtra("article");
        name.setText(article.getName());
        description.setText(article.getDescription());
        imageView.setImageResource(getResources().getIdentifier(article.getPathImage(), "drawable", getPackageName()));


        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Intent viewIntent=new Intent("android.intent.action.VIEW", Uri.parse("http://www.stackoverflow.com/"));
                //Uri uri = Uri.parse("http://www.google.com");
                Intent viewIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.getGoToUrl()));
                //Intent viewIntent=new Intent("android.intent.action.VIEW", gmmIntentUri);
                viewIntent.setPackage("com.android.chrome");
                startActivity(viewIntent);

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
