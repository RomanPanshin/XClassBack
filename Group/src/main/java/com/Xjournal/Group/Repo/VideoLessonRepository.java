package com.Xjournal.Group.Repo;

import com.Xjournal.Group.Entity.AdditionalFile;
import com.Xjournal.Group.Entity.VideoLesson;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


@Component
@Scope("singleton")
public class VideoLessonRepository {
    public static final String COL_NAME="video_lessons_results";
    public void sendFileToDB(VideoLesson videoLesson){
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(videoLesson.getId()).set(videoLesson);
        try {
            System.out.println("Update time : " + collectionsApiFuture.get().getUpdateTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public VideoLesson getByLessonId(String lessonId) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference cities = dbFirestore.collection(COL_NAME);

        Query query = cities.whereEqualTo("lessonId", lessonId);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        ArrayList<VideoLesson> result = new ArrayList<>();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            VideoLesson obj = document.toObject(VideoLesson.class);
            result.add(obj);
        }
        return result.get(0);
    }
}
