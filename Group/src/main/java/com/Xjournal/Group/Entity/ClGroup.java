package com.Xjournal.Group.Entity;

public class ClGroup {
    private String id;
    private int grade;
    private String letter;

    public ClGroup(int grade, String letter) {
        this.grade = grade;
        this.letter = letter;
        this.id = String.format("%d_%s", grade, letter);
    }

    public String getId() {
        return id;
    }

    public int getGrade() {
        return grade;
    }

    public String getLetter() {
        return letter;
    }
}
