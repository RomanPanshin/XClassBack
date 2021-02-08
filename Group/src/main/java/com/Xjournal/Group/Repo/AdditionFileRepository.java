package com.Xjournal.Group.Repo;


import com.Xjournal.Group.Entity.AdditionalFile;
import com.Xjournal.Group.Entity.AdditionalLesson;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@Component
@Scope("singleton")
public class AdditionFileRepository {
    public static final String COL_NAME="additional_file";
    public void sendFileToDB(AdditionalFile additionalFile){
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(additionalFile.getId()).set(additionalFile);
        try {
            System.out.println("Update time : " + collectionsApiFuture.get().getUpdateTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<AdditionalFile> getByALessonId(String ALessonId) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference cities = dbFirestore.collection(COL_NAME);

        Query query = cities.whereEqualTo("alessonId", ALessonId);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        ArrayList<AdditionalFile> result = new ArrayList<>();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            AdditionalFile obj = document.toObject(AdditionalFile.class);
            result.add(obj);
        }
        return result;
    }
}
