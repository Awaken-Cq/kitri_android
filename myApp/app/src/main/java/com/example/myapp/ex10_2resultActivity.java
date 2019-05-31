package com.example.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ex10_2resultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex10_2_result);

        TextView view1 = findViewById(R.id.view1);
        TextView view2 = findViewById(R.id.view2);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        float score = intent.getFloatExtra("score", 0);
        view1.setText(title);
        view2.setText(String.valueOf(score));


    }

    public void close(View view) {
        Intent intent = new Intent(this, ex10_2Activity.class);
        intent.putExtra("scoreAvg", 4.3F);
        setResult(RESULT_OK, intent);
        finish();

    }
}
