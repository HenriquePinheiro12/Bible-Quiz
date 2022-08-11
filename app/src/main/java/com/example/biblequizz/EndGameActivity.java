package com.example.biblequizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndGameActivity extends AppCompatActivity {

    TextView finalScoreLbl;
    Button restartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        finalScoreLbl = findViewById(R.id.finalScoreLbl);
        finalScoreLbl.setText(Question.getScore() +"/"+ Question.getQuestionCount());
    }

    public void handleClick (View v) {
        Intent it = new Intent(this, QuestionsActivity.class);
        startActivity(it);
        finish();
    }
}