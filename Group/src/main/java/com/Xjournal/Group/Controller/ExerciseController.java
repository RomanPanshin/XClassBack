/*
 * Wrote by Panshin Roman (roma.super@icloud.com) in 13.01.21
 * ExersicseController for Xclass
 * Xclass - mobile study application
 * Copyright (c) 2021, Roman Panshin
 * All rights reserved.
 */
package com.Xjournal.Group.Controller;


import com.Xjournal.Group.Entity.Exercise;
import com.Xjournal.Group.Entity.Result;
import com.Xjournal.Group.Exception.StorageException;
import com.Xjournal.Group.Service.Storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.UUID;


@RestController
public class ExerciseController {
    @Autowired
    private Storage storageService;
    private String staticResURL;

    {
        try {
            staticResURL ="http://" + Inet4Address.getLocalHost().getHostAddress() + ":8080" + "/r/";
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/UploadExerciseWithFile", method = RequestMethod.POST,
            consumes = {"multipart/form-data"})
    public Result<String> uploadExerciseWithFile(@RequestParam MultipartFile file, @RequestParam(value = "lessonId") String lessonId,
                                 @RequestParam(value = "simpleDate") String simpleDate, @RequestParam(value = "description") String description) {
        ArrayList<String> names = storageService.uploadFile(file);
        Exercise ex = new Exercise(UUID.randomUUID().toString(), lessonId, description,
                names.get(Storage.ORIGINAL_FILE_NAME), staticResURL + names.get(Storage.UNIC_FILE_ID),
                simpleDate);
        storageService.sendExerciseToDB(ex);
        return new Result<String>(Result.ResultEnum.Success, "");
    }

    @RequestMapping(value = "/UploadExercise", method = RequestMethod.POST,
            consumes = {"multipart/form-data"})
    public Result<String> uploadExercise(@RequestParam(value = "lessonId") String lessonId,
                                 @RequestParam(value = "simpleDate") String simpleDate, @RequestParam(value = "description") String description) {
        Exercise ex = new Exercise(UUID.randomUUID().toString(), lessonId, description,
                null, null, simpleDate);
        storageService.sendExerciseToDB(ex);
        return new Result<String>(Result.ResultEnum.Success, "");
    }

    @ExceptionHandler(StorageException.class)
    public Result<String> handleStorageFileNotFound(StorageException e) {
        return new Result<String>(Result.ResultEnum.Error, e.getMessage());
    }
}
