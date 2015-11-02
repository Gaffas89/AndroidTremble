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
                    Thread.sleep(2000); //to show the splash screen for 2 seconds before going to the next page
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_APPEND);
                //checks if the user is logged in, if not found it would return false as default value
                boolean loggedin = prefs.getBoolean("isLoggedIn", false);

                Class nextClass = LoginPageActivity.class;
                //To check if the user is already logged is, it would direct him/her to the Sessions view instead of the log in view
                if(loggedin){
                    nextClass= SessionListViewActivity.class;
                }
                //The intent to start the actual redirecting. depends on the previous if statement
                Intent i=new Intent(SplashScreenActivity.this,nextClass);
                startActivity(i);
                finish();//to prevent users from going back to the splash screen
            }
        });
        t.start();//start thread

    }
}
