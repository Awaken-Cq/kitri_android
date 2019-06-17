package com.example.myapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.kitri.dto.Product;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class productInfoActivity extends AppCompatActivity {
    NumberPicker count = null;
    //EditText count = null;
    //    TextView tvProd_no
//    TextView tvProd_name
//    TextView tvProd_price
    Product product = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productinfo);

        TextView tvProd_no = (TextView) findViewById(R.id.tvProd_no);
        TextView tvProd_name = (TextView) findViewById(R.id.tvProd_name);
        TextView tvProd_price = (TextView) findViewById(R.id.tvProd_price);

        Intent intent = getIntent();
        product = (Product) intent.getExtras().get("productInfo"); // getExtras() 는 번들값을 반환.
        tvProd_no.setText(tvProd_no.getText() + product.getProduct_no());
        tvProd_name.setText(tvProd_name.getText() + product.getProduct_name());
        tvProd_price.setText(tvProd_price.getText() + String.valueOf(product.getProduct_price()));


        count = (NumberPicker) findViewById(R.id.count);
        count.setMaxValue(10);
        count.setMinValue(1);
        count.setWrapSelectorWheel(true);


    }

    public void addcart(View view) {
        Intent intent = new Intent(this, networkCustomListViewActivity.class);
        //final int cnt = Integer.parseInt(String.valueOf(count.getText()));
        final int cnt = count.getValue();
        //intent.putExtra("product", product);
        //intent.putExtra("cnt", cnt);

        final SharedPreferences pref =
                getApplicationContext().getSharedPreferences(
                                        "sessionCookie",
                                        Context.MODE_PRIVATE);

        final SharedPreferences.Editor edit = pref.edit();

        new Thread() {
            @Override
            public void run() {


                String urlStr = "http://192.168.14.50/myeljstl/addcart";
                urlStr += "?no=" + product.getProduct_no() + "&cnt=" + cnt;
                InputStream is = null;
                ByteArrayOutputStream byteArrayOutputStream= null;




                try {
                    //get방식으로 요청.
                    URL url = new URL(urlStr);
                    HttpURLConnection con = (HttpURLConnection)url.openConnection();
                    con.setRequestMethod("GET");

                    //요청헤더에 쿠키추가
                    String jsession_cookie = pref.getString("JSESSIONID", null);
                    if(jsession_cookie != null){
                        con.setRequestProperty("Cookie", jsession_cookie);
                    }

                    //응답얻기
                    int responseCode = con.getResponseCode();
                    if (responseCode == 200) { //정상응답 200 == HttpURLConnection.HTTP_OK
                        //응답헤더중 cookie값 얻기
//                        String cookies = con.getHeaderField("Set-cookie");
                        List<String> cookies =
                                con.getHeaderFields().get("Set-cookie");
                        if (cookies != null) {
                            Log.i("LoginActivity", cookies.toString());
                            for (String cookie : cookies) {
                                //JSESSIONID=4B9E346D35F9D49E6DA0E8452E82F729; Path=/myeljstl; HttpOnly
                                String cookieNameValue = cookie.split(";\\s*")[0]; //JSESSIONID=4B9E346D35F9D49E6DA0E8452E82F729
                                String cookieName = cookieNameValue.split("=")[0];
                                edit.putString(cookieName, cookieNameValue);//SharedPreference객체에 쓰기
                                edit.apply(); //xml파일에 쓰기작업을 비동기화
                            }
                        }


                        //응답결과 입력스트림
//                        is = con.getInputStream();
//                        byte[] buf = new byte[1024];
//                        byteArrayOutputStream = new ByteArrayOutputStream(buf.length);
//
//                        StringBuffer sb = null;
//                        int readlength = -1;
//                        while ((readlength = is.read(buf)) != -1) {
//
//                            byteArrayOutputStream.write(buf, 0, readlength);
//
//                        }
//
//                        byte[] bytedata = null;
//                        bytedata = byteArrayOutputStream.toByteArray();
//                        String str = new String(bytedata, 0, bytedata.length);
//
//                        Log.i("productInfoActivity", str);



                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(productInfoActivity.this,
                                    "장바구니 넣기 성공",
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                finish();

                    }else{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(productInfoActivity.this,
                                        "장바구니 넣기 실패",
                                        Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally{

                }


            }
        }.start();


        startActivity(intent);


    }
}


