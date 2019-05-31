package com.example.myapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ex42Activity extends AppCompatActivity {
    int clickedCnt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex42);
        Button bt1 = (Button) findViewById(R.id.bt1);
//        //지역변수는 무명class 내에서 값을 변경할 수 없다.
//        //final int clickedCnt = 0;
//        bt1.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view) {
//                clickedCnt++;
//                String text = String.valueOf(clickedCnt); // clickedCnt+""
//                Context context = ex42Activity.this;
//                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
//                Button bt2 = (Button)findViewById(R.id.bt2);
//                if(bt2.getVisibility()== View.VISIBLE)
//                bt2.setVisibility(View.GONE);
//                else
//                bt2.setVisibility(View.VISIBLE);
//
//            }
//        });
    }

    public void btClick(View view) {
        int viewId =  view.getId();
        Log.i("EX42Activity", "지금클릭한 뷰객체의 id는 " + viewId);
        if(viewId == R.id.bt1){
            Log.i("EX42Activity", "지금클릭한 뷰객체의 id값이 bt1입니다.");
        }else if(viewId == R.id.bt2){
            Log.i("EX42Activity", "지금클릭한 뷰객체의 id값이 bt2입니다.");
        }else if (viewId == R.id.bt3) {
            Log.i("EX42Activity", "지금클릭한 뷰객체의 id값이 bt3입니다.");
        }
        clickedCnt++;
        String text = String.valueOf(clickedCnt); // clickedCnt+""
        Context context = ex42Activity.this;
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        Button bt2 = (Button) findViewById(R.id.bt2);
        if (bt2.getVisibility() == View.VISIBLE)
            bt2.setVisibility(View.GONE);
        else
            bt2.setVisibility(View.VISIBLE);


    }
}
