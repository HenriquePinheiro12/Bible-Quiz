package com.example.biblequizz;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;


public class QuestionsActivity extends AppCompatActivity {
    // layout
    Button answerBtn;
    TextView statementText, scoreLbl;
    RadioGroup radioGroup;

    ArrayList<Question> questionList;
    private int QUESTIONS_COUNT;

    Question currentQuestion;
    private int questionIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        answerBtn = findViewById(R.id.answerBtn);
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

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i > 0) {
                    answerBtn.setVisibility(View.VISIBLE);
                }
            }
        });
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
        answerBtn.setVisibility(View.GONE);
    }

    protected void checkAnswer(){
        RadioButton checkedRdo = ( RadioButton) radioGroup.getChildAt(currentQuestion.answerIndex);

        if(checkedRdo.isChecked()){
            Question.increaseScore();
            scoreLbl.setText(Question.getScore() + "");
        }
    }

    protected void endGame(){
        /* TODO: endGame and learn animations */
        System.out.println("End game!");
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
