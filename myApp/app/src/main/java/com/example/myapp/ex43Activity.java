package com.example.myapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ex43Activity extends AppCompatActivity {

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex43);
    }

    public void btClick(View view) {
        EditText input1 = (EditText)findViewById(R.id.input1);
        EditText input2 = (EditText)findViewById(R.id.input2);

        if (input1.getText().toString().length()==0  || input2.getText().toString().equals("")) {
            Toast.makeText(ex43Activity.this, "값을 정확히 입력해주세요.", Toast.LENGTH_LONG).show();
            return;
        }
            int num1 = Integer.parseInt(input1.getText().toString());
            int num2 = Integer.parseInt(input2.getText().toString());
            Context context = ex43Activity.this;
            //Toast.makeText(context,num1,Toast.LENGTH_LONG).show();
            //Toast.makeText(ex43Activity.this,input2.getText().toString(),Toast.LENGTH_LONG).show();

            int id = view.getId();
            if (id == R.id.btnadd) {
                String sum = String.valueOf(num1 + num2);
                Toast.makeText(context, "결과 : " + sum, Toast.LENGTH_LONG).show();

            } else if (id == R.id.btnsub) {
                String sum = String.valueOf(num1 - num2);
                Toast.makeText(context, "결과 : " + sum, Toast.LENGTH_LONG).show();

            } else if (id == R.id.btnmul) {
                String sum = String.valueOf(num1 * num2);
                Toast.makeText(context, "결과 : " + sum, Toast.LENGTH_LONG).show();

            } else if (id == R.id.btndiv) {
                String sum = String.valueOf(num1 / num2);
                Toast.makeText(context, "결과 : " + sum, Toast.LENGTH_LONG).show();

            } else if (id == R.id.btnremain) {
                String sum = String.valueOf(num1 % num2);
                Toast.makeText(context, "결과 : " + sum, Toast.LENGTH_LONG).show();
            }

            input1.setText("");
            input2.setText("");

    }
}