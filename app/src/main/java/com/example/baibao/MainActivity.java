package com.example.baibao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView vitamin;
    TextView bsonline;
    TextView yogaathome;
    TextView heathnow;
    TextView gannhiemmo;
    private DatabaseHelper databaseHelper;
    private TextView healthClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeScreenOnClick(vitamin, R.id.vitamin);
        changeScreenOnClick(bsonline, R.id.bsonline);
        changeScreenOnClick(yogaathome, R.id.yogaathome);
        changeScreenOnClick(heathnow, R.id.heathnow);
        changeScreenOnClick(gannhiemmo, R.id.gannhiemmo);
        databaseHelper = new DatabaseHelper(this);
    }

    private void changeScreenOnClick(TextView textView, final int textViewId) {
        textView = findViewById(textViewId);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HealthData healthData = databaseHelper.getHealthData(1);
                //Intent intent = new Intent(view.getContext(), afterClick.class);
                Intent intent = new Intent(MainActivity.this, afterClick.class);
                //Detail detail = new Detail("url anh","tiêu đề","mo tả");
                //HealthData detailnews = new HealthData()
//                if(R.id.vitamin == textViewId) {
//                    detail.setName("Bổ sung Vitamin C");
//                    detail.setDescription("aaaa");
//                    //detail.setPathImage("langbac");
//                    // System.out.println(detail.toString());
//                }
//
//                if(R.id.bsonline == textViewId) {
//                    detail.setName("Bệnh gan nhiễm mỡ");
//                    detail.setDescription("bbbbb");
//                    //detail.setPathImage("cotcohanoi");
//                }
//
//                if(R.id.yogaathome == textViewId) {
//                    detail.setName("Sức khoẻ hàng ngày");
//                    detail.setDescription("cccc");
//                    //detail.setPathImage("chuamotcot");
//                }
//
//                if(R.id.heathnow == textViewId) {
//                    detail.setName("Tập Yoga tại nhà");
//                    detail.setDescription("ddddd");
//                    //detail.setPathImage("hotay");
//                }
//
//                if(R.id.gannhiemmo == textViewId) {
//                    detail.setName("Bác Sỹ Online");
//                    detail.setDescription("eeeee");
//                    //detail.setPathImage("chuatranquoc");
//                }
                //System.out.println(detail.getName());
                //intent.putExtra("healthdata", detail);
                //intent.putExtra("healthdata", detail);
                intent.putExtra("title", healthData.getTitle());
                intent.putExtra("image", healthData.getImage());
                intent.putExtra("description", healthData.getDescription());
                //startActivity(intent);
                startActivity(intent);
            }
        });
    }
}