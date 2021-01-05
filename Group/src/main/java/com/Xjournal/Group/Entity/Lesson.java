package com.Xjournal.Group.Entity;

public class Lesson {
    private String id;
    private String name;
    private String teacher;
    private String idclass;
    private Date date;

    public Lesson(String name, String teacher, String idclass, Date date) {
        this.id = name + "_" + idclass + "_" + date.getNumLesson() + "_" + date.getCode();
        this.name = name;
        this.teacher = teacher;
        this.idclass = idclass;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getIdclass() {
        return idclass;
    }

    public Date getDate() {
        return date;
    }
}

