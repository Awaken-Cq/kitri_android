package com.example.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ex4_24Activity extends AppCompatActivity {    //extends Activity
    //AppCompatActivity를 상속받지않았을때는 entends Activity를 상속받아도된다.
    //다만 그랬을경우의 차이는 폰화면안에 액션바(상단바?) 표시가 되지않는 차이가 있다.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex4_24);
        //  inflation(펼친다.안드로이드에서의 의미) == 렌더링
        //layout에 해당하는 xml을 찾아 xml 요소들을 객체화하고 화면에 전개한다.

        CheckBox cb1 = (CheckBox) findViewById(R.id.cb1); // VM에 관리되는 객체중 ib가 cb1인 View타입의 객체를 찾는다.
        CheckBox cb2 = (CheckBox) findViewById(R.id.cb2);
        CheckBox cb3 = (CheckBox) findViewById(R.id.cb3);
        RadioGroup rg = (RadioGroup) findViewById(R.id.rg);
        final RadioButton male = (RadioButton) findViewById(R.id.male);
        final RadioButton female = (RadioButton) findViewById(R.id.female);
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.i("ex4_24Activity", "cb1 Checked 속성값이 변경됨 " + b + ", text는 " + compoundButton.getText());

            }
        });

        MyCheckedChangeListener myListener = new MyCheckedChangeListener();
        cb1.setOnCheckedChangeListener(myListener);
        cb2.setOnCheckedChangeListener(myListener);
        cb3.setOnCheckedChangeListener(myListener);
        RadioCheckedChangeListener radioListener = new RadioCheckedChangeListener();
        male.setOnCheckedChangeListener(radioListener);
        female.setOnCheckedChangeListener(radioListener);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.male){
                    Log.i("ex4_24Activity",i + "." + male.getText().toString());
                }else if(i == R.id.female){
                    Log.i("ex4_24Activity",i + "." + female.getText().toString());
                }


            }
        });


    }


    class MyCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {



        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            Log.i("ex4_24Activity", "cb1 Checked 속성값이 변경됨 " + b + ", text는 " + compoundButton.getText());
        }
    }

    class RadioCheckedChangeListener implements RadioButton.OnCheckedChangeListener {


        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            Log.i("ex4_24Activity","radio button 속성값이 변경됨 " + b + ", text는 " + compoundButton.getText());
        }
    }
}

//        cb2.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener(){
//
//
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                Log.i("ex4_24Activity", "cb1 Checked 속성값이 변경됨 " + b + ", text는 " + compoundButton.getText());
//
//            }
//        });

//        cb3.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener(){
//
//
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                Log.i("ex4_24Activity", "cb1 Checked 속성값이 변경됨 " + b + ", text는 " + compoundButton.getText());
//
//            }
//        }




