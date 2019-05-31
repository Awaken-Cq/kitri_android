package com.example.myapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class inflationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inflation);

        final LinearLayout root = (LinearLayout)findViewById(R.id.root);
        Button btCCb = (Button)findViewById(R.id.btCCb);
        Button btRCb = (Button)findViewById(R.id.btRCb);

        //부분XML전개
        LayoutInflater inflater = (LayoutInflater) getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_sub1, root, true);

        btCCb.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view) {
//                //부분XML전개
//                LayoutInflater inflater = (LayoutInflater) getSystemService(
//                        Context.LAYOUT_INFLATER_SERVICE);
//                inflater.inflate(R.layout.activity_sub1, root, true);

                //View보여주기
                findViewById(R.id.sub_1).setVisibility(View.VISIBLE);
            }

        });

        btRCb.setOnClickListener( new View.OnClickListener(){


            @Override
            public void onClick(View view) {
                //View제거
//                root.removeView(findViewById(R.id.sub_1));

                //View사라지기
                findViewById(R.id.sub_1).setVisibility(View.GONE);


            }
        });
    }
}
