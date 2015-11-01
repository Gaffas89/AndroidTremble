package com.techzonecs.tremble.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.techzonecs.tremble.R;

public class SplashScreenActivity extends AppCompatActivity {

    public final static String PREF_NAME="userInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread t=new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
//                    Thread.sleep(3500);
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_APPEND);
                boolean loggedin = prefs.getBoolean("isLoggedIn", false);

                Class nextClass = LoginPageActivity.class;

                if(loggedin){
                    nextClass= SessionListViewActivity.class;
                }

                Intent i=new Intent(SplashScreenActivity.this,nextClass);
                startActivity(i);
                finish();
            }
        });
        t.start();

    }
}
