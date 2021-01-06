package com.Xjournal.Group.Entity;

public class MyUser {
    private String name;
    private String key;
    private String claim;
    private String uId;
    private String classId;

    public MyUser() {
    }

    public MyUser(String name, String key, String claim, String uId, String classId){
        this.name =name;
        this.key = key;
        this.claim = claim;
        this.uId = uId;
        this.classId = classId;
    }

    public String getClassId() { return classId; }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public String getClaim() {
        return claim;
    }

    public String getuId() {
        return uId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setClaim(String claim) {
        this.claim = claim;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }
}
