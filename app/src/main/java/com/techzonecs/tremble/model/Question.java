package com.techzonecs.tremble.model;

/**
 * Created by Gaffas on 03/11/2015.
 */
public class Question {

    private String question;
    private int question_Id;
    private int question_Section;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getQuestion_Id() {
        return question_Id;
    }

    public void setQuestion_Id(int question_Id) {
        this.question_Id = question_Id;
    }

    public int getQuestion_Section() {
        return question_Section;
    }

    public void setQuestion_Section(int question_Section) {
        this.question_Section = question_Section;
    }
}
