package com.example.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class exloginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exlogin);
    }

    public void login(View view) {
        Intent intent = new Intent(this,
                exloginResultActivity.class);

        String id = ((EditText)findViewById(R.id.edId)).getText().toString();
        String pass = ((EditText)findViewById(R.id.etPass)).getText().toString();


        intent.putExtra("id", id);
        intent.putExtra("pass", pass);

        startActivity(intent);
    }
}
