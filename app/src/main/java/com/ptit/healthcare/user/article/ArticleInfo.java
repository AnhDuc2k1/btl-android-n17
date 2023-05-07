package com.ptit.healthcare.user.article;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ptit.healthcare.R;

public class ArticleInfo extends AppCompatActivity {

    TextView tvTenBaiBao, tvNoiDung;
    Button xemChiTiet;

    ImageView ivAnh;
    String urlString, imageString;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_info);
        tvTenBaiBao=findViewById(R.id.infoName);
        tvNoiDung=findViewById(R.id.infoDescription);
        xemChiTiet = findViewById(R.id.infoUrl);
        ivAnh=findViewById(R.id.infoImage);

        Intent intent=ArticleInfo.this.getIntent();

        tvTenBaiBao.setText(intent.getStringExtra("tenBB"));
        tvNoiDung.setText(intent.getStringExtra("DESCRIPTION"));
        urlString=intent.getStringExtra("URL");
        imageString=intent.getStringExtra("ANH");
        System.out.print(imageString);
        Glide.with(this).load(imageString).into(ivAnh);

        xemChiTiet.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent viewIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
                viewIntent.setPackage("com.android.chrome");
                startActivity(viewIntent);
            }
        });
    }
}