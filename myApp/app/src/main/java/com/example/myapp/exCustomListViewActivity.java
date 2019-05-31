package com.example.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.kitri.dto.Product;

import java.util.ArrayList;
import java.util.List;

public class exCustomListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex_custom_list_view);

        ArrayList<Product> data = new ArrayList<>();
        Product p = new Product();
        p.setProd_no("001");
        p.setProd_name("Americano");
        p.setProd_price(2500);
        p.setProd_detail("커피의 기본은 아메리카노");
        data.add(p);
        p = new Product();
        p.setProd_no("002");
        p.setProd_name("Flatwhite");
        p.setProd_price(3000);
        p.setProd_detail("나의 인생커피 플렛화이트");
        data.add(p);
        p = new Product();
        p.setProd_no("003");
        p.setProd_name("cappuccino");
        p.setProd_price(3000);
        p.setProd_detail("카푸치노 스펠링이?");
        data.add(p);
        p = new Product();
        p.setProd_no("004");
        p.setProd_name("Bloodmoon");
        p.setProd_price(5000);
        p.setProd_detail("이 카페의 시그니쳐 블러드문");
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


    }
}
