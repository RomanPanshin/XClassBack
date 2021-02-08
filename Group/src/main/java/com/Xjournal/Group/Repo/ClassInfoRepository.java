package com.Xjournal.Group.Repo;

import com.Xjournal.Group.Entity.ClassInfo;
import com.Xjournal.Group.Entity.Lesson;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
@Scope("singleton")
public class ClassInfoRepository {
    public static final String COL_NAME="classes";

    public void addClassInfo(ClassInfo classInfo) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(classInfo.getId()).set(classInfo);
    }

}

