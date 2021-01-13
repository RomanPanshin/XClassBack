package com.Xjournal.Group.Repo;

import com.Xjournal.Group.Entity.GroupDate;
import com.Xjournal.Group.Entity.Lesson;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@Component
@Scope("singleton")
public class LessonRepository {
    public static final String COL_NAME="lessons";

    public void addLessonDetails(Lesson lesson) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(lesson.getId()).set(lesson);
    }

    public ArrayList<Lesson> getForClassIdAndDay(String classId, GroupDate.DayOfWeek day) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference cities = dbFirestore.collection(COL_NAME);

        Query query = cities.whereEqualTo("idclass", classId).whereEqualTo("date.code",day).orderBy("date.numLesson");
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        ArrayList<Lesson> result = new ArrayList<Lesson>();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            Lesson obj = document.toObject(Lesson.class);
            obj.setTeacher(UserRepository.getNamebyUid(obj.getTeacher()));
            result.add(obj);
        }
        return result;
    }

    public ArrayList<Lesson> getForTeacherIdAndDay(String uId, GroupDate.DayOfWeek day) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference cities = dbFirestore.collection(COL_NAME);

        Query query = cities.whereEqualTo(UserRepository.TEACHER, uId).whereEqualTo("date.code",day).orderBy("date.numLesson");
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        ArrayList<Lesson> result = new ArrayList<Lesson>();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            Lesson obj = document.toObject(Lesson.class);
            obj.setTeacher(UserRepository.getNamebyUid(obj.getTeacher()));
            result.add(obj);
        }
        return result;
    }

    public Lesson getLessonDetails(String name) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(name);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Lesson lesson = null;

        if(document.exists()) {
            lesson = document.toObject(Lesson.class);
            return lesson;
        }else {
            return null;
        }
    }

    public String updatePatientDetails(Lesson lesson) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(lesson.getId()).set(lesson);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public void deletePatient(String name) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(name).delete();
    }
}
