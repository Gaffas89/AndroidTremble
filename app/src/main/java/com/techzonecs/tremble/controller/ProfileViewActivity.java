package com.techzonecs.tremble.controller;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.techzonecs.tremble.R;

public class ProfileViewActivity extends Activity {

    public final static String PREF_NAME="userInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);
        //MAKE APP FULL SCREEN
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        TextView tvName = (TextView) findViewById(R.id.textViewName);
        TextView tvSISID = (TextView) findViewById(R.id.textViewSISID);
        TextView tvMobile = (TextView) findViewById(R.id.textViewMobile);
        TextView tvEmail = (TextView) findViewById(R.id.textViewEmail);
        TextView tvSubject = (TextView) findViewById(R.id.textViewSubject);
        TextView tvGrade = (TextView) findViewById(R.id.textViewGrade);
        ImageView imgLogout = (ImageView) findViewById(R.id.imageViewLogout);
        ImageView imgEdit = (ImageView) findViewById(R.id.imageViewEditProfile);

        final SharedPreferences prefs= getSharedPreferences(PREF_NAME, MODE_APPEND);

        //getting the information from shared preferences
        tvName.setText(prefs.getString("firstname", "ERROR"));
        tvSISID.setText(prefs.getString("sisid", "ERROR"));
        tvMobile.setText(prefs.getString("mobile", "ERROR"));
        tvEmail.setText(prefs.getString("email", "ERROR"));
        tvSubject.setText(prefs.getString("subject", "ERROR"));
        tvGrade.setText(prefs.getString("grade", "ERROR"));

        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfileViewActivity.this, EditProfileActivity.class);
                startActivity(i);
                finish();
            }
        });

        imgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor= prefs.edit();
                editor.putBoolean("isLoggedIn", false);
                editor.clear(); //clears sharedPreferences
                editor.commit();
                Intent logOutIntent = new Intent(ProfileViewActivity.this, LoginPageActivity.class);
                startActivity(logOutIntent);
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);


    }

    //to make users go back to the home page when the back button pressed
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(ProfileViewActivity.this, HomeActivity.class);
        startActivity(i);
        finish();
    }
}
