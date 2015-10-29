package com.techzonecs.tremble.utilities;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.techzonecs.tremble.controller.LoginPageActivity;
import com.techzonecs.tremble.controller.ProfileViewActivity;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Created by Bouyabes on 10/28/2015.
 */
public class LoginConnection {

    String TAG = "TREMBLE";
//    public LoginPageActivity lpa = null;
    boolean isLoggedIn = false;

    public boolean logIn(final String sisid, final String password, final Context context){

        String url = ConnectionURLString.url+"Login?id_trainee="+sisid+"&password="+password;
        // Tag used to cancel the request
        String  tag_string_req = "string_req";


        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());

                try {
                    JSONObject json = new JSONObject(response);
                    JSONArray jsonArray = json.getJSONArray("result_data");
                    JSONObject result = jsonArray.getJSONObject(0);
                    isLoggedIn = Boolean.parseBoolean(result.getString("flag"));
                    Log.d(TAG,"isLoggedIn: "+isLoggedIn);

                    if (isLoggedIn){
                        Intent i = new Intent(context, ProfileViewActivity.class);
                        context.startActivity(i);
                        //add finish here later
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


//    public void logIn(final String sisid, final String password){
//        // Tag used to cancel the request
//        String tag_json_obj = "json_obj_req";
//
//        String url = ConnectionURLString.url+"Login?id_trainee="+sisid+"&password="+password;
//        Log.d(TAG, url);
//
//        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
//                url, null,
//                new Response.Listener<JSONObject>() {
//
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Log.d(TAG, response.toString());
//                        responseHandling(response);
//
//                    }
//                }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d(TAG, "Error: " + error.getMessage());
//
//            }
//        }) {
//
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
////                params.put("id_trainee", sisid);
////
////                params.put("password", password);
//
//                return params;
//            }
//
//        };
//
//// Adding request to request queue
//        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
//    }
//
//    void responseHandling(JSONObject response){
//        try {
//            JSONObject jsnobject = response;
//            JSONArray jsonArray = jsnobject.getJSONArray("result_data");
//
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject tempObject = jsonArray.getJSONObject(i);
//                String logInFlag = tempObject.getString("flag");
//
//                Log.d("test:",logInFlag.toString());
//
//                boolean flag = Boolean.parseBoolean(logInFlag);
//                if (flag){
//                    Toast.makeText(lpa, "ture!", Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(lpa, "FALSE!", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }catch (JSONException e){
//
//        }
//    }


}
