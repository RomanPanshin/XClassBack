package com.Xjournal.Group.Entity;

public class MyUser {
    private String name;
    private String key;
    private String claim;
    private String uId;

    public MyUser(String name, String key, String claim, String uId){
        this.name =name;
        this.key = key;
        this.claim = claim;
        this.uId = uId;
    }

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
}
