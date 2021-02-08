package com.Xjournal.Group.Repo;

import com.Xjournal.Group.Entity.Exercise;
import com.Xjournal.Group.Entity.Homework;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
@Scope("singleton")
public class HomeworkRepository {
    public static final String COL_NAME="homeworks";
    public void sendHomeworkToDB(Homework homework){
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(homework.getId()).set(homework);
        try {
            System.out.println("Update time : " + collectionsApiFuture.get().getUpdateTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Homework> getHomeworksByExerciseId(String exerciseId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference homeworks = dbFirestore.collection(COL_NAME);
        Query query = homeworks.whereEqualTo("exerciseId", exerciseId);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        ArrayList<Homework> result = new ArrayList<Homework>();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            Homework obj = document.toObject(Homework.class);
            result.add(obj);
        }
        return result;
    }

    public Exercise getExerciseByDateAndLessonId(String simpleDate, String lessonId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference exercises = dbFirestore.collection(COL_NAME);
        Query query = exercises.whereEqualTo("simpleDate", simpleDate).whereEqualTo("lessonId",lessonId);
        List<QueryDocumentSnapshot> documentSnapshots = null;
        documentSnapshots = query.get().get().getDocuments();
        if(documentSnapshots.isEmpty()){
            System.out.println("Не найдено задание");
            return null;
        }
        return documentSnapshots.get(0).toObject(Exercise.class);
    }
}
