package com.techzonecs.tremble.controller;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

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
        Log.d("after adaptor", "submit_Answers");
        listView.setAdapter(adapter);
    }

    String  tag_string_req = "string_req";

    public void getQuestionsArray(final Context context) {

        String tag_string_req = "string_req";

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

                Log.d("listing", mappedSecsion[0].toString());
                Log.d("listing", mappedSecsion[1].toString());
                Log.d("listing", mappedSecsion[2].toString());
                Log.d("listing", mappedSecsion[3].toString());
                Log.d("listing", mappedSecsion.toString());

                for (int i = 0; i < questionArrayList.size() ; i++)
                {
                    int section = questionArrayList.get(i).getQuestion_Section() - 1;
                    mappedSecsion[section].add(questionArrayList.get(i));
                }

                Log.d("before", arrayOfQuestions.toString());

                populateSessionList(sectionPage);

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //VolleyLog.d(TAG, "Error: " + error.getMessage());
                Log.d("error", "test1");
            }
        });

        Log.d("before", strReq.toString());
// Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }

    private ArrayList<Question> parseJson(String str){

        ArrayList<Question> questionsArrayList = new ArrayList<>();
        try{
            JSONObject jsonResponse = new JSONObject(str);
            String answer = jsonResponse.getString("result_data");
            Log.d("submit_Answers", answer);
            JSONArray jsonArray = new JSONArray(answer);
            Log.d("test1", ""+jsonArray.length());


            for(int i = 0; i < jsonArray.length() ; i++)
            {

                JSONObject json = jsonArray.getJSONObject(i);
                Question question = new Question();
                question.setQuestion(json.getString("question"));
                question.setQuestion_Id(Integer.parseInt(json.getString("question_id")));
                question.setQuestion_Section(Integer.parseInt(json.getString("section")));


                questionsArrayList.add(question);
            }


            Log.d("array", questionsArrayList.toString());
        }catch (Exception e){
            e.printStackTrace();
        }

        return questionsArrayList;
    }

    public void submit_Answers(View v) {

        populateSessionList(++sectionPage);
//        String replyString = ConnectionURLString.url ;
//
//        int answers[] = new int[questionArrayList.size()];
//
//        ListView listView = (ListView) findViewById(R.id.lv_Questions);
//        for (int i = 0; i < 5; i++) {
//            View viewInCell = listView.getChildAt(i);
//
//            //Log.d("id",""+ viewInCell.findViewById(R.id.tv_question_id));
////            question_id = (TextView) viewInCell.findViewById(R.id.tv_question_id);
////            radioGroup = (RadioGroup) viewInCell.findViewById(R.id.rg_chosen_answer);
////
////            int chosenID = radioGroup.getCheckedRadioButtonId();
////            radioButton = (RadioButton) findViewById(chosenID);
////            Log.d(""+radioButton.getText().toString(),"test");
//
//
//           // replyString += "&answers[]=" + ;
//        }
    }
}
