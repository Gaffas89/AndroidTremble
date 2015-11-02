package com.techzonecs.tremble.controller;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.techzonecs.tremble.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.net.URLEncoder;

public class QrMakerActivity extends AppCompatActivity {

    ImageLoader imgLoader;
    ImageView qrImg;
    public final static String PREF_NAME="userInfo";

    String BASE_QR_URL = "https://chart.googleapis.com/chart?chs=200x200&cht=qr&chl=";
    String fullUrl = BASE_QR_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_maker);

        SharedPreferences prefs= getSharedPreferences(PREF_NAME, MODE_APPEND);


        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        imgLoader = ImageLoader.getInstance();
        imgLoader.init(config);

        String Sisid = prefs.getString("firstname", "ERROR");

        qrImg = (ImageView) findViewById(R.id.iv_qr_code);

        try{

            fullUrl += URLEncoder.encode(Sisid , "UTF-8");
            imgLoader.displayImage(fullUrl, qrImg);

        }catch (Exception e){

            e.printStackTrace();
        }
    }
}
