package com.techzonecs.tremble.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.techzonecs.tremble.R;

public class ListItemViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item_view);

        TextView tvCourseName = (TextView) this.findViewById(R.id.tv_course_name);
        TextView tvClassName = (TextView) this.findViewById(R.id.tv_class_name);
        TextView tvLocationName = (TextView) this.findViewById(R.id.tv_location_name);
        Button btnLocationGps = (Button) this.findViewById(R.id.btn_location_gps);
        TextView tvZone = (TextView) this.findViewById(R.id.tv_zone);
        TextView tvDate = (TextView) this.findViewById(R.id.tv_date);
        TextView tvTrainerName = (TextView) this.findViewById(R.id.tv_trainer);


        tvClassName.setText(getIntent().getStringExtra("class_name"));
        tvCourseName.setText(getIntent().getStringExtra("course_name"));
        tvLocationName.setText(getIntent().getStringExtra("location_name"));
        tvZone.setText(getIntent().getStringExtra("zone"));
     //   tvDate.setText(getIntent().getStringExtra("date"));
        tvTrainerName.setText(getIntent().getStringExtra("trainer_name"));
        btnLocationGps.setText(getIntent().getStringExtra("Open GPS Location").toString());

        btnLocationGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
