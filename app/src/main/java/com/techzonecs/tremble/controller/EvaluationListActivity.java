package com.techzonecs.tremble.controller;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
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

public class EvaluationListActivity extends ActionBarActivity {

    public TextView khamoosh;

    public ArrayList<Question> questionArrayList = new ArrayList<Question>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation);

        getQuestionsArray(EvaluationListActivity.this);

    }

    private void populateSessionList() {

        // Create the adapter to convert the array to views
        EvaluationCustomAdaptor adapter = new EvaluationCustomAdaptor(this, questionArrayList);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.lv_Questions);
        Log.d("after adaptor", "test");
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

                for(int i = 0; i < questionArrayList.size() - 1 ; i++)
                {
                    int minSection = i;

                    for(int j = i+1; j < questionArrayList.size() ; j++)
                    {
                        if(questionArrayList.get(j).getQuestion_Section() < questionArrayList.get(i).getQuestion_Section() )
                        {
                            minSection =  j;
                        }
                    }

                    changedPlace = questionArrayList.get(i);
                    questionArrayList.set(i, questionArrayList.get(minSection));
                    questionArrayList.set(minSection, changedPlace);
                }

                Log.d("before", arrayOfQuestions.toString());

                populateSessionList();

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
            Log.d("test", answer);
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

    public void test(View v)
    {


        ListView listView = (ListView) findViewById(R.id.lv_Questions);
        View bo = listView.getChildAt(2);
        khamoosh = (TextView) bo.findViewById(R.id.tv_question_id);
        Log.d("test", "" + khamoosh.getText());
    }
}
