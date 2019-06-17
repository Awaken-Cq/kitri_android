package com.cookandroid.project14_2;

import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

/*
액티비티 라이프사이클
액티비티 시작 -> onCreate -> onStart -> onResume
액티비티 종료 ->onPause -> onStop -> onDestroy
(finish())

홈버튼눌러액티비티 사라짐 -> onPause -> onStop
태스크에서 선택하면 액티비티 나타남 -> onRestart -> onStart -> onResume

 */

public class MainActivity extends AppCompatActivity {
    ImageView ivBattery;
    EditText edtBattery;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);
        setTitle("배터리 상태 체크");

        ivBattery = (ImageView) findViewById(R.id.ivBattery);
        edtBattery = (EditText) findViewById(R.id.edtBattery);

    }

    BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (action.equals(Intent.ACTION_BATTERY_CHANGED)) {
                int remain = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                edtBattery.setText("현재 충전량 : " + remain + "\n");

                if (remain >= 90)
                    ivBattery.setImageResource(R.drawable.battery_100);
                else if (remain >= 70)
                    ivBattery.setImageResource(R.drawable.battery_80);
                else if (remain >= 50)
                    ivBattery.setImageResource(R.drawable.battery_60);
                else if (remain >= 10)
                    ivBattery.setImageResource(R.drawable.battery_20);
                else
                    ivBattery.setImageResource(R.drawable.battery_0);

                int plug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
                switch (plug) {
                    case 0:
                        edtBattery.append("전원 연결 : 안됨");
                        break;
                    case BatteryManager.BATTERY_PLUGGED_AC:
                        edtBattery.append("전원 연결 : 어댑터 연결됨");
                        break;
                    case BatteryManager.BATTERY_PLUGGED_USB:
                        edtBattery.append("전원 연결 : USB 연결됨");
                        break;
                }
            }

        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(br); // 화면에서 사라질때 브로드캐스트데이터를 수신 해제한다.
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(br, iFilter); // 화면에 나타날때 브로드캐스트데이터를 수신받는다.
    }

}
