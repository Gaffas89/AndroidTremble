package com.techzonecs.tremble.controller;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog.Builder;

import com.techzonecs.tremble.R;
//import com.techzonecs.tremble.utilities.LogInAsyncTask;
import com.techzonecs.tremble.utilities.ConnectionURLString;
import com.techzonecs.tremble.utilities.ForgetPasswordConnection;
import com.techzonecs.tremble.utilities.LoginConnection;

import java.net.URI;
import java.util.ArrayList;

public class LoginPageActivity extends Activity {

    EditText etSISID;
    EditText etPassword;
    ImageView etLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //MAKE APP FULL SCREEN
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Button singin = (Button) findViewById(R.id.btn_signin);
        etLogin = (ImageView) findViewById(R.id.btn_signin1);
        etSISID = (EditText) findViewById(R.id.TFfield_SISID);
        etPassword = (EditText) findViewById(R.id.TFfield_pw);

        etLogin.setOnClickListener(new View.OnClickListener() {
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
