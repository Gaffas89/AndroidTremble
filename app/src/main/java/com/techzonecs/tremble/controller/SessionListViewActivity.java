package com.techzonecs.tremble.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.techzonecs.tremble.R;
import com.techzonecs.tremble.model.Session;
import com.techzonecs.tremble.utilities.SessionConnection;

import java.util.ArrayList;

public class SessionListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_list_view);

        populateSessionList();
    }

    private void populateSessionList() {
        // Construct the data source
        ArrayList<Session> arrayOfSessions;
        SessionConnection sessionConnect = new SessionConnection();
        arrayOfSessions = sessionConnect.getSessionArray("1");

        Log.d("testing", arrayOfSessions.toString());

        // Create the adapter to convert the array to views
        CustomSessionAdaptor adapter = new CustomSessionAdaptor(this, arrayOfSessions);
        // Attach the adapter to a ListView
        //ListView listView = (ListView) findViewById(R.id.lvUsers);
        //listView.setAdapter(adapter);
    }
}
