package com.example.myapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class exloginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exlogin);
    }

    public void login(View view) {
        Intent intent = new Intent(this,
                exloginResultActivity.class);

        final String id = ((EditText)findViewById(R.id.edId)).getText().toString();
        final String pass = ((EditText)findViewById(R.id.etPass)).getText().toString();

        //현재 application에서 사용할 수 있는 공통정보가 저장될 XML파일
        //MODE_PRIVATE 설정을 했을 경우 외부에서 접근이 불가능함. 보안효율 증가
        //sessionCookie : 공통정보가 저장될 xml 파일이름
        final SharedPreferences pref = getApplicationContext().getSharedPreferences(
                                        "sessionCookie",
                                           Context.MODE_PRIVATE);
        //XML을 쓰기위한 eidt를 얻음.
        final SharedPreferences.Editor edit = pref.edit();


        new Thread(){
            public void run(){
                String urlStr = "http://192.168.14.50/myeljstl/login";
                InputStream is = null;

                try {
                    //URL을 통한 connection
                    URL url = new URL(urlStr);
                    HttpURLConnection con = (HttpURLConnection)url.openConnection();

                    //요청데이터출력허용
                    con.setDoOutput(true);

                    //jsession아이디를 받아 비교하는 것의 의미는 로그인을 한번이라도
                    //해본사람인지 아닌지를 확인하려는 의도.
                    String jsession_cookie = pref.getString("JSESSIONID", null);
                    //con.setRequestProperty("Cookie", jsession_cookie);


                    //post방식으로 dataoutputStream을 통해 데이터 전송
                    con.setRequestMethod("POST");
                    String act ="login";
                    if(jsession_cookie != null){
                        //요청헤더설정 작업
                        Log.i("exloginActivity", "이미 로그인 된 상태");
                        con.setRequestProperty("cookie", jsession_cookie);
                    }else{
                        Log.i("exloginActivity", "아직 로그인 안된 상태");
                    }


                    DataOutputStream dos = new DataOutputStream(con.getOutputStream());
                    dos.writeBytes("act=" + act + "&id=" + id + "&pass="+pass);




                    //응답받기
                    int responseCode= con.getResponseCode();
                    if(responseCode==200){



                        //쿠키가 여러개가 전달될 경우를 대비(필요한 쿠키를 선택해야함)
                        //set-cookie : 응답의 헤더이름 중 하나
                        //String cookies = con.getHeaderField("set-cookie");
                        //응답결과



                        //쿠키처리(저장)
                        List<String> cookies =
                                con.getHeaderFields().get("Set-cookie");

                        if(cookies != null) {//로그인 성공된 경우
                            Log.i("exloginActivity", cookies.toString());
                                    for (String cookie : cookies) {
                                        //JSESSIONID=5F459D1BBB1A320F66762BA0C2724A09; Path=/myeljstl; HttpOnly
                                        //별 표시는 \\s이 한개이상 즉 공백이 하나 이상이여도 처리가능.
                                        String cookieNameVlaue = cookie.split(";\\s*")[0];
                                        String cookieName = cookieNameVlaue.split("=")[0];
                                        edit.putString(cookieName, cookieNameVlaue);
                                //쿠키가 많을 경우 반복작업(xml파일에 쓰기는 작업)이 많다보니
                                //먹통이 될 수 있기때문에 xml파일에 쓰기는 작업을 비동기화.
                                edit.apply();
                            }

                        }
                        is = con.getInputStream();
                        StringBuffer sb = new StringBuffer();
                        int readValue = -1;
                        while((readValue = is.read()) != -1){
                            sb.append((char)readValue);
                        }
                        //Log.i("exloginActivity","로그인결과 : "+ sb.toString());
                        Intent intent = new Intent(exloginActivity.this, exloginResultActivity.class);
                        intent.putExtra("result", sb.toString());
                        intent.putExtra("id", id);
                        startActivity(intent);
                        finish();
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }.start();






//        intent.putExtra("id", id);
//        intent.putExtra("pass", pass);
//
//        startActivity(intent);
    }
}
