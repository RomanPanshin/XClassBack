package com.Xjournal.Group.Entity;

import java.util.HashMap;
import java.util.UUID;

public class Question {
    private String description;
    private HashMap<String, Boolean> answers;
    private String id;

    public String getDescription() {
        return description;
    }

    public HashMap<String, Boolean> getAnswers() {
        return answers;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAnswers(HashMap<String, Boolean> answers) {
        this.answers = answers;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Question(String description, HashMap<String, Boolean> answers){
        this.description = description;
        this.answers = answers;
        this.id = UUID.randomUUID().toString();
    }
    public Question(String description, HashMap<String, Boolean> answers, String id){
        this.description = description;
        this.answers = answers;
        this.id = id;
    }

    public void toFalse(){
        HashMap<String, Boolean> answers = new HashMap<>();
        for (String key : this.answers.keySet()) {
            answers.put(key, false);
        }
        this.answers = answers;
    }

    public boolean checkWith(Question q){
        HashMap<String, Boolean> studentAnswers = q.getAnswers();
        HashMap<String, Boolean> teacherAnswers = this.getAnswers();
        boolean result = true;
        System.out.println("----");
        System.out.println(studentAnswers);
        System.out.println(teacherAnswers);
        System.out.println("----");
        for (String key : teacherAnswers.keySet()) {

            result = (teacherAnswers.get(key) == studentAnswers.get(key)) && result;
        }
        return result;
    }

    @Override
    public String toString() {
        return "Question{" +
                "description='" + description + '\'' +
                ", answers=" + answers +
                ", id='" + id + '\'' +
                '}';
    }

    public Question(){}
}
