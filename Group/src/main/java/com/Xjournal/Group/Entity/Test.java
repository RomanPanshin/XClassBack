package com.Xjournal.Group.Entity;

import java.util.ArrayList;
import java.util.UUID;

public class Test {
    private String description;
    private ArrayList<Question> questions;
    private String lessonId;
    private String id;
    private String date;

    public String getDescription() {
        return description;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Test(String description, ArrayList<Question> questions, String lessonId, String date) {
        this.description = description;
        this.questions = questions;
        this.lessonId = lessonId;
        this.date = date;
        this.id = UUID.randomUUID().toString();
    }

    public void toFalse(){
        ArrayList<Question> questions = new ArrayList<>();
        for(Question q : this.questions){
            q.toFalse();
            questions.add(q);
        }
        this.questions = questions;
    }

    public String getId() {
        return id;
    }

    public Test(){
        this.questions = new ArrayList<Question>();
    }

    public void setId(String id) {
        this.id = id;
    }
}
