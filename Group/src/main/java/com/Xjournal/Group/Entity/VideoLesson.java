package com.Xjournal.Group.Entity;

import java.util.HashMap;


public class VideoLesson {
    private String id;
    private String lessonId;
    private HashMap<String, Boolean> PresentStudents;
    private String simpleDate;
    private String teacherId;

    public VideoLesson(){}

    public VideoLesson(String id, String lessonId, HashMap<String, Boolean> presentStudents, String simpleDate, String teacherId) {
        this.id = id;
        this.lessonId = lessonId;
        PresentStudents = presentStudents;
        this.simpleDate = simpleDate;
        this.teacherId = teacherId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public HashMap<String, Boolean> getPresentStudents() {
        return PresentStudents;
    }

    public void setPresentStudents(HashMap<String, Boolean> presentStudents) {
        PresentStudents = presentStudents;
    }

    public String getSimpleDate() {
        return simpleDate;
    }

    public void setSimpleDate(String simpleDate) {
        this.simpleDate = simpleDate;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "VideoLesson{" +
                "id='" + id + '\'' +
                ", lessonId='" + lessonId + '\'' +
                ", PresentStudents=" + PresentStudents +
                ", simpleDate='" + simpleDate + '\'' +
                ", teacherId='" + teacherId + '\'' +
                '}';
    }
}
