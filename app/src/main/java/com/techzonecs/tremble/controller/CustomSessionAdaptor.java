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
        TextView tvCourseName = (TextView) convertView.findViewById(R.id.tv_course_name);
        TextView tvClassName = (TextView) convertView.findViewById(R.id.tv_class_name);
        TextView tvLocationName = (TextView) convertView.findViewById(R.id.tv_location_name);
        Button btnLocationGps = (Button) convertView.findViewById(R.id.btn_location_gps);
        TextView tvZone = (TextView) convertView.findViewById(R.id.tv_zone);
        TextView tvDate = (TextView) convertView.findViewById(R.id.tv_date);
        TextView tvTrainerName = (TextView) convertView.findViewById(R.id.tv_trainer_name);

        // Populate the data into the template view using the data object
        tvCourseName.setText(session.getCourseName());
        tvClassName.setText(session.getClassName());
        tvLocationName.setText(session.getLocation());
        tvZone.setText(session.getZone());
        btnLocationGps.setText(session.getLocationGps());
        tvTrainerName.setText(session.getTrainerName());
        tvDate.setText(session.getDate());
        Log.d("test", session.getClassName());
        // Return the completed view to render on screen
        return convertView;
    }
}