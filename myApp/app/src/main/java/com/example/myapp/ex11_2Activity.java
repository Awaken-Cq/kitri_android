package com.example.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ex11_2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex11_2);

        ListView lv = (ListView)findViewById(R.id.lv);
        final String[] arr = {"아메리카노", "좋아좋아좋아", "아이스아메리카노", "싫어싫어싫어"};
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_single_choice,
                arr);

        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv.setAdapter(adapter);

        //lv.setItemChecked(1,true);

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView,
                                           View view,
                                           int index,
                                           long l) {

                Toast.makeText(ex11_2Activity.this,
                                        arr[index],
                                        Toast.LENGTH_LONG).show();
                adapter.notifyDataSetChanged();
            return false;
            }
        });
    }
}
