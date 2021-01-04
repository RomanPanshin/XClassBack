package com.Xjournal.Group.Repo;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

abstract class Repository {
    public static final String URI_AUTH_REQUEST = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=AIzaSyAgS9KmnXH3Gc84i0l6D3aPYQsO_KpQnvs";
    public Repository()
    {
        FileInputStream serviceAccount =
                null;
        try {
            serviceAccount = new FileInputStream("/Volumes/podarochek/1/Group/serviceAccountKey.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        FirebaseOptions options = null;
        try {
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://journal-da2a5-default-rtdb.firebaseio.com")
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FirebaseApp.initializeApp(options);

    }
}
