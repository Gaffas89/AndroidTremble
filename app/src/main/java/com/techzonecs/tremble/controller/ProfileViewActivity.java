package com.techzonecs.tremble.controller;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.techzonecs.tremble.R;

public class ProfileViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);
        TextView tvName = (TextView) findViewById(R.id.textViewName);
        TextView tvSISID = (TextView) findViewById(R.id.textViewSISID);
        TextView tvMobile = (TextView) findViewById(R.id.textViewMobile);
        TextView tvEmail = (TextView) findViewById(R.id.textViewEmail);
        TextView tvSubject = (TextView) findViewById(R.id.textViewSubject);
        TextView tvGrade = (TextView) findViewById(R.id.textViewGrade);

        Intent intent = getIntent();
        tvName.setText(intent.getStringExtra("firstname"));
        tvSISID.setText(intent.getStringExtra("sisid"));
        tvMobile.setText(intent.getStringExtra("mobile"));
        tvEmail.setText(intent.getStringExtra("email"));
        tvSubject.setText(intent.getStringExtra("subject"));
        tvGrade.setText(intent.getStringExtra("grade"));




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);


    }
}
