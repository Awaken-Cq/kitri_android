package com.example.myapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class ex10_3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex10_3);
        Log.i("ex10_3Activity", "onCreate()");

    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i("ex10_3Activity", "onStart()");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i("ex10_3Activity", "onResume()");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.i("ex10_3Activity", "onPause()");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.i("ex10_3Activity", "onStop()");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("ex10_3Activity", "onDestroy()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("ex10_3Activity", "onRestart()");
    }


}
