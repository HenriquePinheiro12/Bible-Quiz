package com.example.biblequizz;

public class Question {
    public Question(String stt, String alt1, String alt2, String alt3, int answerI) {
        this.alternatives = new String[3];

        this.statement = stt;
        this.alternatives[0] = alt1;
        this.alternatives[1] = alt2;
        this.alternatives[2] = alt3;

        this.answerIndex = answerI;
    }

    private String statement;
    private String[] alternatives;
    private int answerIndex;
    private static int score;
    private static int questionIndex;
    private static int questionCount;

    public String getStatement() {
        return this.statement;
    }

    public String getAlternative(int i) {
        return this.alternatives[i];
    }

    public int getAnswerIndex() {
        return this.answerIndex;
    }

    public static void increaseScore() {
        score++;
    }

    public static int getScore() {
        return score;
    }

    public static void increaseIndex() {
        questionIndex++;
    }

    public static int getQuestionIndex() {
        return questionIndex;
    }

    public static int getQuestionIndex(int increaser) {
        return questionIndex + increaser;
    }

    public static int getQuestionCount() {
        return questionCount;
    }

    public static void setQuestionCount(int c) {
        questionCount = c;
    }

    public static void resetQuestions() {
        score = 0;
        questionIndex = 0;
        questionCount = 0;
    }
}

/*
*   statement: "",
*   option1: "",
*   option2: "",
*   option3: "",
*   index
*/