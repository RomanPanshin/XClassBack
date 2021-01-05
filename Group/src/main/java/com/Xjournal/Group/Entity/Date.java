package com.Xjournal.Group.Entity;

public class Date{
    public enum day{
        Monday,
        Tuesday,
        Wednesday,
        Thursday,
        Saturday,
        Sunday
    }

    int numLesson;
    private day code;

    public Date(day code,int numLesson) {
        this.code = code;
        this.numLesson = numLesson;
    }

    public int getNumLesson() {
        return numLesson;
    }

    public day getCode() {
        return code;
    }
}
