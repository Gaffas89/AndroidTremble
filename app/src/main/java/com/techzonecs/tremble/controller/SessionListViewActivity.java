package com.techzonecs.tremble.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.techzonecs.tremble.R;
import com.techzonecs.tremble.model.Session;
import com.techzonecs.tremble.utilities.AppController;
import com.techzonecs.tremble.utilities.ConnectionURLString;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SessionListViewActivity extends AppCompatActivity {

    ArrayList<Session> arrayOfSessions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_list_view);

        getSessionArray("2");
    }



    public void getSessionArray(String trainee){
        String tag_string_req = "string_req";

        String url = ConnectionURLString.url + "GetSessions?id_trainee=" + trainee;

        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {


                arrayOfSessions = parseJson(response);
                populateSessionList();

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

    }

    private ArrayList<Session> parseJson(String str){

        ArrayList<Session> sessionArrayList = new ArrayList<>();
        try{
            JSONObject jsonResponse = new JSONObject(str);
            JSONArray jsonArray = new JSONArray(jsonResponse.getString("result_data"));

            for(int i = 0; i < jsonArray.length() ; i++)
            {

                JSONObject json = jsonArray.getJSONObject(i);
                Session sess = new Session();
                sess.setClassName(json.getString("class_name"));
                sess.setCourseName(json.getString("course_name"));

                String tempDates = json.getString("wave_date");
                String[] dates = tempDates.split(",", -1);
                for (int k = 0; k<4; k++){
                    Log.d("ARRAY OF DATES "+k+": ",dates[k]);
                }
                sess.setDate(dates);
                sess.setLocation(json.getString("location_name"));
                sess.setLocationGps(json.getString("location_gps"));
                sess.setTrainerName(json.getString("trainer_name"));
                sess.setZone(json.getString("zone"));
                sess.setId_session(json.getString("id_session"));
                sess.setId_class(json.getString("id_class"));
                sess.setIsEvaluationDoneFlag(json.getString("isEvaluationDone"));

                sessionArrayList.add(sess);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return sessionArrayList;
    }


    private void populateSessionList() {

        // Create the adapter to convert the array to views
        CustomSessionAdaptor adapter = new CustomSessionAdaptor(this, arrayOfSessions);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.lv_sessions_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(onListClick);

    }

    private AdapterView.OnItemClickListener onListClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Intent i = new Intent(SessionListViewActivity.this , ListItemViewActivity.class);
            i.putExtra("item_Location", id);
            i.putExtra("class_name", arrayOfSessions.get((int) id).getClassName());
            i.putExtra("course_name", arrayOfSessions.get((int) id).getCourseName());
            i.putExtra("location_name", arrayOfSessions.get((int) id).getLocation());
            i.putExtra("location_gps", arrayOfSessions.get((int) id).getLocationGps());
            i.putExtra("trainer_name", arrayOfSessions.get((int) id).getTrainerName());
            i.putExtra("zone", arrayOfSessions.get((int) id).getZone());

            String[] tempDatesArray = arrayOfSessions.get((int) id).getDate();
            String datesString = tempDatesArray[0];
            for (int k = 1; k<tempDatesArray.length; k++){
                datesString = datesString+","+tempDatesArray[k];
            }
            i.putExtra("dates", datesString);
            i.putExtra("class_id", arrayOfSessions.get((int) id).getId_class());
            i.putExtra("session_id", arrayOfSessions.get((int) id).getId_session());
            i.putExtra("isEvaluationDone", arrayOfSessions.get((int)id).getIsEvaluationDoneFlag());


            startActivity(i);
        }
    };
}
