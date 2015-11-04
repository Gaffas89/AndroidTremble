package com.techzonecs.tremble.controller;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.techzonecs.tremble.R;
import com.techzonecs.tremble.model.Session;

import java.util.ArrayList;

// Populates different sessions for each item in the list
public class CustomSessionAdaptor extends ArrayAdapter<Session> {

    public CustomSessionAdaptor(Context context, ArrayList<Session> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Session session = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.session_list_item, parent, false);
        }

        // Lookup view for data population
        TextView tvLocationName = (TextView) convertView.findViewById(R.id.tv_location_name);
        TextView tvDate1 = (TextView) convertView.findViewById(R.id.tv_date);
        TextView tvDate2 = (TextView) convertView.findViewById(R.id.tv_end_date);


        // Populate the data into the template view using the data object
        tvLocationName.setText(session.getLocation());
        String[] dates = session.getDate();
        tvDate1.setText(dates[0]);
        tvDate2.setText(dates[3]);


        // Return the completed view to render on screen
        return convertView;
    }
}