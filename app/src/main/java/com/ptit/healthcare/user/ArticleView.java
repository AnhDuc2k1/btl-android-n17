package com.ptit.healthcare.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ptit.healthcare.R;
import com.ptit.healthcare.entities.Article;

public class ArticleView extends AppCompatActivity {

    TextView tv;
    TextView bacsi;
    TextView huyetap;
    TextView covid;
    TextView yoga;
    TextView vitamin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_view);

//        changeScreenOnClick(tv, R.id.tv);
//        changeScreenOnClick(bacsi, R.id.bacsi);
//        changeScreenOnClick(huyetap, R.id.huyetap);
//        changeScreenOnClick(covid, R.id.covid);
//        changeScreenOnClick(yoga, R.id.yoga);
//        changeScreenOnClick(vitamin, R.id.vitamin);
    }

    private void changeScreenOnClick(TextView textView, final int textViewId) {
        textView = findViewById(textViewId);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ViewDetail.class);
                Article article = new Article();
//                if(R.id.tv == textViewId) {
//                    article.setName("Xem TV quá nhiều ở trẻ");
//                    article.setDescription("jkdscsajdjfij");
//                    article.setPathImage("tv");
//                    article.setGoToUrl("https://hellobacsi.com/");
//                }
//
//                if(R.id.bacsi == textViewId) {
//                    article.setName("Bác Sĩ khuyên gì");
//                    article.setDescription("Lnxjcknadvj id");
//                    article.setPathImage("bacsi");
//                    article.setGoToUrl("https://www.collinsdictionary.com/dictionary/english/doctor-advises");
//                }
//
//                if(R.id.huyetap == textViewId) {
//                    article.setName("Bệnh tăng huyết áp ngày càng trẻ hoá");
//                    article.setDescription("cdjcndnkcszckmdskj");
//                    article.setPathImage("huyetap");
//                    article.setGoToUrl("https://tamanhhospital.vn/tang-huyet-ap/");
//                }
//
//                if(R.id.covid == textViewId) {
//                    article.setName("Covid đang trở lại");
//                    article.setDescription("jcjdjicjdijsoadkockd");
//                    article.setPathImage("covid");
//                    article.setGoToUrl("https://www.unilever.com/news/news-search/2023/how-unilever-food-solutions-fasttracked-its-covid-comeback/");
//                }
//
//                if(R.id.yoga == textViewId) {
//                    article.setName("Tập Yoga để tịnh tâm");
//                    article.setDescription("dkcnjdjhdjiv jdiv.");
//                    article.setPathImage("yoga");
//                    article.setGoToUrl("https://www.nytimes.com/guides/well/beginner-yoga");
//                }
//
//                if(R.id.vitamin == textViewId) {
//                    article.setName("Vitamin rất tốt cho sức khoẻ");
//                    article.setDescription("dkcif ifjvifjvifiddvkkdoo ");
//                    article.setPathImage("vitamin");
//                    article.setGoToUrl("https://vi.wikipedia.org/wiki/Vitamin_C");
//                }
//                intent.putExtra("article", article);
//                startActivity(intent);
            }
        });
    }
}

