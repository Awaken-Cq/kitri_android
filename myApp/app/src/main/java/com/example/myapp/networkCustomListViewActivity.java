package com.example.myapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kitri.dto.Product;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class networkCustomListViewActivity extends AppCompatActivity {
        private ArrayList<Product> data = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_custom_list_view);

        data = new ArrayList<>();

        LinearLayout layout = (LinearLayout)findViewById(R.id.jsonlist);
        //
        //final MyAdapter adapter = new MyAdapter(this, data);

        //
        final MyListView view = new MyListView(this);
        //
        //view.setAdapter(adapter);
        //
        layout.addView(view);

        view.setOnItemLongClickListener( new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int index, long l) {

                Toast.makeText(networkCustomListViewActivity.this,
                        data.get(index). getProduct_name(),
                        Toast.LENGTH_LONG).show();

                Intent intent = new Intent(networkCustomListViewActivity.this,
                        productInfoActivity.class);

                //putExtra(대상 - 직렬화 가능한 객체, 기본형 데이터)
                //product.java의 implements Serializable 부분과 연관.
                intent.putExtra("productInfo", data.get(index));
                startActivity(intent);

                return false;


            }
        });

        new Thread() {
            public void run() {
                String urlStr = "http://192.168.14.50/myeljstl/productlistjson";
                InputStream is = null;
                ByteArrayOutputStream byteArrayOutputStream = null;


                try {

                    URL url = new URL(urlStr);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");
                    // 요청 전달데이터를 message body에 쓰기허용.
                    con.setDoOutput(true);
                    DataOutputStream dos = new DataOutputStream(con.getOutputStream());
                    dos.writeBytes("opt=add");
                    //응답결과 입력스트림
                    is = con.getInputStream();
                    byte[] buf = new byte[1024];

                    byteArrayOutputStream = new ByteArrayOutputStream(buf.length);


                    //원리 이해할것.
                    int readLength = -1;
                    while ((readLength = is.read(buf)) != -1) {

                        byteArrayOutputStream.write(buf, 0, readLength);

                        //받은 데이터 String으로 변환
                        byte[] byteData = null;
                        byteData = byteArrayOutputStream.toByteArray();
                        //응답내용 문자열
                        String str = new String(byteData, 0, byteData.length);


                        //-------------------

                        //JACKSON API : JSONObject와 DTO 간의 매핑
                        ObjectMapper mapper = new ObjectMapper();
                        Log.i("list", str);

                        //readvalue();
                        //응답내용문자열의 구성이 JSONArray포맷이면
                        //DTO클래스 배열타입으로 매핑
                        // 방법 1)
                        // List<Product> list = Arrays.asList(mapper.readValue(str, Product[].class));
                        // Log.i("NetworkActivity", "서버가 보내준 상품종류 : " + list.size());
//                        for (int i = 0; i < list.size(); i++) {
//                            Product product = list.get(i);
//
//                            data.add(product);

//                           }

                        // 방법 2)
                        data = mapper.readValue(str, new TypeReference<ArrayList<Product>>(){});









                    }


                    final MyAdapter adapter = new MyAdapter(networkCustomListViewActivity.this, data);




                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //adapter.notifyDataSetChanged();
                            view.setAdapter(adapter);
                        }
                    });

                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
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
