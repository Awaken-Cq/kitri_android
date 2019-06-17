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

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_login);
    }

    public void login(View view) {
        Intent intent = new Intent(this,
                LoginResultActivity.class);
        final String id = ((EditText) findViewById(R.id.edId)).getText().toString();
        final String pass = ((EditText) findViewById(R.id.etPass)).getText().toString();

        //현재application에서 사용할 수 있는 공통정보가 저장될 XML파일
        final SharedPreferences pref =
                getApplicationContext().getSharedPreferences(
                        "sessionCookie"  //xml파일이름
                        , Context.MODE_PRIVATE);

        final SharedPreferences.Editor edit = pref.edit();

        new Thread() {
            public void run() {
                String urlStr = "http://192.168.14.52/myeljstl/login";
                InputStream is = null;
                try {
                    URL url = new URL(urlStr);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setDoOutput(true); //요청데이터를 출력허용
                    con.setRequestMethod("POST");

                    String jsession_cookie = pref.getString("JSESSIONID", null);
                    con.setRequestProperty("Cookie", jsession_cookie);
//                    if(jsession_cookie != null){
//                        Log.i("LoginActivity", "이미 로그인 성공된 상태");
//                        con.setRequestProperty("Cookie", jsession_cookie);//요청헤더 추가
//                    }else{
//                        Log.i("LoginActivity", "로그인 안된 상태");
//                    }

                    DataOutputStream dos =
                            new DataOutputStream(con.getOutputStream());
                    dos.writeBytes("id=" + id + "&pass=" + pass);

                    //-----------------------------------------------------


                    int responseCode = con.getResponseCode();
                    if (responseCode == 200) { //정상응답
                        //응답헤더중 cookie값 얻기
//                        String cookies = con.getHeaderField("Set-cookie");
                        List<String> cookies =
                                con.getHeaderFields().get("Set-cookie");
                        if (cookies != null) {
                            Log.i("LoginActivity" , cookies.toString());
                            for (String cookie : cookies) {
                                //JSESSIONID=4B9E346D35F9D49E6DA0E8452E82F729; Path=/myeljstl; HttpOnly
                                String cookieNameValue = cookie.split(";\\s*")[0]; //JSESSIONID=4B9E346D35F9D49E6DA0E8452E82F729
                                String cookieName = cookieNameValue.split("=")[0];
                                edit.putString(cookieName, cookieNameValue);//SharedPreference객체에 쓰기
                                edit.apply(); //xml파일에 쓰기작업을 비동기화
                            }
                        }

                        is = con.getInputStream();
                        StringBuffer sb = new StringBuffer();
                        int readValue = -1;
                        while( (readValue = is.read()) != -1){
                            sb.append((char)readValue);
                        }


                        Log.i("LoginActivity", "로그인결과:" + sb.toString());//응답결과
                        Intent intent = new Intent(LoginActivity.this, LoginResultActivity.class);
                        intent.putExtra("result", sb.toString());
                        intent.putExtra("id", id);
                        startActivity(intent);
                        finish();
//
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally{

                }
            }
        }.start();


        //intent.putExtra("id", id);
        //intent.putExtra("pass", pass);
        //startActivity(intent);
    }
}
