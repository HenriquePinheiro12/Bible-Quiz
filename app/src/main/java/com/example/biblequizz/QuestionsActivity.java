package com.example.biblequizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class QuestionsActivity extends AppCompatActivity {
    // layout
    Button answerBtn;
    RadioGroup rdoGroup;
    TextView statementText;
    RadioGroup radioGroup;

    ArrayList<Question> questionList;
    // XmlResourceParser parser = getResources(R.xml.questions);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        answerBtn = findViewById(R.id.answerBtn);
        rdoGroup = findViewById(R.id.radioGroup);
        statementText = findViewById(R.id.statementText);
        radioGroup = findViewById(R.id.radioGroup);



        questionList = generateQuestionsList();

        final int QUESTION_COUNT = questionList.size();

        double drawnNumber = Math.random();
        renderQuestion ();

    }

    protected void renderQuestion () {
        statementText.setText(questionList.get(4).statement);
        int count = radioGroup.getChildCount();
        ArrayList<RadioButton> listOfRadioButtons = new ArrayList<RadioButton>();

        for (int i=0;i<count;i++) {
            RadioButton rdoBtn = (RadioButton) radioGroup.getChildAt(i);
            rdoBtn.setText(questionList.get(4).alternatives[i]);

        }
    }


    private ArrayList<Question> generateQuestionsList(){
        ArrayList<Question> questionList = new ArrayList<>();



        return questionList;
    }
}
