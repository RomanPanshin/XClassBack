package com.Xjournal.Group.Entity;

public class ClassInfo {
    private String id;
    private String grade;
    private String letter;

    public ClassInfo() {
    }

    public ClassInfo(String grade, String letter) {
        this.grade = grade;
        this.letter = letter;
        this.id =  grade +letter;
    }

    public String getId() {
        return id;
    }

    public String getGrade() {
        return grade;
    }

    public String getLetter() {
        return letter;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }
}
