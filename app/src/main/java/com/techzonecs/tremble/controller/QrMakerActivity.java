package com.techzonecs.tremble.controller;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.techzonecs.tremble.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.Random;

public class QrMakerActivity extends AppCompatActivity {

    ImageLoader imgLoader;
    ImageView qrImg;
    public final static String PREF_NAME="userInfo";

    private static final Random RANDOM = new SecureRandom();

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
        String Sisid = generateRandomBefore();
        Sisid += "x";
        Sisid += prefs.getString("sisid", "ERROR");
        Sisid += "L";
        Sisid += generateRandomAfter();
        Sisid += ";";

        qrImg = (ImageView) findViewById(R.id.iv_qr_code);

        try{

            fullUrl += URLEncoder.encode(Sisid , "UTF-8");
            imgLoader.displayImage(fullUrl, qrImg);

        }catch (Exception e){

            e.printStackTrace();
        }
    }

    public static String generateRandomBefore ()
    {
        // Pick from some letters that won't be easily mistaken for each
        // other. So, for example, omit o O and 0, generateRandomPassword1 l and L.
        String letters = "abcdefghijklmnopqrstuvwyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789012345678901234567890123456789012345678901234567890+@";

        String str = "";
        for (int i=0; i < ((RANDOM.nextDouble()*100)%15 )+10 ; i++)
        {
            int index = (int)(RANDOM.nextDouble()*letters.length());
            str += letters.substring(index, index+1);
        }
        return str;
    }

    public static String generateRandomAfter()
    {
        // Pick from some letters that won't be easily mistaken for each
        // other. So, for example, omit o O and 0, generateRandomPassword1 l and L.
        String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKMNOPQRSTUVWXYZ123456789012345678901234567890123456789012345678901234567890+@";

        String str = "";
        for (int i=0; i < ((RANDOM.nextDouble()*100) %15)+10 ; i++)
        {
            int index = (int)(RANDOM.nextDouble()*letters.length());
            str += letters.substring(index, index+1);
        }
        return str;
    }
}
