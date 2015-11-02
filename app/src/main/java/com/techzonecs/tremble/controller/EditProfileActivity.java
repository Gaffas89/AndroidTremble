package com.techzonecs.tremble.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.techzonecs.tremble.R;
import com.techzonecs.tremble.utilities.EditProfileConnection;

public class EditProfileActivity extends AppCompatActivity {

    public final static String PREF_NAME="userInfo";
    EditText etName;
    EditText etEmail;
    EditText etMobile;
    EditText etSubject;
    EditText etGrade;
    EditText etPassword;
    EditText etConfirmPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        etName = (EditText) findViewById(R.id.editTextName);
        etEmail = (EditText) findViewById(R.id.editTextEmail);
        etMobile = (EditText) findViewById(R.id.editTextMobile);
        etSubject = (EditText) findViewById(R.id.editTextSubject);
        etGrade = (EditText) findViewById(R.id.editTextGrade);
        etPassword = (EditText) findViewById(R.id.editTextPassword);
        etConfirmPass = (EditText) findViewById(R.id.editTextConfirmPass);

        final SharedPreferences prefs= getSharedPreferences(PREF_NAME, MODE_APPEND);

        etName.setText(prefs.getString("firstname", "ERROR"));
        etEmail.setText(prefs.getString("email", "ERROR"));
        etMobile.setText(prefs.getString("mobile", "ERROR"));
        etSubject.setText(prefs.getString("subject", "ERROR"));
        etGrade.setText(prefs.getString("grade", "ERROR"));
        etPassword.setText(prefs.getString("password", "ERROR"));
        etConfirmPass.setText(prefs.getString("password", "ERROR"));

        Button save = (Button)findViewById(R.id.buttonSave);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateEdit()){
                    EditProfileConnection ep = new EditProfileConnection();
                    ep.editProfileConnection(prefs.getString("sisid", "ERROR"), etName.getText().toString(),etPassword.getText().toString(),etEmail.getText().toString(),etMobile.getText().toString(),etSubject.getText().toString(),etGrade.getText().toString(), EditProfileActivity.this);
                }else{
                    Toast.makeText(EditProfileActivity.this, "Check Fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public boolean validateEdit(){

        if(etName.getText().toString().equals("")){
            etName.setError("Cannot leave field empty!");
            return false;
        }
        if (etEmail.getText().toString().equals("")){
            etEmail.setError("Cannot leave field empty!");
            return false;
        }
        if (etGrade.getText().toString().equals("")){
            etGrade.setError("Cannot leave field empty!");
            return false;
        }
        if (etMobile.getText().toString().equals("")){
            etMobile.setError("Cannot leave field empty!");
            return false;
        }
        if (etSubject.getText().toString().equals("")){
            etSubject.setError("Cannot leave field empty!");
            return false;
        }
        if (etMobile.getText().length()<12){
            etMobile.setError("Mobile No. too short");
            return false;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(
                etEmail.getText().toString()).matches()){
            etEmail.setError("Invalid Email..");
            return false;
        }
        if (!etPassword.getText().toString().equals(etConfirmPass.getText().toString())){
            etConfirmPass.setError("Passwords don't match!");
            return false;
        } else {
            return true;
        }

    }

    //to make users go back to the home page when the back button pressed
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(EditProfileActivity.this, ProfileViewActivity.class);
        startActivity(i);
        finish();
    }

}
