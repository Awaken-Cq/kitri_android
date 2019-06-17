package com.example.myapp;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class seekBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_bar);
    }

    public void init(View view) {
        SeekBar sb = (SeekBar) findViewById(R.id.sb);
        sb.setProgress(1);

    }

    public void progress(View view) {
        final SeekBar sb = (SeekBar) findViewById(R.id.sb);
        final TextView tvResult = (TextView)findViewById(R.id.tvResult);
        new Thread() {
            public void run() {


                int start = sb.getProgress();
                for (int i = start; i <= 100; i++) {
                    sb.setProgress(i);

                    //tvResult.setText("진행률 : " + i + "%");
                    SystemClock.sleep(100);
                    runOnUiThread(new Runnable(){

                        @Override
                        public void run() {
                            tvResult.setText("현재진행률 : " + sb.getProgress());
                        }
                    });
                }


            }
        }.start();


    }

}
