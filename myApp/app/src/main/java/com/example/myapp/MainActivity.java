package com.example.myapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
//public class MainActivity extends Activity

    private Map<Integer, Class> activityMap = new HashMap<>();

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt1 = (Button)findViewById(R.id.bt1);
        //String btnContent = bt1.getText().toString();
        //System.out.println("버튼 텍스트 : " + btnContent);
        String tag = "My App MainActivity";
        //String message = btnContent;
        //Log.d(tag, message);
        //Log.e(tag, message);
        Context ctx = this;
        //String text = btnContent;
        //Toast.makeText(ctx, text, Toast.LENGTH_LONG).show();

        activityMap.put(R.id.bt_ex42, ex42Activity.class);
        activityMap.put(R.id.bt_ex43, ex43Activity.class);
        activityMap.put(R.id.bt_ex51, ex51Activity.class);
        activityMap.put(R.id.bt_ex4_24, ex4_24Activity.class);
        activityMap.put(R.id.bt_inflation, inflationActivity.class);
        activityMap.put(R.id.bt_newActivity, newActivity.class);
        activityMap.put(R.id.bt_ex10_2, ex10_2Activity.class);
        activityMap.put(R.id.bt_ex10_3, ex10_3Activity.class);
        activityMap.put(R.id.exlogin, exloginActivity.class);
        activityMap.put(R.id.otherapp, CallComponentActivity.class);
        activityMap.put(R.id.btlv,ex11_2Activity.class);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        String msg = intent.getStringExtra("msg");
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    public void btClick(View view) {

        Class clazz = activityMap.get(view.getId());
        intent = new Intent(this, clazz);
        startActivity(intent);
//        switch(view.getId()){
//            case R.id.bt_ex42:
//                intent = new Intent(this, ex42Activity.class);
//                startActivity(intent); // 새화면 띄우기
//                break;
//
//            case R.id.bt_ex43:
//                intent = new Intent(this, ex43Activity.class);
//                startActivity(intent);
//                break;
//
//            case R.id.bt_ex51:
//                intent = new Intent(this, ex51Activity.class);
//                startActivity(intent);
//                break;
//
//            case R.id.bt_ex4_24:
//                intent = new Intent(this, ex4_24Activity.class);
//                startActivity(intent);
//                break;
//
//            case R.id.bt_inflation:
//                intent = new Intent(this, inflationActivity.class);
//                startActivity(intent);
//                break;
//
//
//        }

    }
}
