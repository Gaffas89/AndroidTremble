package com.techzonecs.tremble.utilities;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.techzonecs.tremble.model.Session;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gaffas on 28/10/2015.
 */
public class SessionConnection {


    private String TAG;
    private static ArrayList<Session> sessionArrayList = new ArrayList<>();

    public ArrayList<Session> getSessionArray(String trainee){
        String tag_string_req = "string_req";

        String url = "http://192.168.1.129:8080/TrembleBackend/GetSessions?id_trainee=" + trainee;

       StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try{
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray jsonArray = new JSONArray(jsonResponse.getString("result_data"));
                    for(int i = 0; i < jsonArray.length() ; i++)
                    {
                        JSONObject json = jsonArray.getJSONObject(i);
                        Session sess = new Session();
                        sess.setClassName(json.getString("class_name"));
                        sess.setCourseName(json.getString("course_name"));
                        sess.setDate(json.getString("wave_date"));
                        sess.setLocation(json.getString("location_name"));
                        sess.setLocationGps(json.getString("location_gps"));
                        sess.setTrainerName(json.getString("trainer_name"));
                        sess.setZone(json.getString("zone"));

                        sessionArrayList.add(sess);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //VolleyLog.d(TAG, "Error: " + error.getMessage());
                Log.d("error","test1");
            }
        });

// Adding request to request queue
       AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
        return sessionArrayList;
    }
}


