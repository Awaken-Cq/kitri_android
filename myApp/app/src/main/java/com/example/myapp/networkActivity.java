package com.example.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kitri.dto.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class networkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        new Thread() {
            public void run() {


                String urlStr = "http://192.168.14.50/androidweb/index.jsp";
                InputStream is = null;
                ByteArrayOutputStream byteArrayOutputStream = null;


                try {
                           // 1) GET 방식 요청 --------------------------------------------------------
                    /*
                    urlStr += "?opt=add";
                    URL url = new URL(urlStr);  // url 요청
                    HttpURLConnection con = (HttpURLConnection) url.openConnection(); // 응답
                    con.setRequestMethod("GET"); // 응답결과 입력스트림
                    // ------------------------------------------------------------------------
                    */

                    // 2) POST 방식 요청 -------------------------------------------------------
                    URL url = new URL(urlStr);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();//응답
                    con.setRequestMethod("POST");//
                    con.setDoOutput(true); //요청 전달데이터를 message body에 쓰기허용
                    DataOutputStream dos =
                            new DataOutputStream(con.getOutputStream());
                    dos.writeBytes("opt=add");//message body에 쓰기
                    // ------------------------------------------------------------------------

                    is = con.getInputStream();   //응답결과 입력스트림
                    byte[] buf = new byte[1024]; // 1kb 짜리 바이트 배열 생성

                    byteArrayOutputStream = new ByteArrayOutputStream(buf.length);

                    int readLength = -1;
                    while ((readLength=is.read(buf)) != -1) {   // 서버가 보낸 값을 최대 1kb만큼 읽어옴
                        //String str = new String(buf, 0, readLength);
                        //Log.i("NetworkActivity", "서버가 보내 준 응답결과 : " + str);
                        //JSONObject jsonObj = new JSONObject();

                        byteArrayOutputStream.write(buf, 0, readLength);

                    }
                    //받은 데이터 String으로 변환
                    byte[] byteData = null;
                    byteData = byteArrayOutputStream.toByteArray();
                    String str = new String(byteData, 0, byteData.length);

                    //String -> Json으로 재생성
//                    JSONObject jsonObj = new JSONObject(str);
//                    int status = jsonObj.getInt("status");
//                    String msg = jsonObj.getString("msg");
//                    Log.i("networkActivity", "서버가 보내준 [status] : " + status);
//                    Log.i("networkActivity", "서버가 보내준 [msg] : " + msg);
//                    Log.i("networkActivity", "서버가 보내준 [method] :" + jsonObj.getString("method"));
//                    Log.i("networkActivity", "서버가 보내준 [opt] :" + jsonObj.getString("opt"));

                    //배열형태의 문자열을 JSONArray()로 받기
//                    JSONArray jsonArr = new JSONArray(str);
//                    Log.i("NetworkActivity", "서버가 보내준 상품종류 : " + jsonArr.length());
//                    for(int i=0;i<jsonArr.length();i++){
//                        JSONObject jsonObj = (JSONObject)jsonArr.get(i);
//                        Log.i("NetworkActivity", i + "상품번호 : " + jsonObj.getString("prod_no") +
//                                ", " + i + "상품이름 : " + jsonObj.getString("prod_name") +
//                                ", " + i + "상품가격 : " + jsonObj.getInt("prod_price"));
//
//                    }

                    //json library jackson을 사용한 json활용
                    ObjectMapper mapper = new ObjectMapper();
                    Log.i("fwef", str);
                    //Product를 저장하는 List로 바꿈
                    List<Product> list = Arrays.asList(
                            //JSONArray -> Product 배열
                            mapper.readValue(str, Product[].class));

                    Log.i("NetworkActivity", "서버가 보내준 상품종류 : " + list.size());
                    for (int i = 0; i < list.size(); i++) {
                        Product product = list.get(i);
                        Log.i("NetworkActivity", i + "상품번호 : " + product.getProduct_no() +
                                ", " + i + "상품이름 : " + product.getProduct_name() +
                                ", " + i + "상품가격 : " + product.getProduct_price());

                    }

                    //json library jackson을 사용한 json활용
//                    ObjectMapper mapper = new ObjectMapper();
//                    Log.i("fwef", str);
//                    List<Product> list = Arrays.asList(mapper.readValue(str, Product[].class));
//
//                    Log.i("NetworkActivity", "서버가 보내준 상품종류 : " + list.size());
//                    for (int i = 0; i < list.size(); i++) {
//                        Product product = list.get(i);
//                        Log.i("NetworkActivity", i + "상품번호 : " + product.getProd_no() +
//                                ", " + i + "상품이름 : " + product.getProd_name() +
//                                ", " + i + "상품가격 : " + product.getProd_price());
//
//                    }




                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (JsonParseException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (JsonMappingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (is != null) {


                        try {
                            is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                }

            }


        }.start();
    }
}

