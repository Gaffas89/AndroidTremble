package com.techzonecs.tremble.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.techzonecs.tremble.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageView ivProfile = (ImageView)findViewById(R.id.imageViewProfile);
        ImageView ivAttendance = (ImageView)findViewById(R.id.imageViewAttendance);
        ImageView ivSessions = (ImageView)findViewById(R.id.imageViewSession);

        TextView tvProfile = (TextView)findViewById(R.id.textViewProfile);
        TextView tvAttendance = (TextView)findViewById(R.id.textViewAttendance);
        TextView tvSessions = (TextView)findViewById(R.id.textViewSessions);

        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(HomeActivity.this, ProfileViewActivity.class);
                startActivity(i1);
                finish();
            }
        });

        ivAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(HomeActivity.this, QrMakerActivity.class);
                startActivity(i2);
            }
        });

        ivSessions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3 = new Intent(HomeActivity.this, SessionListViewActivity.class);
                startActivity(i3);
            }
        });

        tvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i4 = new Intent(HomeActivity.this, ProfileViewActivity.class);
                startActivity(i4);
                finish();
            }
        });

        tvAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i5 = new Intent(HomeActivity.this, QrMakerActivity.class);
                startActivity(i5);
            }
        });

        tvSessions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i6 = new Intent(HomeActivity.this, SessionListViewActivity.class);
                startActivity(i6);
            }
        });

    }
}