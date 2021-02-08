package com.Xjournal.Group.Controller;


import com.Xjournal.Group.Entity.*;
import com.Xjournal.Group.Exception.StorageException;
import com.Xjournal.Group.Repo.AdditionFileRepository;
import com.Xjournal.Group.Repo.AdditionalLessonRepository;
import com.Xjournal.Group.Service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@RestController
public class AdditionalInfoController {
    @Autowired
    AdditionFileRepository additionFileRepository;
    @Autowired
    AdditionalLessonRepository additionalLessonRepository;
    @Autowired
    private StorageService storageService;

    @RequestMapping(value = "/additional/upload", method = RequestMethod.POST,
            consumes = {"multipart/form-data"})
    public Result<AdditionalFile> uploadExerciseWithFile(@RequestParam MultipartFile file,
                                                         @RequestParam(value = "ALessonId") String ALessonId,
                                                         @RequestParam(value = "description") String description) {
        ArrayList<String> names = storageService.uploadFile(file);
        AdditionalFile res = new AdditionalFile(ALessonId, description,
                                names.get(StorageService.ORIGINAL_FILE_NAME), names.get(StorageService.UNIC_FILE_ID));
        additionFileRepository.sendFileToDB(res);
        return new Result<AdditionalFile>(Result.ResultEnum.Success, res);
    }

    @GetMapping("/additional/lessonsByClass")
    public Result<ArrayList<AdditionalLesson>> getLessonByClassId(@RequestParam(value = "classId") String classId) {
        try {
            Result<ArrayList<AdditionalLesson>> arrayListResult = new Result<>(
                    Result.ResultEnum.Success,
                    additionalLessonRepository.getByClassId(classId)
            );
            return arrayListResult;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return new Result<ArrayList<AdditionalLesson>>(Result.ResultEnum.Error, null);
    }

    @GetMapping("/additional/Files")
    public Result<ArrayList<AdditionalFile>> getByALessonId(@RequestParam(value = "ALessonId") String ALessonId){
        try {
            Result<ArrayList<AdditionalFile>> arrayListResult = new Result<>(
                    Result.ResultEnum.Success,
                    additionFileRepository.getByALessonId(ALessonId)
            );
            return arrayListResult;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return new Result<ArrayList<AdditionalFile>>(Result.ResultEnum.Error, null);
    }

    @ExceptionHandler(StorageException.class)
    public Result<String> handleStorageFileNotFound(StorageException e) {
        return new Result<String>(Result.ResultEnum.Error, e.getMessage());
    }
}
