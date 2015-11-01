package com.techzonecs.tremble.controller;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.techzonecs.tremble.R;

public class EditProfileActivity extends AppCompatActivity {

    public final static String PREF_NAME="userInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        EditText etName = (EditText) findViewById(R.id.editTextName);
        EditText etEmail = (EditText) findViewById(R.id.editTextEmail);
        EditText etMobile = (EditText) findViewById(R.id.editTextMobile);
        EditText etSubject = (EditText) findViewById(R.id.editTextSubject);
        EditText etGrade = (EditText) findViewById(R.id.editTextGrade);
        EditText etPassword = (EditText) findViewById(R.id.editTextPassword);
        EditText etConfirmPass = (EditText) findViewById(R.id.editTextConfirmPass);

        SharedPreferences prefs= getSharedPreferences(PREF_NAME, MODE_APPEND);

        etName.setText(prefs.getString("firstname", "ERROR"));
        etEmail.setText(prefs.getString("email", "ERROR"));
        etMobile.setText(prefs.getString("mobile", "ERROR"));
        etSubject.setText(prefs.getString("subject", "ERROR"));
        etGrade.setText(prefs.getString("grade", "ERROR"));
        etPassword.setText(prefs.getString("password", "ERROR"));
        etConfirmPass.setText(prefs.getString("password", "ERROR"));


    }
}
