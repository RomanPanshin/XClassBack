//package com.Xjournal.Group.Repo;
//
//import com.Xjournal.Group.Entity.ClassInfo;
//import com.Xjournal.Group.Entity.Date;
//import com.Xjournal.Group.Entity.Lesson;
//import com.Xjournal.Group.Entity.MyUser;
//import com.google.firebase.database.*;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//@Scope("singleton")
//public class DataRepository{
//    final FirebaseDatabase database = FirebaseDatabase.getInstance();
//
//    public void read(){
//        DatabaseReference scoresRef = database.getReference("lessones/Phisic");
//        scoresRef.orderByValue().addListenerForSingleValueEvent(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                try {
//                    Lesson a = dataSnapshot.getValue(Lesson.class);
//                    System.out.println(a.getTeacher());
//                }catch (Exception e)
//                {
//                    System.out.println(e);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//
//        });
//    }
//
//    public void addClasses() {
//        DatabaseReference ref = database.getReference();
//        DatabaseReference usersRef = ref.child("classes");
//        Map<String, ClassInfo> classes = new HashMap<>();
//        classes.put("11И", new ClassInfo(11, "И"));
//        classes.put("1А", new ClassInfo(1, "A"));
//       usersRef.setValueAsync(classes);
//    }
//    public void addLessons() {
//        DatabaseReference ref = database.getReference();
//        DatabaseReference usersRef = ref.child("lessones");
//        Map<String, Lesson> lessones = new HashMap<>();
//        lessones.put("Phisic", new Lesson("Физика", "6P2Q5pdNWWX4yj5h77RF485PFoj1", "11_И", new Date(Date.DayOfWeek.Monday, 1)));
//        usersRef.setValueAsync(lessones);
//    }
//
//    public void addUser() {
//        DatabaseReference ref = database.getReference();
//        DatabaseReference usersRef = ref.child("users/admin");
//        Map<String, MyUser> users = new HashMap<>();
////        users.put(UserRepository.STUDENT + " 1", new MyUser("Александра Бархоатова", null, UserRepository.STUDENT,"5OeejwAv4dMS235gf8YgXjsh1oc2"));
////        users.put(UserRepository.STUDENT + " 2", new MyUser("Александр Романюк", null, UserRepository.STUDENT,"E7vv5iPUt9QLgr70GrZlVTJkDXD3"));
//
////        users.put(UserRepository.TEACHER, new MyUser("Елена Сотскова", null, UserRepository.TEACHER,"bNpbxGcw7vgDM6NQWMwuWnQCnis2"));
////        users.put(UserRepository.TEACHER + "2" , new MyUser("Анастасия Таразевич", null, UserRepository.TEACHER,"6P2Q5pdNWWX4yj5h77RF485PFoj1"));
////        users.put(UserRepository.ADMIN , new MyUser("Паньшин Роман", null, UserRepository.ADMIN,"JnomN2C1SXc9c0ysx7PcmzOrxhq2", null));
//        usersRef.setValueAsync(users);
//    }
//}
