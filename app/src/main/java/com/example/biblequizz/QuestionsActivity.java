package com.example.biblequizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
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

        Question q1 =
                new Question("Em quantos dias Deus fez o universo?",
                            "7 dias", "6 dias", "1 dia", 1);
        questionList.add(q1);

        Question q2 =
                new Question("Quem foi pai de José?",
                        "Israel", "Jerusalém", "Moisés", 0);
        questionList.add(q2);

        Question q3 =
                new Question("Quantos anos durou o cativeiro babilônico?",
                        "400 anos", "40 anos", "70 anos", 2);
        questionList.add(q3);

        Question q4 =
                new Question("Quem escreveu o livro de Lamentações?",
                        "Salomão", "Jeremias", "Davi", 1);
        questionList.add(q4);

        Question q5 =
                new Question("Quem escreveu o livro de Lamentações?",
                        "Salomão", "Jeremias", "Davi", 1);
        questionList.add(q5);

        return questionList;
    }
}
