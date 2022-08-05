package com.example.biblequizz;


import android.content.res.Resources;

public class Question {


    public Question (String stt, String alt1, String alt2, String alt3,int answerI) {
        this.alternatives = new String[3];

        this.statement = stt;
        this.alternatives[0] = alt1;
        this.alternatives[1] = alt2;
        this.alternatives[2] = alt3;

        this.answerIndex = answerI;
    }

    public String statement;
    public String[] alternatives;
    public int answerIndex;

}


/*
*   statement: "",
*   option1: "",
*   option2: "",
*   option3: "",
*   index
*/