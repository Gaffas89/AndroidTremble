package com.techzonecs.tremble.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.techzonecs.tremble.R;
import com.techzonecs.tremble.model.Session;

public class ListItemViewActivity extends AppCompatActivity {

    String classId;
    String sessionId;
    //the controller for a single session`s details
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item_view);



        // linking all the fields of the xml
        TextView tvCourseName = (TextView) this.findViewById(R.id.tv_course_name);
        TextView tvClassName = (TextView) this.findViewById(R.id.tv_class_name);
        TextView tvLocationName = (TextView) this.findViewById(R.id.tv_location_name);
        Button btnLocationGps = (Button) this.findViewById(R.id.btn_location_gps);
        TextView tvZone = (TextView) this.findViewById(R.id.tv_zone);
        //TextView tvDate = (TextView) this.findViewById(R.id.tv_date);
        TextView tvTrainerName = (TextView) this.findViewById(R.id.tv_trainer);
        Button btnEvaluation = (Button) this.findViewById(R.id.btn_evaluation);

        //set the values of the fields
        tvClassName.setText(getIntent().getStringExtra("class_name"));
        tvCourseName.setText(getIntent().getStringExtra("course_name"));
        tvLocationName.setText(getIntent().getStringExtra("location_name"));
        tvZone.setText(getIntent().getStringExtra("zone"));

        classId = getIntent().getStringExtra("class_id");
        sessionId = getIntent().getStringExtra("session_id");

        //tvDate.setText(getIntent().getStringExtra("date")); // to be added once the session back end is fixed
        tvTrainerName.setText(getIntent().getStringExtra("trainer_name"));
        //btnLocationGps.setText(getIntent().getStringExtra("Open GPS Location").toString());

        //set the function of the gps button to navigate to the map
        btnLocationGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public void navigateToEvaluationView(View view)
    {
        Intent i = new Intent(ListItemViewActivity.this , EvaluationListActivity.class);
        i.putExtra("id_class", classId);
        i.putExtra("id_session" , sessionId);
        startActivity(i);
    }
}
