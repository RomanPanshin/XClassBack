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
import com.Xjournal.Group.Repo.ExerciseRepository;
import com.Xjournal.Group.Service.StorageService;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ExecutionException;


@RestController
public class ExerciseController {
    @Autowired
    private StorageService storageService;
    @Autowired
    private ExerciseRepository exerciseRepository;

    @RequestMapping(value = "/UploadExerciseWithFile", method = RequestMethod.POST,
            consumes = {"multipart/form-data"})
    public Result<Exercise> uploadExerciseWithFile(@RequestParam MultipartFile file,
                                                   @RequestParam(value = "lessonId") String lessonId,
                                                   @RequestParam(value = "simpleDate") String simpleDate,
                                                   @RequestParam(value = "description") String description) {
        ArrayList<String> names = storageService.uploadFile(file);
        Exercise ex = new Exercise(UUID.randomUUID().toString(), lessonId, description,
                names.get(StorageService.ORIGINAL_FILE_NAME), storageService.staticResURL + names.get(StorageService.UNIC_FILE_ID),
                simpleDate);
        exerciseRepository.sendExerciseToDB(ex);
        return new Result<Exercise>(Result.ResultEnum.Success, ex);
    }

    @RequestMapping(value = "/UploadExercise", method = RequestMethod.POST,
            consumes = {"multipart/form-data"})
    public Result<Exercise> uploadExercise(@RequestParam(value = "lessonId") String lessonId,
                                           @RequestParam(value = "simpleDate") String simpleDate,
                                           @RequestParam(value = "description") String description) {
        Exercise ex = new Exercise(UUID.randomUUID().toString(), lessonId, description,
                null, null, simpleDate);
        exerciseRepository.sendExerciseToDB(ex);
        return new Result<Exercise>(Result.ResultEnum.Success, ex);
    }

    @GetMapping(value = "/GetExerciseBySimpleDateAndLessonId")
    public Result<Exercise> getExercise(@RequestParam(value = "simpleDate") String simpleDate,
                                        @RequestParam(value = "lessonId") String lessonId){
        Exercise ex = null;
        try {
            ex = exerciseRepository.getExerciseByDateAndLessonId(simpleDate, lessonId);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ex == null ?
                new Result<Exercise>(Result.ResultEnum.Error, null) :
                new Result<Exercise>(Result.ResultEnum.Success, ex);
    }

    @ExceptionHandler(StorageException.class)
    public Result<String> handleStorageFileNotFound(StorageException e) {
        return new Result<String>(Result.ResultEnum.Error, e.getMessage());
    }
}
