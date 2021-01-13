package com.Xjournal.Group.Entity;

import java.util.UUID;

public class Lesson {
    private String id;
    private String name;
    private String teacher;
    private String idclass;
    private GroupDate groupDate;

    public String getName() {
        return name;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getIdclass() {
        return idclass;
    }

    public GroupDate getDate() {
        return groupDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setIdclass(String idclass) {
        this.idclass = idclass;
    }

    public void setDate(GroupDate groupDate) {
        this.groupDate = groupDate;
    }

    public Lesson() {

    }


    public Lesson(String name, String teacher, String idclass, GroupDate groupDate) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.teacher = teacher;
        this.idclass = idclass;
        this.groupDate = groupDate;
    }

    public String getId() {
        return id;
    }


    @Override
    public String toString() {
        return "Lesson{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", teacher='" + teacher + '\'' +
                ", idclass='" + idclass + '\'' +
                ", date=" + groupDate +
                '}';
    }



}

