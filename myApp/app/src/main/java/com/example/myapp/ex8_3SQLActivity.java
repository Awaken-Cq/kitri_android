package com.example.myapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class ex8_3SQLActivity extends AppCompatActivity {

    private DatePicker dp;
    private EditText et_content;
    private Button btn;
    private String diary_dt;
    private String content;


    //이너클래스로 DB 생성?연결?
    class DiarySQLiteOpenHelper extends SQLiteOpenHelper{
        public DiarySQLiteOpenHelper(Context context,
                                     String name) {
            super(context, name, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            //테이블 생성
            String createSQL = "CREATE TABLE IF NOT EXISTS DIARY( \r\n" +
                    "diary_dt char(10), \r\n" +
                    "diary_content varchar2(400), \r\n" +
                    "CONSTRAINT diary_dt_pk PRIMARY KEY (diary_dt))";//diary_dt '2019/06/10'
            sqLiteDatabase.execSQL(createSQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            //테이블 제거
            String dropSQL = "DROP TABLE IF EXISTS DIARY";
            sqLiteDatabase.execSQL(dropSQL);
        }




    }
    private DiarySQLiteOpenHelper diaryHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex8_3_sql);
        diaryHelper = new DiarySQLiteOpenHelper(this, "testDB");
        diaryHelper.onCreate(diaryHelper.getWritableDatabase());

        setTitle("간단 일기장");
        dp = (DatePicker) findViewById(R.id.datePicker1);
        et_content  = (EditText)findViewById(R.id.et_content);
        btn = (Button)findViewById(R.id.bt_write);

        Calendar cal = Calendar.getInstance();
        int cYear = cal.get(Calendar.YEAR);
        int cMonth = cal.get(Calendar.MONTH);
        int cDay = cal.get(Calendar.DAY_OF_MONTH);

        diary_dt = Integer.toString(cYear) + "/"
                + ((cMonth+1)/10 == 0 ?  "0"+Integer.toString(cMonth + 1) : Integer.toString(cMonth + 1)) + "/"
                + ((cDay)/10 > 0 ? Integer.toString(cDay) :  "0"+Integer.toString(cDay));
         //String a = ((cMonth+1)/10 != 0 ?  "0"+Integer.toString(cMonth + 1) : Integer.toString(cMonth + 1));
        selectFromSql(diary_dt);

        dp.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            public void onDateChanged(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                diary_dt = Integer.toString(year) + "/"
                        //+ Integer.toString(monthOfYear + 1) + "/"
                        + ((monthOfYear+1)/10 == 0 ?  "0"+Integer.toString(monthOfYear + 1) : Integer.toString(monthOfYear + 1)) + "/"
                        + ((dayOfMonth)/10 > 0 ? Integer.toString(dayOfMonth) :  "0"+Integer.toString(dayOfMonth));

                et_content.setText("");
                btn.setEnabled(false);
                selectFromSql(diary_dt);
            }

        });



        et_content.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {

                content = et_content.getText().toString();

                if (content == null || content.trim().equals("")) {
                    btn.setEnabled(false);
                } else {
                    btn.setEnabled(true);
                }
                return false;
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase sqLiteDatabase = diaryHelper.getWritableDatabase();
                content = ((EditText)findViewById(R.id.et_content)).getText().toString();

                String insertSQL = "INSERT OR REPLACE INTO DIARY VALUES(" +
                        "'"+ diary_dt +"', '"+ content +"')";
                sqLiteDatabase.execSQL(insertSQL);
                Toast.makeText(ex8_3SQLActivity.this, "일기가 저장되었습니다.", Toast.LENGTH_SHORT).show();
                sqLiteDatabase.close();
                btn.setText("일기 수정");
            }
        });

    }


//    public void insertToSql(View view){
//        SQLiteDatabase sqLiteDatabase = diaryHelper.getWritableDatabase();
//        content = ((EditText)findViewById(R.id.et_content)).getText().toString();
//
//        String insertSQL = "INSERT OR REPLACE INTO DIARY VALUES(" +
//                "'"+ diary_dt +"', '"+ content +"'";
//        sqLiteDatabase.execSQL(insertSQL);
//        Toast.makeText(ex8_3SQLActivity.this, "일기가 저장되었습니다.", Toast.LENGTH_SHORT).show();
//        sqLiteDatabase.close();
//        btn.setText("일기 수정");
//        et_content.setText("");
//
//    }

    public void selectFromSql(String dt) {

        SQLiteDatabase sqLiteDatabase = diaryHelper.getReadableDatabase();
        String selectSQL = "SELECT diary_content \r\n" +
                "FROM DIARY \r\n" +
                "WHERE diary_dt = '" + dt + "'";


        Cursor cursor = sqLiteDatabase.rawQuery(selectSQL, null);
        if (cursor.moveToNext()) {
            content = cursor.getString(0);
            et_content.setText(content);
            btn.setEnabled(true);
            btn.setText("일기 수정");
        } else {
            //btn.setEnabled(false);
            //btn.setText("일기 저장");

        }
        sqLiteDatabase.close();
        Toast.makeText(this,"enter",Toast.LENGTH_LONG);
    }
}
