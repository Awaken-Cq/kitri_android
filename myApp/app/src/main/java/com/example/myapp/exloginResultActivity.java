package com.example.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class exloginResultActivity extends AppCompatActivity {
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exlogin_result);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        String pass = intent.getStringExtra("pass");

        TextView tvResult = findViewById(R.id.tvResult);
        tvResult.setText("Login Succeeded\n"+"welcome to login " + id);
    }

    public void close(View view) {
        //메인화면 띄우기
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("msg", id+"님 환영합니다.");
        startActivity(intent);

        finish();
    }
}
