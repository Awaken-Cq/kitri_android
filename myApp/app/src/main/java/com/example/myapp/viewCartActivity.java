package com.example.myapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kitri.dto.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class viewCartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);

        final SharedPreferences pref =
                getApplicationContext().getSharedPreferences(
                        "sessionCookie",
                        Context.MODE_PRIVATE);

        final SharedPreferences.Editor edit = pref.edit();

        new Thread() {
            @Override
            public void run() {


                String urlStr = "http://192.168.14.50/myeljstl/viewcart";
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
                        is = con.getInputStream();
                        byte[] buf = new byte[1024];
                        byteArrayOutputStream = new ByteArrayOutputStream(buf.length);

                        StringBuffer sb = null;
                        int readlength = -1;
                        while ((readlength = is.read(buf)) != -1) {

                            byteArrayOutputStream.write(buf, 0, readlength);

                        }

                        byte[] bytedata = null;
                        bytedata = byteArrayOutputStream.toByteArray();
                        String str = new String(bytedata, 0, bytedata.length);



                        ObjectMapper mapper = new ObjectMapper();
                        JSONArray jArray = new JSONArray(str);
                        //JSONArray jArrayy = new JSONArray(str.toString());
                        //Log.i("viewCartActivity",jArray.toString());
                        //Log.i("viewCartActivity",jArrayy.toString());//두개의 결과가 같음.

                        JSONObject json = jArray.getJSONObject(0);
                        //Log.i("viewCartActivity",json.toString());
                        JSONObject product = json.getJSONObject("product");
                        int quantity = json.getInt("quantity");

                        Log.i("viewCartActivity",product.toString());
                        Log.i("viewCartActivity",quantity + "");

                        //Map<Product, Integer> map
//                        Map<Product, Integer> map = mapper.readValue(str, new TypeReference<HashMap<Product,Integer>>(){});
//                        Set<Product> keys = map.keySet();
//
//                        for(Product p : keys){
//                            Log.i("viewCartActivity",p.getProduct_no());
//                            Log.i("viewCartActivity",String.valueOf(map.get(p)));
//                        }

                        //Log.i("viewCartActivity", "");
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally{

                }


            }
        }.start();
    }
}
