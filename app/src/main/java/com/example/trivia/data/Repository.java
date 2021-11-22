package com.example.trivia.data;


import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.trivia.controller.AppController;
import com.example.trivia.model.Question;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    ArrayList<Question> questionArrayList = new ArrayList<>();
    String url = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";

    public List<Question> getQuestions(){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url, null, response -> {
            for (int i = 0; i < response.length(); i++) {
                try {
                    Question question = new Question(response.getJSONArray(i).get(0).toString(),response.getJSONArray(i).getBoolean(1));
                    //Add questions to arraylist/list
                    questionArrayList.add(question);


//                    Log.d("hello", "getQuestions: "+ questionArrayList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, error ->{

        });

        AppController.getInstance().addToRequestQueue(jsonArrayRequest);


        return questionArrayList;
    }

}
