package com.Xjournal.Group.Entity;

import java.util.UUID;

public class LessonTopic {
    private String lessonId;
    private String topic;
    private String simpleDate;
    private String id;

    public LessonTopic(String lessonId, String topic, String simpleDate) {
        this.lessonId = lessonId;
        this.topic = topic;
        this.simpleDate = simpleDate;
        this.id = UUID.randomUUID().toString();
    }

    public LessonTopic() {}

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

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getSimpleDate() {
        return simpleDate;
    }

    public void setSimpleDate(String simpleDate) {
        this.simpleDate = simpleDate;
    }
}
