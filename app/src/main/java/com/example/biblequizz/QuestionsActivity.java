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
    TextView statementText, scoreLbl;
    RadioGroup radioGroup;
    /*
    * TODO: add an OnCheckedChange listener on RadioGroup to show "next" button when something is selected
    * */



    ArrayList<Question> questionList;
//    ArrayList<Integer> drawnIndexes = new ArrayList<>();

    private int QUESTIONS_COUNT;

    Question currentQuestion;
    private int questionIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        answerBtn = findViewById(R.id.answerBtn);
        rdoGroup = findViewById(R.id.radioGroup);
        statementText = findViewById(R.id.statementText);
        radioGroup = findViewById(R.id.radioGroup);
        scoreLbl = findViewById(R.id.scoreLbl);

        scoreLbl.setText(Question.getScore() + "");

        try {
            questionList = generateQuestionsList();
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }

        QUESTIONS_COUNT = questionList.size();

        updateQuestion();
    }

    protected void updateQuestion() {
        currentQuestion = questionList.get(questionIndex);

        statementText.setText(currentQuestion.statement);
        int rdoCount = radioGroup.getChildCount();
        for (int i=0; i < rdoCount; i++) {
            RadioButton rdoBtn = (RadioButton) radioGroup.getChildAt(i);
            rdoBtn.setText(currentQuestion.alternatives[i]);
        }
        questionIndex++;

        radioGroup.clearCheck();
    }

    protected void checkAnswer(){
        RadioButton checkedRdo = ( RadioButton) radioGroup.getChildAt(currentQuestion.answerIndex);

        if(checkedRdo.isChecked()){
            Question.increaseScore();
            scoreLbl.setText(Question.getScore() + "");
        }
    }

    protected void endGame(){

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

    public void handleClick(View view) {
        checkAnswer();
        if(questionIndex == QUESTIONS_COUNT){
            endGame();
            return;
        }
        updateQuestion();
    }
}
