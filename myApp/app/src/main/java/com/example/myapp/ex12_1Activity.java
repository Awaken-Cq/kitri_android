package com.example.myapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ex12_1Activity extends AppCompatActivity {

    class MySQLiteOpenHelper extends SQLiteOpenHelper {
        public MySQLiteOpenHelper(Context context,
                               String name) {
            super(context, name, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            //DB생성작업
            //테이블 생성
            String sql = "CREATE TABLE groups( \r\n" +
                    "name varchar2(20), cnt int)";
            sqLiteDatabase.execSQL(sql);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            //테이블삭제
            String sql = "DROP TABLE IF EXISTS groups";
            sqLiteDatabase.execSQL(sql);
        }
    }

    private MySQLiteOpenHelper myHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex12_1);
        myHelper = new MySQLiteOpenHelper(this, "testDB");

    }

    public void init(View view){
        //초기화
        //쓰기가능
        SQLiteDatabase sqLiteDatabase =  myHelper.getWritableDatabase();
        int majorVersion = 1;
        int minorVersion = 0;
        //읽기전용
        //myHelper.getReadableDatabase();
        myHelper.onUpgrade(sqLiteDatabase, majorVersion, minorVersion);//테이블제거
        myHelper.onCreate(sqLiteDatabase);//테이블 생성

    }

    public void insert(View view){
        //입력
        String name = ((EditText)findViewById(R.id.et_name)).getText().toString();
        String cnt = ((EditText)findViewById(R.id.et_cnt)).getText().toString();
        SQLiteDatabase sqLiteDatabase = myHelper.getWritableDatabase();
        sqLiteDatabase.execSQL("INSERT INTO groups VALUES('" + name +"', '"+ Integer.parseInt(cnt)+"')");
        sqLiteDatabase.close();
        select(findViewById(R.id.bt_select));
    }

    public void select(View view){
        SQLiteDatabase sqLiteDatabase = myHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM groups", null);
        //cursor.moveToFirst();//첫행으로이동
        //cursor.moveToLast();//마지막행으로이동
        StringBuffer sb = new StringBuffer();
        sb.append("그룹명 - 인원수 \n");
        while(cursor.moveToNext()){//다음행으로 이동
            //SQLite에서는 인덱스의 위치가 0부터시작함.
          String name = cursor.getString(0);//select한 첫번째 컬럼
            int cnt = cursor.getInt(1);//select한 두번째 컬럼
            sb.append(name + "-" + cnt + "\n");
        }
        TextView tvResult = findViewById(R.id.tvResult);
        tvResult.setText(sb.toString());
        sqLiteDatabase.close();

    }


}
