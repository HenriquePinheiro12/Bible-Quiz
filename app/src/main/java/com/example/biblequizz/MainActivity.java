package com.example.biblequizz;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.*;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Button startGameBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startGameBtn = findViewById(R.id.stratBtn);
    }

    public void handleClick(View view){

    }
}