package com.Xjournal.Group;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class Repo {
    public static final String URI_AUTH_REQUEST = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=AIzaSyAgS9KmnXH3Gc84i0l6D3aPYQsO_KpQnvs";

    public Repo(){
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

        public void createUser(String email,String password, String name) {
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(email)
                    .setPassword(password)
                    .setDisplayName(name);


            UserRecord userRecord = null;
            try {
                userRecord = FirebaseAuth.getInstance().createUser(request);
            } catch (FirebaseAuthException e) {
                e.printStackTrace();
            }
            System.out.println("Successfully created new user: " + userRecord.getUid());
        }

    public static MyUser postReqest(String email, String password){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(URI_AUTH_REQUEST);

    // Request parameters and other properties.

        List<NameValuePair> params = new ArrayList<NameValuePair>(2);
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("password", password));
        params.add(new BasicNameValuePair("returnSecureToken", "true"));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    //Execute and get the response.
        HttpResponse response = null;
        try {
            response = httpclient.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            try (InputStream stream = entity.getContent()) {
                String jsonString =  IOUtils.toString(stream, StandardCharsets.UTF_8.name());
                try {
                    JSONObject obj = new JSONObject(jsonString);
                    try {
                        return new MyUser(FirebaseAuth.getInstance().getUserByEmail(email).getDisplayName(), obj.getString("idToken"));
                    } catch (FirebaseAuthException e) {
                        e.printStackTrace();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new MyUser("", "error");
    }
}
