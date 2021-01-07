package com.Xjournal.Group.Repo;
import com.Xjournal.Group.Entity.MyUser;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.cloud.FirestoreClient;
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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Component
@Scope("singleton")
public class UserRepository extends Repository {
    private static final String COL_NAME="users";

    public static final String STUDENT = "student";
    public static final String TEACHER = "teacher";
    public static final String ADMIN = "admin";

    public void createUser(String email, String password, String name, String claim) {
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
            addClaims(userRecord, claim);
            System.out.println("Successfully created new user: " + userRecord.getUid());
        }

        public String getClassid(String uid){
            Firestore dbFirestore = FirestoreClient.getFirestore();
            CollectionReference cities = dbFirestore.collection(COL_NAME);

            Query query = cities.whereEqualTo("uId", uid);
            ApiFuture<QuerySnapshot> querySnapshot = query.get();
            try {
                MyUser user = (MyUser) querySnapshot.get().toObjects(MyUser.class).get(0);

                return user.getClassId();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return null;
        }

        public void addClaims(UserRecord user, String claim){
            Map<String, Object> claims = new HashMap<>();
                claims.put(claim, true);
            try {
                FirebaseAuth.getInstance().setCustomUserClaims(user.getUid(), claims);
            } catch (FirebaseAuthException e) {
                e.printStackTrace();
            }
        }

        private String getClaims(String email){
            UserRecord user = null;
            try {
                user = FirebaseAuth.getInstance().getUserByEmail(email);
            } catch (FirebaseAuthException e) {
                e.printStackTrace();
            }
            Map<String, Object> currentClaims = user.getCustomClaims();
            return (String) currentClaims.keySet().iterator().next();
        }

        public static String getNamebyUid(String uid){
            UserRecord user = null;
            try {
                user = FirebaseAuth.getInstance().getUser(uid);
                return user.getDisplayName();
            } catch (FirebaseAuthException e) {
                e.printStackTrace();
            }
            return null;
        }

    public MyUser postReqest(String email, String password){
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
                   // System.out.println(jsonString);
                    JSONObject obj = new JSONObject(jsonString);
                    try {
                        UserRecord userByEmail = FirebaseAuth.getInstance().getUserByEmail(email);
                        String uid = userByEmail.getUid();
                        return new MyUser(userByEmail.getDisplayName(),
                                obj.getString("idToken"),
                                getClaims(email),
                                uid,
                                getClassid(uid));
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
        //not found
        return null;
    }
}
