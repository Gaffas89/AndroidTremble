package com.techzonecs.tremble.controller;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog.Builder;

import com.techzonecs.tremble.R;
//import com.techzonecs.tremble.utilities.LogInAsyncTask;
import com.techzonecs.tremble.utilities.ConnectionURLString;
import com.techzonecs.tremble.utilities.ForgetPasswordConnection;
import com.techzonecs.tremble.utilities.LoginConnection;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.net.URI;
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
                if (longInValidaion()) {
                    Log.d("LOGINPAGEACTIVITY", "SISID: " + etSISID.getText().toString() + " PASSWORD: " + etPassword.getText().toString());

                    LoginConnection lc = new LoginConnection();
                    boolean flag = lc.logIn(etSISID.getText().toString(), etPassword.getText().toString(), LoginPageActivity.this);
                    Log.d("LOGINPAGE", "" + flag);

                } else {
                    Toast.makeText(LoginPageActivity.this, "Log in failed! Check Credentials..", Toast.LENGTH_SHORT).show();
                    Log.d("TOAST", "FAILED");
                }
            }
        });

        // Creating an object of the dialog or something

        Builder builder = new Builder(this);

        builder.setTitle("Forget Password");

        final EditText usernameInput = new EditText(this);
        usernameInput.setInputType(InputType.TYPE_CLASS_TEXT);

        builder.setView(usernameInput);
        builder.setMessage("Reset Password via SISID");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setCancelable(false);

        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                String userId = usernameInput.getText().toString();
                ForgetPasswordConnection forgetPassword = new ForgetPasswordConnection();

                forgetPassword.forgotPassword(LoginPageActivity.this, userId);
                Toast.makeText(LoginPageActivity.this, "An Email has been sent with your new password", Toast.LENGTH_SHORT).show();

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        final Dialog dialog = builder.create();

        TextView tvForgotPassword = (TextView) findViewById(R.id.tv_forget_pw);
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show();
            }
        });
    }

    //to validate the login form
    private boolean longInValidaion(){
        //checks if one of the fields is empty returns true if both are filled
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
