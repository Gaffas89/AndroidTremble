package com.techzonecs.tremble.utilities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.techzonecs.tremble.controller.ProfileViewActivity;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Bouyabes on 11/1/2015.
 */
public class EditProfileConnection {

    String TAG = "TREMBLE";

    public final static String PREF_NAME="userInfo";


    public void editProfileConnection(final String sisid, final String name, final String password, final String email, final String mobile, final String subject, final String grade, final Context context){

        Log.d(TAG, "INFO SENT: "+sisid+", "+name+", "+email+", "+mobile+", "+subject+", "+grade);
        String url = ConnectionURLString.url+"EditUserProfile?name="+name+"&password="+password+"&id_trainee="+sisid+"&mobile="+mobile+"&email="+email+"&subject="+subject+"&grade="+grade;
        // Tag used to cancel the request
        String  tag_string_req = "string_req";

        final ProgressDialog pDialog = new ProgressDialog(context);
        pDialog.setMessage("Saving...");
        pDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());

                SharedPreferences prefs= context.getSharedPreferences(PREF_NAME, context.MODE_APPEND);


                try {
//                    JSONObject json = new JSONObject(response);
//                    JSONObject result = json.getJSONObject("result_code");

//                    int result_code = Integer.parseInt(result.toString());

//                    if ( result_code == 0){

                        SharedPreferences.Editor editor= prefs.edit();
                        //Setting user info in Shared preferences to be retrieved anywhere

                        editor.putString("password", password);
                        editor.putString("email", email);
                        editor.putString("firstname", name);
                        editor.putString("mobile", mobile);
                        editor.putString("subject", subject);
                        editor.putString("grade", grade);

                        editor.commit();

                        pDialog.dismiss();

                        Intent i = new Intent(context, ProfileViewActivity.class);
                        context.startActivity(i);
                        //to finish the activity (not be able to go back)
                        Activity temp = (Activity)context;
                        temp.finish();
                        Log.d(TAG,"intent here: SUCCESS");


//                    }else {
//                        pDialog.dismiss();
//                        Toast.makeText(context, "Log in Failed!", Toast.LENGTH_SHORT).show();
//                    }

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

    }



}
