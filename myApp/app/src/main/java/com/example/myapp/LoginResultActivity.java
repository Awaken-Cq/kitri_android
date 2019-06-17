package com.example.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class LoginResultActivity extends AppCompatActivity {
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login_result);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
//        String pass = intent.getStringExtra("pass");
        String result = intent.getStringExtra("result");
        TextView tvResult = (TextView)findViewById(R.id.tvResult);
        if(result.equals("1")) {
            tvResult.setText(id + "님 로그인 성공!");
        }else{
            tvResult.setText(id + "님 로그인 실패!");
        }
        Log.i("LoginResultActivity", "onCreate()");
    }
    public void close(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("msg", id+"님 환영합니다!");
        startActivity(intent);
        finish(); //화면사라지기 onPause->onStop
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("LoginResultActivity", "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("LoginResultActivity", "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("LoginResultActivity", "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("LoginResultActivity", "onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Ex10_3Activity", "onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Ex10_3Activity", "onDestroy()");
    }

}
