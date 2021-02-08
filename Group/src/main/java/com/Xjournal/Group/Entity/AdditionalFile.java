package com.Xjournal.Group.Entity;

import java.util.UUID;

public class AdditionalFile {
    private String fileUrl;
    private String id;
    private String fileName;
    private String Description;
    private String ALessonId;

    public AdditionalFile(){}

    public AdditionalFile(String ALessonId, String description, String fileName, String fileUrl){
        this.ALessonId = ALessonId;
        this.fileName = fileName;
        this.Description = description;
        this.fileUrl = fileUrl;
        this.id = UUID.randomUUID().toString();
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getALessonId() {
        return ALessonId;
    }

    public void setALessonId(String ALessonId) {
        this.ALessonId = ALessonId;
    }
}
