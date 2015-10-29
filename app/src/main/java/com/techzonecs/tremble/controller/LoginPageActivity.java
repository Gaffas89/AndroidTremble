package com.techzonecs.tremble.controller;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.techzonecs.tremble.R;
import com.techzonecs.tremble.model.Session;
import com.techzonecs.tremble.utilities.AppController;
//import com.techzonecs.tremble.utilities.LogInAsyncTask;
import com.techzonecs.tremble.utilities.LoginConnection;
import com.techzonecs.tremble.utilities.SessionConnection;

import java.util.ArrayList;

public class LoginPageActivity extends AppCompatActivity {

    EditText etSISID;
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button singin = (Button) findViewById(R.id.btn_signin);
        etSISID = (EditText) findViewById(R.id.TFfield_SISID);
        etPassword = (EditText) findViewById(R.id.TFfield_pw);

        singin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (longInValidaion()){
                    Log.d("LOGINPAGEACTIVITY", "SISID: "+etSISID.getText().toString()+" PASSWORD: "+etPassword.getText().toString());
//                    LoginConnection lc = new LoginConnection();
//                    lc.lpa = LoginPageActivity.this;
//                    lc.logIn(etSISID.getText().toString(), etPassword.getText().toString());

                   // new LogInAsyncTask(etSISID.getText().toString(), etPassword.getText().toString());

                    LoginConnection lc = new LoginConnection();
                    boolean flag = lc.logIn(etSISID.getText().toString(), etPassword.getText().toString(), LoginPageActivity.this);
                    Log.d("LOGINPAGE", ""+flag);

                } else {
                    Toast.makeText(LoginPageActivity.this, "Log in failed! Check Credentials..", Toast.LENGTH_SHORT).show();
                    Log.d("TOAST", "FAILED");
                }
            }
        });

    }

    //to validate the login form
    private boolean longInValidaion(){
        //checks if one of the field is empty returns true if both are filled
        if (etSISID.getText().toString().equals("")) {
            return false;
        }
        if (etPassword.getText().toString().equals("")){
            return false;
        }
        else {
            return true;
        }
    }

}
