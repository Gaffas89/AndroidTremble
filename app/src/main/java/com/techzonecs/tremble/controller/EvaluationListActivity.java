package com.techzonecs.tremble.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.techzonecs.tremble.R;
import com.techzonecs.tremble.model.Question;
import com.techzonecs.tremble.utilities.AppController;
import com.techzonecs.tremble.utilities.ConnectionURLString;
import com.techzonecs.tremble.utilities.QuestionConnection;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class EvaluationListActivity extends ActionBarActivity {

    TextView question_id;
    RadioGroup radioGroup;
    RadioButton radioButton;
    int sectionPage = 0;
    public ArrayList<Question> questionArrayList = new ArrayList<Question>();

    ArrayList<Question> mappedSecsion[];
    int chosen_answer[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation);

        getQuestionsArray(EvaluationListActivity.this);

    }

    private void populateSessionList(int section) {

        // Create the adapter to convert the array to views
        EvaluationCustomAdaptor adapter = new EvaluationCustomAdaptor(this, mappedSecsion[section]);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.lv_Questions);
        listView.setAdapter(adapter);
    }

    String  tag_string_req = "string_req";

    public void getQuestionsArray(final Context context) {

        String url = ConnectionURLString.url + "GetQuestions";

        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                ArrayList<Question> arrayOfQuestions = parseJson(response);
                questionArrayList = arrayOfQuestions;

                Question changedPlace;

                mappedSecsion = new ArrayList[4];
                mappedSecsion[0] = new ArrayList();
                mappedSecsion[1] = new ArrayList();
                mappedSecsion[2] = new ArrayList();
                mappedSecsion[3] = new ArrayList();

                for (int i = 0; i < questionArrayList.size() ; i++)
                {
                    int section = questionArrayList.get(i).getQuestion_Section() - 1;
                    mappedSecsion[section].add(questionArrayList.get(i));
                }

                populateSessionList(sectionPage);
                chosen_answer = new int[questionArrayList.size()];

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //VolleyLog.d(TAG, "Error: " + error.getMessage());
                Log.d("error", "test1");
            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }

    private ArrayList<Question> parseJson(String str){

        ArrayList<Question> questionsArrayList = new ArrayList<>();
        try{
            JSONObject jsonResponse = new JSONObject(str);
            String answer = jsonResponse.getString("result_data");
            JSONArray jsonArray = new JSONArray(answer);

            for(int i = 0; i < jsonArray.length() ; i++)
            {

                JSONObject json = jsonArray.getJSONObject(i);
                Question question = new Question();
                question.setQuestion(json.getString("question"));
                question.setQuestion_Id(Integer.parseInt(json.getString("question_id")));
                question.setQuestion_Section(Integer.parseInt(json.getString("section")));

                questionsArrayList.add(question);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return questionsArrayList;
    }

    boolean flag = false;
    public void submit_Answers(View v) {


        ListView listView = (ListView) findViewById(R.id.lv_Questions);

        for(int i = 0; i < mappedSecsion[sectionPage].size() ; i++)
        {
            View viewInCell = listView.getChildAt(i);
            radioGroup = (RadioGroup) viewInCell.findViewById(R.id.rg_chosen_answer);
            Log.d("radio button", ""+radioGroup.getCheckedRadioButtonId());

            if(radioGroup.getCheckedRadioButtonId() == -1)
            {
                Log.d("statement", ""+i);
                flag = false;
            }
            else
            {
                Log.d("statement", ""+i);
                flag = true;
            }
        }




        if(flag)
        {
            for(int i = 0; i < mappedSecsion[sectionPage].size() ; i++)
            {
                View viewInCell = listView.getChildAt(i);
                radioGroup = (RadioGroup) viewInCell.findViewById(R.id.rg_chosen_answer);
                question_id = (TextView) viewInCell.findViewById(R.id.tv_question_id);
                int chosenID = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(chosenID);

                int q_id = Integer.parseInt(question_id.getText().toString());
                chosen_answer[q_id-1] = Integer.parseInt(radioButton.getText().toString());
            }

            if (sectionPage < 3)
            {
                populateSessionList(++sectionPage);
            }
            else
            {
                Toast.makeText(EvaluationListActivity.this, "Your Evaluation has been submitted", Toast.LENGTH_SHORT).show();
                final SharedPreferences prefs= getSharedPreferences("userInfo", MODE_APPEND);
                String url = ConnectionURLString.url + "EvaluationAnswers?id_trainee=" + prefs.getString("sisid", "ERROR") +"&id_class=" + getIntent().getStringExtra("id_class");

                for (int i = 0; i < chosen_answer.length ; i++)
                {
                    url += "&answers[]=" + chosen_answer[i];
                }

                url += "&id_session=" + getIntent().getStringExtra("id_session");
                sendEvaluation(url);

                Intent i = new Intent(EvaluationListActivity.this , HomeActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();


            }
        }
        else
        {
            Toast.makeText(EvaluationListActivity.this, "Please fill in all the choices", Toast.LENGTH_SHORT).show();
        }
    }

    public void sendEvaluation(String url) {
        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }
}
