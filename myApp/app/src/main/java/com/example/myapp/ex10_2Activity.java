package com.example.myapp;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class ex10_2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex10_2);

        Button btScore = (Button)findViewById(R.id.btScore);

        btScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RatingBar ratingBar = (RatingBar)findViewById(R.id.starScore);
                float rating = ratingBar.getRating();
                Toast.makeText(ex10_2Activity.this
                        ,"별점="+rating
                        , Toast.LENGTH_LONG).show();
                String title = "센과 치히로의 행방불명";
                //intent.putextra로 전달할 데이터
                //title : 영화제목 intent.putExtra("title",rating);
                //score : 별점 intent.putExtra("score",rating);
               Intent intent = new Intent(ex10_2Activity.this, ex10_2resultActivity.class);
               intent.putExtra("title", title);
               intent.putExtra("score",rating);
               startActivityForResult(intent,0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK){
            Float scoreAvg = data.getFloatExtra("scoreAvg",0);
            Toast.makeText(this
                    , "영화평점은 " + scoreAvg + "입니다."
                    , Toast.LENGTH_LONG).show();

        }
    }

    public void close(View view) {
        finish();

    }
}
