package com.example.myapp;


import android.Manifest;
import android.media.MediaPlayer;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ex13Activity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex13);

        //sd카드 경로 접근 커미션 받기
        ActivityCompat.requestPermissions(this,
                             new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                             MODE_PRIVATE);

        //sd카드 기본경로 찾기
        //sd카드 기본 경로 : /storage/emulated/0
        final String mp3Path = Environment.getExternalStorageDirectory().getPath() + "/";

        Log.i("ex13Activity",mp3Path);

        //확장자가 .mp3인 파일 찾기
        File dir = new File(mp3Path);
        File[] files = dir.listFiles();
        final List<String> fileNames = new ArrayList<>();

        for(File file : files){
            String fileName = file.getName();
            if(fileName.endsWith(".mp3")){
            fileNames.add(fileName);
            }
        }

        //ListView에 파일목록 보여주기
        ListView lvMp3 = (ListView)findViewById(R.id.lvMp3);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,//콘텍스트
                android.R.layout.simple_list_item_single_choice,//보여줄형식
                fileNames);//보여줄 View

        lvMp3.setAdapter(adapter);
        lvMp3.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        //mp3파일용 MediaPlayer 생성
        lvMp3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                String selectedFileName = fileNames.get(index);

                mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(mp3Path + selectedFileName);
                    mediaPlayer.prepare();//리소스 준비
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });


    }

    public void play(View view) {
        mediaPlayer.start();

//        mediaPlayer.isPlaying();  //현재 실행중인지
//        mediaPlayer.getDuration();
//        mediaPlayer.getCurrentPosition(); //미디어플레이어의 현재위치값
        final ProgressBar pb = findViewById(R.id.pb);
        pb.setMax(mediaPlayer.getDuration());
        new Thread() {
            public void run() {
                //플레이중
                while (mediaPlayer.isPlaying()) {
                    Log.i("ex13Activity", "getDuration() = " + mediaPlayer.getDuration() +
                            "// getCurrentPosition = " + mediaPlayer.getCurrentPosition());
                    //미디어플레이어의 현재위치값
                    final int currentPosition = mediaPlayer.getCurrentPosition();
//                    pb.setProgress();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pb.setProgress(mediaPlayer.getCurrentPosition());

                            //progress를 숫자로 표현
                            SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
                            String progressTime = sdf.format(currentPosition);
                        }
                    });
                }
            }
        }.start();

        findViewById(R.id.pb).setVisibility(View.VISIBLE);

    }
    private boolean flag = true; // 일시정지와 이어듣기를 구분 전자는 true, 후자는 false
    public void pause(View view) {
        if(flag) {
            mediaPlayer.pause();
            //일시정지버튼이 클릭되어 음악이 일시정지된 상태에서는 "이어듣기"로 바뀜
            ((Button) view).setText("이어듣기");
        }else {
            //이어듣기가 클릭이 된 경우(start()호출) 일시정지로 바뀜.
            mediaPlayer.start();
            ((Button) view).setText("일시정지");
        }
        flag = !flag;
    }

    public void stop(View view) {
        mediaPlayer.stop();
        findViewById(R.id.pb).setVisibility(View.INVISIBLE);
    }
}
