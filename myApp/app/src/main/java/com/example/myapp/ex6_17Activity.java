package com.example.myapp;

import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;

// tabhost를 사용하려면 tabActivity를 상속받아야함
//@SuppressWarnings("deprecation") deprecation의 warnings을 무시하겠다.라는 의미의 annotation
@SuppressWarnings("deprecation")
public class ex6_17Activity extends TabActivity {//extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex6_17);

        TabHost tabHost = this.getTabHost(); //탭호스트 찾기
        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec("SONG"); //탭추가
        tabSpec1.setIndicator("음악별"); //탭라벨설정
        tabSpec1.setContent(R.id.song); //탭이클릭되었을때 보여줄 위젯등록
        tabHost.addTab(tabSpec1); //탭호스트에 탭설정


        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("SINGER"); //탭추가
        tabSpec2.setIndicator("가수별"); //탭라벨설정
        tabSpec2.setContent(R.id.singer); //탭이클릭되었을때 보여줄 위젯등록
        tabHost.addTab(tabSpec2); //탭호스트에 탭설정


        TabHost.TabSpec tabSpec3 = tabHost.newTabSpec("ALBUM"); //탭추가
        tabSpec3.setIndicator("앨범별"); //탭라벨설정
        tabSpec3.setContent(R.id.album); //탭이클릭되었을때 보여줄 위젯등록
        tabHost.addTab(tabSpec3); //탭호스트에 탭설정

    }

}
