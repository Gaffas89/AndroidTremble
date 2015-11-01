package com.techzonecs.tremble.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.techzonecs.tremble.R;
import com.techzonecs.tremble.model.Session;
import com.techzonecs.tremble.utilities.SessionConnection;

import java.util.ArrayList;

public class SessionListViewActivity extends AppCompatActivity {

    ArrayList<Session> arrayOfSessions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_list_view);

        populateSessionList();
    }

    private void populateSessionList() {
        // Construct the data source

        SessionConnection sessionConnect = new SessionConnection();

        arrayOfSessions = sessionConnect.getSessionArray("2");

        Log.d("testing", arrayOfSessions.toString());

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
            i.putExtra("date", arrayOfSessions.get((int) id).getDate());

            startActivity(i);
        }
    };
}
