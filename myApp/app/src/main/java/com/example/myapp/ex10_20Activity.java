package com.example.myapp;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ex10_20Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex10_20);
    }

    public void dial(View view) {
        Uri uri = Uri.parse("tel:01027026429");
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(intent);

    }

    public void map(View view) {
        Uri uri = Uri.parse("https://www.google.co.kr/maps/@37.4853165,126.8985408,16z?hl=ko");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void send(View view) {

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.putExtra("sms_body", "날이 좋아서 날이 흐려서 날이 거지같아서");
        intent.setData(Uri.parse("smsto:" + Uri.encode("tel:01027026429")));
        startActivity(intent);
    }

    public void capture(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);

    }
}
