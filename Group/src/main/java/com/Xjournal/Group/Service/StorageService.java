/*
 * Wrote by Panshin Roman (roma.super@icloud.com) in 13.01.21
 * Storage for Xclass
 * Xclass - mobile study application
 * Copyright (c) 2021, Roman Panshin
 * All rights reserved.
 */
package com.Xjournal.Group.Service;

import com.Xjournal.Group.Entity.Exercise;
import com.Xjournal.Group.Exception.StorageException;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class StorageService {
    public String staticResURL = "http://borovik.fun:8080/r/";
    public static final int ORIGINAL_FILE_NAME = 0;
    public static final int UNIC_FILE_ID = 1;

    @Value("{upload.path}")
    private final String path = "/Volumes/podarochek/1/Storage/";
    public ArrayList<String> uploadFile(MultipartFile file) {
        ArrayList<String> result = new ArrayList<String>();
        if (file.isEmpty()) {
            throw new StorageException("Failed to store empty file");
        }

        try {
            result.add(file.getOriginalFilename());
            String fileName = UUID.randomUUID().toString() + getExtensionByName(result.get(ORIGINAL_FILE_NAME));
            result.add(fileName);
            var is = file.getInputStream();
            Files.copy(is, Paths.get(path + fileName),
                    StandardCopyOption.REPLACE_EXISTING);
            return result;
        }
        catch (IOException e) {
            var msg = String.format("Failed to store file %f", file.getName());
            throw new StorageException(msg, e);
        }
    }

    private String getExtensionByName(String filename) {
        return "."+ FilenameUtils.getExtension(filename);
    }

}
