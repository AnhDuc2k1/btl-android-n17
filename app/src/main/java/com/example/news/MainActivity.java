package com.example.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    TextView bacsi;
    TextView huyetap;
    TextView covid;
    TextView yoga;
    TextView vitamin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeScreenOnClick(tv, R.id.tv);
        changeScreenOnClick(bacsi, R.id.bacsi);
        changeScreenOnClick(huyetap, R.id.huyetap);
        changeScreenOnClick(covid, R.id.covid);
        changeScreenOnClick(yoga, R.id.yoga);
        changeScreenOnClick(vitamin, R.id.vitamin);
    }

    private void changeScreenOnClick(TextView textView, final int textViewId) {
        textView = findViewById(textViewId);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailActitvity.class);
                Factnews factnews = new Factnews();
                if(R.id.tv == textViewId) {
                    factnews.setName("Xem TV quá nhiều ở trẻ");
                    factnews.setDescription("jkdscsajdjfij");
                    factnews.setPathImage("tv");
                    factnews.setGotourl("https://hellobacsi.com/");
                }

                if(R.id.bacsi == textViewId) {
                    factnews.setName("Bác Sĩ khuyên gì");
                    factnews.setDescription("Lnxjcknadvj id");
                    factnews.setPathImage("bacsi");
                    factnews.setGotourl("https://www.collinsdictionary.com/dictionary/english/doctor-advises");
                }

                if(R.id.huyetap == textViewId) {
                    factnews.setName("Bệnh tăng huyết áp ngày càng trẻ hoá");
                    factnews.setDescription("cdjcndnkcszckmdskj");
                    factnews.setPathImage("huyetap");
                    factnews.setGotourl("https://tamanhhospital.vn/tang-huyet-ap/");
                }

                if(R.id.covid == textViewId) {
                    factnews.setName("Covid đang trở lại");
                    factnews.setDescription("jcjdjicjdijsoadkockd");
                    factnews.setPathImage("covid");
                    factnews.setGotourl("https://www.unilever.com/news/news-search/2023/how-unilever-food-solutions-fasttracked-its-covid-comeback/");
                }

                if(R.id.yoga == textViewId) {
                    factnews.setName("Tập Yoga để tịnh tâm");
                    factnews.setDescription("dkcnjdjhdjiv jdiv.");
                    factnews.setPathImage("yoga");
                    factnews.setGotourl("https://www.nytimes.com/guides/well/beginner-yoga");
                }

                if(R.id.vitamin == textViewId) {
                    factnews.setName("Vitamin rất tốt cho sức khoẻ");
                    factnews.setDescription("dkcif ifjvifjvifiddvkkdoo ");
                    factnews.setPathImage("vitamin");
                    factnews.setGotourl("https://vi.wikipedia.org/wiki/Vitamin_C");
                }
                intent.putExtra("factnews", factnews);
                startActivity(intent);
            }
        });
    }
}
