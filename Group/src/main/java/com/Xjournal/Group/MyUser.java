package com.Xjournal.Group;

public class MyUser {
    private String name;
    private String key;
    private String error;
    public MyUser(String name, String key){
        this.name =name;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }
}
