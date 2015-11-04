package com.techzonecs.tremble.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.techzonecs.tremble.R;
import com.techzonecs.tremble.model.Question;
import com.techzonecs.tremble.model.Session;

import java.util.ArrayList;

/**
 * Created by Gaffas on 03/11/2015.
 */
public class EvaluationCustomAdaptor extends ArrayAdapter<Question> {

    public EvaluationCustomAdaptor(Context context, ArrayList<Question> questions) {
        super(context, 0, questions);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Question question = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_evaluation_list_item, parent, false);
        }

        // Lookup view for data population
        TextView tvQuestion = (TextView) convertView.findViewById(R.id.tv_Question);
        TextView tvSection = (TextView) convertView.findViewById(R.id.tv_Section); // This can be changed to a text related to each number via the creation of an enum
        TextView tvQuestionId = (TextView) convertView.findViewById(R.id.tv_question_id);

        // Populate the data into the template view using the data object
        tvQuestion.setText(question.getQuestion());
        tvSection.setText("Section "+question.getQuestion_Section());
        tvQuestionId.setText(""+question.getQuestion_Id());

        // Return the completed view to render on screen
        return convertView;
    }
}
