package com.Xjournal.Group.Repo;

import com.Xjournal.Group.Entity.Exercise;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;


@Component
@Scope("singleton")
public class ExerciseRepository {
    public static final String COL_NAME="exercises";

    public void sendExerciseToDB(Exercise ex){
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(ex.getId()).set(ex);
        try {
            System.out.println("Update time : " + collectionsApiFuture.get().getUpdateTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
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
