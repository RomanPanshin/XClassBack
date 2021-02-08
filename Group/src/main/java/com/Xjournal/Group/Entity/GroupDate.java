package com.Xjournal.Group.Entity;

public class GroupDate {
    public enum DayOfWeek {
        Monday,
        Tuesday,
        Wednesday,
        Thursday,
        Friday,
        Saturday,
        Sunday
    }

    int numLesson;
    private DayOfWeek code;

    public GroupDate() {

    }

    public GroupDate(DayOfWeek code, int numLesson) {
        this.code = code;
        this.numLesson = numLesson;
    }

    public int getNumLesson() {
        return numLesson;
    }

    public DayOfWeek getCode() {
        return code;
    }

    public void setNumLesson(int numLesson) {
        this.numLesson = numLesson;
    }

    public void setCode(DayOfWeek code) {
        this.code = code;
    }
}
