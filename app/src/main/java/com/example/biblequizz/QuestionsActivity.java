package com.example.biblequizz;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class QuestionsActivity extends AppCompatActivity {
    // layout
    Button answerBtn;
    TextView statementText, scoreLbl, questionsCountLbl;
    RadioGroup radioGroup;

    // data
    ArrayList<Question> questionList;
    Question currentQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        // COMPONENTS
        answerBtn = findViewById(R.id.answerBtn);
        statementText = findViewById(R.id.statementText);
        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i > 0) answerBtn.setVisibility(View.VISIBLE);
            }
        });
        scoreLbl = findViewById(R.id.scoreLbl);
        questionsCountLbl = findViewById(R.id.questionsCountLbl);

        // DATA
        try {
            questionList = generateQuestionsList();
            Collections.shuffle(questionList);
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        int count = questionList.size();
        Question.resetQuestions(); // reset score, index and count
        Question.setQuestionCount(count);
        updateQuestion();

        // RENDERING
        scoreLbl.setText(Question.getScore() + "");

    }

    protected ArrayList<Question> generateQuestionsList() throws XmlPullParserException, IOException {
        ArrayList<Question> provisoryQuestionList = new ArrayList<>();
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

                    provisoryQuestionList.add(q);
                }
            }

            parser.next();
        }
        return provisoryQuestionList;
    }

    public void handleClick(View view) {
        checkAnswer();
        if(Question.getQuestionIndex() == Question.getQuestionCount()){
            endGame();
            return;
        }
        updateQuestion();
    }

    protected void checkAnswer(){
        RadioButton checkedRdo = (RadioButton) radioGroup.getChildAt(currentQuestion.getAnswerIndex());

        if(checkedRdo.isChecked()){
            Question.increaseScore();
            scoreLbl.setText(Question.getScore() + "");
            System.out.println("SCORE: "+ Question.getScore());
        }
    }

    protected void updateQuestion() {
        currentQuestion = questionList.get(Question.getQuestionIndex());

        statementText.setText(currentQuestion.getStatement());
        int rdoCount = radioGroup.getChildCount();
        for (int i=0; i < rdoCount; i++) {
            RadioButton rdoBtn = (RadioButton) radioGroup.getChildAt(i);
            rdoBtn.setText(currentQuestion.getAlternative(i));
        }

        questionsCountLbl.setText(Question.getQuestionIndex(1) + "/" + Question.getQuestionCount());
        Question.increaseIndex();

        radioGroup.clearCheck();
        answerBtn.setVisibility(View.GONE);
    }

    protected void endGame(){
        Intent it = new Intent(this, EndGameActivity.class);
        startActivity(it);
        finish();
    }
}
