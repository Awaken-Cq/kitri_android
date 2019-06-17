package com.example.myapp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kitri.dto.Product;

public class ProductView extends LinearLayout {
    private ImageView ivProd_img;
    private TextView tvProd_no;
    private TextView tvProd_name;
    private TextView tvProd_price;
    public ProductView(Context context, Product product) {
        super(context);
        // Layout Inflation
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.listitem, this, true);

        //------------
        ivProd_img = (ImageView) findViewById(R.id.prod_img); //이미지뷰
//        String imgFileName = "001"//product.getProd_img();//이미지파일명
        int imgResource = -1;
        imgResource = R.drawable.c001;

//        if(imgFileName.equals("americano")){
//            imgResource = R.drawable.americano;
//        }else{
//            imgResource = R.drawable.icelatte;
//        }

        Resources res = getResources();
        Drawable img = res.getDrawable(imgResource);
        ivProd_img.setImageDrawable(img);
        //---------------
        tvProd_no = (TextView) findViewById(R.id.prod_no);
        tvProd_no.setText(product.getProduct_no());

        tvProd_name = (TextView) findViewById(R.id.prod_name);
        tvProd_name.setText(product.getProduct_name());

        tvProd_price = (TextView) findViewById(R.id.prod_price);
        tvProd_price.setText("" + product.getProduct_price());
        //-------------------
		/* NumberPicker numberPicker = (NumberPicker) findViewById(R.id.numberPicker1);
        numberPicker.setMaxValue(20);
        numberPicker.setMinValue(0);     */
        // numberPicker.setWrapSelectorWheel(true);
    }
}

