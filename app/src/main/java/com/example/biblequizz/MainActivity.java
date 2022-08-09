package com.example.biblequizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Button startGameBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startGameBtn = findViewById(R.id.startBtn);
    }

    public void handleClick(View view){
        Intent it = new Intent(getBaseContext(), QuestionsActivity.class);
        startActivity(it);
    }
}