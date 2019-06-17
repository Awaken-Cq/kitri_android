package com.example.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.kitri.dto.Product;

import java.util.ArrayList;
import java.util.List;

public class exCustomListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex_custom_list_view);

        final ArrayList<Product> data = new ArrayList<>();
        Product p = new Product();
        p.setProduct_no("001");
        p.setProduct_name("Americano");
        p.setProduct_price(2500);
        p.setProduct_detail("커피의 기본은 아메리카노");
        data.add(p);
        p = new Product();
        p.setProduct_no("002");
        p.setProduct_name("Flatwhite");
        p.setProduct_price(3000);
        p.setProduct_detail("나의 인생커피 플렛화이트");
        data.add(p);
        p = new Product();
        p.setProduct_no("003");
        p.setProduct_name("cappuccino");
        p.setProduct_price(3000);
        p.setProduct_detail("카푸치노 스펠링이?");
        data.add(p);
        p = new Product();
        p.setProduct_no("004");
        p.setProduct_name("Bloodmoon");
        p.setProduct_price(5000);
        p.setProduct_detail("이 카페의 시그니쳐 블러드문");
        data.add(p);

        //
        LinearLayout layout = (LinearLayout)findViewById(R.id.list);
        //
        MyAdapter adapter = new MyAdapter(this, data);
        //
        MyListView view = new MyListView(this);
        //
        view.setAdapter(adapter);
        //
        layout.addView(view);

        view.setOnItemLongClickListener( new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int index, long l) {

                Toast.makeText(exCustomListViewActivity.this,
                        data.get(index). getProduct_name(),
                        Toast.LENGTH_LONG).show();

                Intent intent = new Intent(exCustomListViewActivity.this,
                        productInfoActivity.class);

                //putExtra(대상 - 직렬화 가능한 객체, 기본형 데이터)
                //product.java의 implements Serializable 부분과 연관.
                intent.putExtra("productInfo", data.get(index));
                startActivity(intent);

                return false;


            }
        });


    }
}
