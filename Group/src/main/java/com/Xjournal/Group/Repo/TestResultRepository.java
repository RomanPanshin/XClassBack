package com.Xjournal.Group.Repo;

import com.Xjournal.Group.Entity.Test;
import com.Xjournal.Group.Entity.TestResult;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@Component
@Scope("singleton")
public class TestResultRepository {
    public static final String COL_NAME="tests_results";
    public void sendTestResToDB(TestResult testResult){
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(testResult.getId()).set(testResult);
        try {
            System.out.println("Update time : " + collectionsApiFuture.get().getUpdateTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<TestResult> getResTests(String testId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference tests = dbFirestore.collection(COL_NAME);

        Query query = tests.whereEqualTo("testId", testId);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        ArrayList<TestResult> result = new ArrayList<TestResult>();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            TestResult obj = document.toObject(TestResult.class);
            result.add(obj);
        }
        return result;
    }
}
