package com.Xjournal.Group.Entity;

public class Homework {
    private String Id;
    private String exerciseId;
    private String description;
    private String filename;
    private String fileURL;
    private String UID;

    public Homework() {}

    public Homework(String id, String exerciseId, String description, String filename, String fileURL, String UID) {
        Id = id;
        this.exerciseId = exerciseId;
        this.description = description;
        this.filename = filename;
        this.fileURL = fileURL;
        this.UID = UID;
    }

    public String getId() {
        return Id;
    }

    public String getExerciseId() {
        return exerciseId;
    }

    public String getDescription() {
        return description;
    }

    public String getFilename() {
        return filename;
    }

    public String getUID() {
        return UID;
    }

    public String getFileURL() {
        return fileURL;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setExerciseId(String exerciseId) {
        this.exerciseId = exerciseId;
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

    public void setUID(String UID) {
        this.UID = UID;
    }
}
