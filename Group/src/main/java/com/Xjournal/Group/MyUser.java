package com.Xjournal.Group;

public class MyUser {
    private String name;
    private String password;
    public MyUser(String name, String password){
        this.name =name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
