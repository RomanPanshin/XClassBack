package com.Xjournal.Group.Repo;

import com.Xjournal.Group.Entity.ClGroup;
import com.Xjournal.Group.Entity.Date;
import com.Xjournal.Group.Entity.Lesson;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Scope("singleton")
public class DataRepository{
    final FirebaseDatabase database = FirebaseDatabase.getInstance();

    public void addClasses() {
        DatabaseReference ref = database.getReference();
        DatabaseReference usersRef = ref.child("classes");
        Map<String, ClGroup> classes = new HashMap<>();
        classes.put("11И", new ClGroup(11, "И"));
        classes.put("1А", new ClGroup(1, "A"));
       usersRef.setValueAsync(classes);
    }
    public void addLessons() {
        DatabaseReference ref = database.getReference();
        DatabaseReference usersRef = ref.child("lessones");
        Map<String, Lesson> lessones = new HashMap<>();
        lessones.put("Phisic", new Lesson("Физика", "6P2Q5pdNWWX4yj5h77RF485PFoj1", "11_И", new Date(Date.day.Monday, 1)));
        usersRef.setValueAsync(lessones);
    }

    public void addUser() {
        DatabaseReference ref = database.getReference();
        DatabaseReference usersRef = ref.child("users");
        Map<String, Lesson> users = new HashMap<>();
        users.put("Phisic", new Lesson("Физика", "6P2Q5pdNWWX4yj5h77RF485PFoj1", "11_И", new Date(Date.day.Monday, 1)));
        usersRef.setValueAsync(users);
    }
}
