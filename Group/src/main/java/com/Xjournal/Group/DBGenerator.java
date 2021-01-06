package com.Xjournal.Group;

import com.Xjournal.Group.Entity.ClassInfo;
import com.Xjournal.Group.Entity.Lesson;
import com.Xjournal.Group.Entity.MyUser;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class DBGenerator {
    private static final String COL_NAME="classes";
    public void Generate(ClassInfo myUser) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(myUser.getId()).set(myUser);
    }


    public void Clear(){


    }
}
