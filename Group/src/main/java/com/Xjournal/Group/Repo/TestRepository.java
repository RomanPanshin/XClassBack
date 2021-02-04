package com.Xjournal.Group.Repo;

import com.Xjournal.Group.Entity.Homework;
import com.Xjournal.Group.Entity.Question;
import com.Xjournal.Group.Entity.Test;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@Component
@Scope("singleton")
public class TestRepository {
    public static final String COL_NAME="tests";
    public void sendTestToDB(Test test){
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(test.getId()).set(test);
        try {
            System.out.println("Update time : " + collectionsApiFuture.get().getUpdateTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Test> getTests(String lessonId, String date) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference tests = dbFirestore.collection(COL_NAME);

        Query query = tests.whereEqualTo("lessonId", lessonId).whereEqualTo("date", date);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        ArrayList<Test> result = new ArrayList<Test>();
            for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
                Test obj = document.toObject(Test.class);
                result.add(obj);
            }

        return result;
    }

    public Test getTestForSolve(String id) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference tests = dbFirestore.collection(COL_NAME);

        Query query = tests.whereEqualTo("id", id);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        ArrayList<Test> testArray = new ArrayList<Test>();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
                Test obj = document.toObject(Test.class);
                testArray.add(obj);
            }

        Test test = testArray.get(0);
        test.toFalse();
        return test;
    }

}
