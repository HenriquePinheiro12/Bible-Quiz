package com.example.biblequizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Questions extends AppCompatActivity {
    // layout
    Button answerBtn;
    RadioGroup rdoGroup;

    Resources res;

    ArrayList<Question> questionList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        answerBtn = findViewById(R.id.answerBtn);
        rdoGroup = findViewById(R.id.radioGroup);

        questionList = new ArrayList<Question>();
        res = getBaseContext().getResources();
    }

    protected void populateList () {
        // create objects based on string resources
        // populate ArrayList with them

        // how to access property by string?
    }
}
