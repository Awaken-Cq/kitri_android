package com.example.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ex52Activity extends AppCompatActivity {
    EditText input1;
    EditText input2;
    TextView resultText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex52);


        resultText = (TextView) findViewById(R.id.result);
    }

    // if("" || "0"
    //  ""+num
    //  beforetext+num

    public void btClick(View view) {
        if(getCurrentFocus() == null){
            return;
        }



        int focus = getCurrentFocus().getId();

        EditText input = (EditText)findViewById(focus);
        Log.i("ex52Activity", String.valueOf(focus));

        int viewId = view.getId();
        if (viewId == R.id.n0) {
            input.append("0");
        } else if (viewId == R.id.n1) {
            input.append("1");
        } else if (viewId == R.id.n2) {
            input.append("2");
        } else if (viewId == R.id.n3) {
            input.append("3");
        } else if (viewId == R.id.n4) {
            input.append("4");
        } else if (viewId == R.id.n5) {
            input.append("5");
        } else if (viewId == R.id.n6) {
            input.append("6");
        } else if (viewId == R.id.n7) {
            input.append("7");
        } else if (viewId == R.id.n8) {
            input.append("8");
        } else if (viewId == R.id.n9) {
            input.append("9");
        } else{
            resultText.setText("계산 결과 : ");

            input1 = (EditText)findViewById(R.id.input1);
            input2 = (EditText)findViewById(R.id.input2);
            if(String.valueOf(input1.getText()).trim().equals("") || String.valueOf(input2.getText()).trim().equals("")) {
                Toast.makeText(this, "값을 입력하시오", Toast.LENGTH_SHORT).show();
                return;
            }else {
                if (viewId == R.id.plus) {
                    int result =
                            Integer.parseInt(String.valueOf(input1.getText()))
                                    + Integer.parseInt(String.valueOf(input2.getText()));
                    resultText.append(String.valueOf(result));
                } else if (viewId == R.id.minus) {
                    int result =
                            Integer.parseInt(String.valueOf(input1.getText()))
                                    - Integer.parseInt(String.valueOf(input2.getText()));
                    resultText.append(String.valueOf(result));
                } else if (viewId == R.id.multi) {
                    int result =
                            Integer.parseInt(String.valueOf(input1.getText()))
                                    * Integer.parseInt(String.valueOf(input2.getText()));
                    resultText.append(String.valueOf(result));
                } else if (viewId == R.id.divide) {
                    int result =
                            Integer.parseInt(String.valueOf(input1.getText()))
                                    / Integer.parseInt(String.valueOf(input2.getText()));
                    resultText.append(String.valueOf(result));
                }

            }
        }
    }
}
