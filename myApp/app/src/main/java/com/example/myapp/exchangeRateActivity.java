package com.example.myapp;

        import android.os.TestLooperManager;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Spinner;
        import android.widget.TextView;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;
        import org.w3c.dom.Text;

        import java.io.IOException;
        import java.io.InputStream;
        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.List;

public class exchangeRateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_rate);


        final String urlStr =
                "https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey=8OCT25hKR9TcitHeK85FNo2n5RVGILRD&searchdate=20190604&data=AP01";



        //네트워크부분은 반드시 새 thread가 일해야한다.
        new Thread() {
            public void run() {
                InputStream is = null;
                try {
                    //API 접근
                    URL url = new URL(urlStr);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    int responseCode = con.getResponseCode();//200(정상), 400, 405, ...etc
                    if (responseCode == 200) {
                        is = con.getInputStream();
                        byte[] buf = new byte[1024];
                        int readLength = -1; // 읽어온 바이트 수

                        StringBuffer sb = new StringBuffer();
                        while ((readLength = is.read(buf)) != -1) {
                            sb.append(new String(buf, 0, readLength));
                        }
                        //응답결과
                        String str = sb.toString();
                        //결과 TO json
                        JSONArray jsonArray = new JSONArray(str);
                        final StringBuffer resultStr = new StringBuffer();
                        //Log.i("exchangeRateActivity", "오늘의 환율정보 : " + jsonArray);

                        //스피너용 데이터
                        final String[] spinData = new String[jsonArray.length()];

                        //JSON데이터 처리로직
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObj = (JSONObject)jsonArray.get(i);
                            String cur_nm = jsonObj.getString("cur_nm").split(" ")[0];//국가명
                            String cur_unit = jsonObj.getString("cur_unit");//통화코드
                            String deal_bas_r = jsonObj.getString("deal_bas_r");//매매기준율
                            resultStr.append("국가명 : " +cur_nm + "\t\t통화코드 : " +cur_unit
                                    + "\t\t매매기준율 : " + deal_bas_r + "\n");

                            //스핀용 데이터 세팅
                            spinData[i] = new String(cur_nm + "\t\t"+cur_unit
                                    + "\t\t" + deal_bas_r);
                        }

                        //ui 변경
                        runOnUiThread(new Thread(){
                            @Override
                            public void run() {
                                TextView tvExchange = (TextView) findViewById(R.id.tvExchange);
                                tvExchange.setText(resultStr.toString());

                                ArrayAdapter adapter = new ArrayAdapter(exchangeRateActivity.this,
                                            android.R.layout.simple_list_item_1, spinData);
                                Spinner spinner = (Spinner)findViewById(R.id.spinner);
                                spinner.setAdapter(adapter);
                                final TextView spinnerResult = (TextView) findViewById(R.id.spinnerResult);

                                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        String cur_nm = String.valueOf(findViewById(view.getId())).split(" ")[0];//국가명

                                        String cur_unit = String.valueOf(findViewById(view.getId())).split(" ")[1];//통화코드
                                        String deal_bas_r = String.valueOf(findViewById(view.getId())).split(" ")[2];//매매기준율
                                        spinnerResult.setText("국가명 : " +cur_nm + "\t\t통화코드 : " +cur_unit
                                                + "\t\t매매기준율 : " + deal_bas_r);

                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                    }
                                });

                            }
                        });


                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
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
