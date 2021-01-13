package com.Xjournal.Group.Entity;

public class Exercise {
    private String Id;
    private String lessonId;
    private String description;
    private String filename;
    private String fileURL;
    private String simpleDate;

    public Exercise(){}

    public Exercise(String id, String lessonId, String description, String filename, String fileURL, String simpleDate) {
        this.Id = id;
        this.lessonId = lessonId;
        this.description = description;
        this.filename = filename;
        this.fileURL = fileURL;
        this.simpleDate = simpleDate;
    }

    public String getId() {
        return Id;
    }

    public String getLessonId() {
        return lessonId;
    }

    public String getDescription() {
        return description;
    }

    public String getFilename() {
        return filename;
    }

    public String getFileURL() {
        return fileURL;
    }

    public String getSimpleDate() {
        return simpleDate;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setFileURL(String fileURL) {
        this.fileURL = fileURL;
    }

    public void setSimpleDate(String simpleDate) {
        this.simpleDate = simpleDate;
    }
}
