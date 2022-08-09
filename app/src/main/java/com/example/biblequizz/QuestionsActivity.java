package com.example.biblequizz;

import androidx.annotation.XmlRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.*;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class QuestionsActivity extends AppCompatActivity {
    // layout
    Button answerBtn;
    RadioGroup rdoGroup;
    TextView statementText;
    RadioGroup radioGroup;

    ArrayList<Question> questionList;
    ArrayList<Integer> drawnIndexes = new ArrayList<>();

    int QUESTION_COUNT;

    Question currentQuestion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        answerBtn = findViewById(R.id.answerBtn);
        rdoGroup = findViewById(R.id.radioGroup);
        statementText = findViewById(R.id.statementText);
        radioGroup = findViewById(R.id.radioGroup);


        try {
            questionList = generateQuestionsList();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        QUESTION_COUNT = questionList.size();

        // renderQuestion();
    }

    protected void renderQuestion() {
        int drawnIndex = -1;

        do drawnIndex = (int) Math.ceil(Math.random() * QUESTION_COUNT);
        while(drawnIndexes.contains(drawnIndex));

        drawnIndexes.add(drawnIndex);

        currentQuestion = questionList.get(drawnIndex);

        statementText.setText(currentQuestion.statement);
        int Rdocount = radioGroup.getChildCount();
        ArrayList<RadioButton> listOfRadioButtons = new ArrayList<>();

        for (int i=0; i<Rdocount; i++) {
            RadioButton rdoBtn = (RadioButton) radioGroup.getChildAt(i);
            rdoBtn.setText(currentQuestion.alternatives[i]);
        }
    }


    protected ArrayList<Question> generateQuestionsList() throws XmlPullParserException, IOException {
        ArrayList<Question> questionList = new ArrayList<>();
        String tagName;
        String statement;
        int correctAnswer;
        String[] alternatives = new String[3];
        XmlResourceParser parser = getResources().getXml(R.xml.questions);
        Question q;

        while(parser.getEventType() != XmlResourceParser.END_DOCUMENT){
            if(parser.getEventType() == XmlResourceParser.START_TAG){
                tagName = parser.getName();


                if(tagName.equals("Question")){
                    statement = parser.getAttributeValue(null, "statement");
                    correctAnswer =
                            Integer.parseInt(parser.getAttributeValue(null, "correctIndex"));

                    /*
                     * TODO:
                     *  - render questions logic: don´t allow repeating
                     *  - fix xml parsing - try another logic
                     * */
                    for(int i = 0; i < 3; i++){
                        do parser.next();
                        while(parser.getEventType() != XmlResourceParser.START_TAG);

                        tagName = parser.getName();
                        if(tagName.equals("Alternative")){
                            alternatives[i] =
                                    parser.getAttributeValue(null, "text");
                        }
                    }

                    q =
                            new Question(statement,
                                    alternatives[0],
                                    alternatives[1],
                                    alternatives[2],
                                    correctAnswer);

                    questionList.add(q);
                }
            }

            parser.next();
        }


        return questionList;
    }
}
