package com.example.biblequizz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.*;

public class Questions extends AppCompatActivity {

    Button answerBtn;
    RadioGroup rdoGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        answerBtn = findViewById(R.id.answerBtn);
        rdoGroup = findViewById(R.id.radioGroup);
    }
}