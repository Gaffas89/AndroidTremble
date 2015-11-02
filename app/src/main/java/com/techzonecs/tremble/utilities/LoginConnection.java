package com.techzonecs.tremble.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.techzonecs.tremble.controller.HomeActivity;
import com.techzonecs.tremble.controller.LoginPageActivity;
import com.techzonecs.tremble.controller.ProfileViewActivity;
import com.techzonecs.tremble.model.Trainee;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Created by Bouyabes on 10/28/2015.
 */
public class LoginConnection {

    String TAG = "TREMBLE";
//    public LoginPageActivity lpa = null;
    boolean isLoggedIn = false;

    public final static String PREF_NAME="userInfo";


    public boolean logIn(final String sisid, final String password, final Context context){


        String url = ConnectionURLString.url+"Login?id_trainee="+sisid+"&password="+password;
        // Tag used to cancel the request
        String  tag_string_req = "string_req";


        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());

                SharedPreferences prefs= context.getSharedPreferences(PREF_NAME, context.MODE_APPEND);




                try {
                    JSONObject json = new JSONObject(response);
                    JSONArray jsonArray = json.getJSONArray("result_data");
                    JSONObject result = jsonArray.getJSONObject(0);
                    isLoggedIn = Boolean.parseBoolean(result.getString("flag"));
                    Log.d(TAG,"isLoggedIn: "+isLoggedIn);

                    if (isLoggedIn){

                        SharedPreferences.Editor editor= prefs.edit();
                        //Setting user info in Shared preferences to be retrieved anywhere
                        editor.putBoolean("isLoggedIn", true);
                        editor.putString("sisid", sisid + "");
                        editor.putString("password", password);
                        editor.putString("email", result.getString("email"));
                        editor.putString("firstname", result.getString("firstname"));
                        editor.putString("mobile", result.getString("mobile"));
                        editor.putString("subject", result.getString("subject"));
                        editor.putString("grade", result.getString("grade"));

                        editor.commit();

                        Intent i = new Intent(context, HomeActivity.class);

                        context.startActivity(i);

                        //to finish the activity (not be able to go back)
                        Activity temp = (Activity)context;
                        temp.finish();
                        Log.d(TAG,"intent here: SUCCESS");
                    }else {
                        Toast.makeText(context, "Log in Failed!", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Log.d(TAG, "ERROR!");

            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
        Log.d(TAG,""+isLoggedIn);
        return isLoggedIn;

    }


}
