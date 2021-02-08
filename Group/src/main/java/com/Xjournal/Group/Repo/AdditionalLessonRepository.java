package com.Xjournal.Group.Repo;

import com.Xjournal.Group.Entity.AdditionalLesson;
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
public class AdditionalLessonRepository {
    public static final String COL_NAME="additional_lesson";
    public void sendALessonToDB(AdditionalLesson additionalLesson){
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(additionalLesson.getId()).set(additionalLesson);
        try {
            System.out.println("Update time : " + collectionsApiFuture.get().getUpdateTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<AdditionalLesson> getByClassId(String classId) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference cities = dbFirestore.collection(COL_NAME);

        Query query = cities.whereEqualTo("classId", classId);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        ArrayList<AdditionalLesson> result = new ArrayList<>();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            AdditionalLesson obj = document.toObject(AdditionalLesson.class);
            result.add(obj);
        }
        return result;
    }
}
