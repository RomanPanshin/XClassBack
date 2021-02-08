package com.Xjournal.Group.Entity;

import java.util.ArrayList;
import java.util.UUID;

public class TestResult extends Test{
    private String uId;
    private String id;
    private String testId;
    private double percentage;
    private ArrayList<String> incorrectAnswers;
    public TestResult(){}
    public TestResult(Test test, String uId){
        super(test);
        this.testId = test.getId();
        this.uId = uId;
        this.id = UUID.randomUUID().toString();
        this.incorrectAnswers = new ArrayList<>();
    }

    public void checkBy(Test teacherTest) {
        ArrayList<Question> studentQuestions = super.getQuestions();
        ArrayList<Question> teacherQuestions = teacherTest.getQuestions();
        int i = 0;
        int quantity = teacherQuestions.size();
        int correctAnswers = 0;

        for (Question q : teacherQuestions) {
            if (q.checkWith(studentQuestions.get(i))) {
                correctAnswers++;
            } else {
                this.incorrectAnswers.add(q.getId());
            }
            i++;
        }
        System.out.println(correctAnswers);
        this.percentage = Math.floor(correctAnswers * 100.0 / quantity);
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }


    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public ArrayList<String> getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(ArrayList<String> incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    public TestResult(String description, ArrayList<Question> questions, String lessonId, String date, String uId, String id, double percentage, ArrayList<String> incorrectAnswers) {
        super(description, questions, lessonId, date);
        this.uId = uId;
        this.id = id;
        this.percentage = percentage;
        this.incorrectAnswers = incorrectAnswers;
    }

    public TestResult(String description, ArrayList<Question> questions, String lessonId, String date, String id, String uId, String id1, double percentage, ArrayList<String> incorrectAnswers) {
        super(description, questions, lessonId, date, id);
        this.uId = uId;
        this.id = id1;
        this.percentage = percentage;
        this.incorrectAnswers = incorrectAnswers;
    }

    public TestResult(String uId, String id, double percentage, ArrayList<String> incorrectAnswers) {
        this.uId = uId;
        this.id = id;
        this.percentage = percentage;
        this.incorrectAnswers = incorrectAnswers;
    }

    public TestResult(Test test, String uId, String id, double percentage, ArrayList<String> incorrectAnswers) {
        super(test);
        this.uId = uId;
        this.id = id;
        this.percentage = percentage;
        this.incorrectAnswers = incorrectAnswers;
    }
}
