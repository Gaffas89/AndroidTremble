package com.techzonecs.tremble.controller;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.techzonecs.tremble.R;
import com.techzonecs.tremble.model.Session;

import java.util.Date;

public class ListItemViewActivity extends AppCompatActivity {

    String[] dates;
    String classId;
    String sessionId;
    String evaluationDoneFlag;
    //the controller for a single session`s details
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item_view);



        // linking all the fields of the xml
        TextView tvCourseName = (TextView) this.findViewById(R.id.tv_course_name);
        TextView tvClassName = (TextView) this.findViewById(R.id.tv_class_name);
        TextView tvLocationName = (TextView) this.findViewById(R.id.tv_location_name);
        TextView tvZone = (TextView) this.findViewById(R.id.tv_zone);
        TextView tvDate1 = (TextView) this.findViewById(R.id.textView1stDate);
        TextView tvDate2 = (TextView) this.findViewById(R.id.textView2ndDate);
        TextView tvDate3 = (TextView) this.findViewById(R.id.textView3rdDate);
        TextView tvDate4 = (TextView) this.findViewById(R.id.textView4thDate);
        TextView tvTrainerName = (TextView) this.findViewById(R.id.tv_trainer);
        ImageView imMap = (ImageView) findViewById(R.id.imageViewMap);


        //set the values of the fields
        tvClassName.setText(getIntent().getStringExtra("class_name"));
        tvCourseName.setText(getIntent().getStringExtra("course_name"));
        tvLocationName.setText(getIntent().getStringExtra("location_name"));
        tvZone.setText(getIntent().getStringExtra("zone"));

        classId = getIntent().getStringExtra("class_id");
        sessionId = getIntent().getStringExtra("session_id");
        evaluationDoneFlag = getIntent().getStringExtra("isEvaluationDone");

        String tempStringDates = getIntent().getStringExtra("dates");
        dates = tempStringDates.split(",", -1);
        tvDate1.setText(dates[0]);
        tvDate2.setText(dates[1]);
        tvDate3.setText(dates[2]);
        tvDate4.setText(dates[3]);

        tvTrainerName.setText(getIntent().getStringExtra("trainer_name"));
        //btnLocationGps.setText(getIntent().getStringExtra("Open GPS Location").toString());

        String tempLocationGPS = getIntent().getStringExtra("location_gps").toString();
        final String[] GPSCoordinates = tempLocationGPS.split(";", -1);

        //set the function of the gps button to navigate to the map
        imMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://www.google.ae/maps/@"+GPSCoordinates[0]+","+GPSCoordinates[1]+",16z?hl=en"));

                startActivity(i);

            }
        });

    }

    public void navigateToEvaluationView(View view)
    {
        if ( evaluationDoneFlag.equals("false")) {
            if (dates[3].equals(new Date())){
                Intent i = new Intent(ListItemViewActivity.this, EvaluationListActivity.class);
                i.putExtra("id_class", classId);
                i.putExtra("id_session", sessionId);
                startActivity(i);
            } else {
                Toast.makeText(ListItemViewActivity.this, "Cannot Evaluate Yet!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(ListItemViewActivity.this, "You have finished this Evaluation.", Toast.LENGTH_SHORT).show();
        }
    }
}
