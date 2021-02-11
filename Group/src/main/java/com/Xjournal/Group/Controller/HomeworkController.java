package com.Xjournal.Group.Controller;

import com.Xjournal.Group.Entity.Homework;
import com.Xjournal.Group.Entity.Result;
import com.Xjournal.Group.Exception.StorageException;
import com.Xjournal.Group.Repo.HomeworkRepository;
import com.Xjournal.Group.Repo.UserRepository;
import com.Xjournal.Group.Service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ExecutionException;


@RestController
public class HomeworkController {
    @Autowired
    private StorageService storageService;
    @Autowired
    private HomeworkRepository homeworkRepository;


    @RequestMapping(value = "/homework/UploadHomeworkWithFile", method = RequestMethod.POST,
            consumes = {"multipart/form-data"})
    public Result<Homework> uploadHomeworkWithFile(@RequestParam MultipartFile file, @RequestParam(value = "exerciseId") String exerciseId,
                                                   @RequestParam(value = "description") String description,
                                                   @RequestParam(value = "UID") String UID) {
        ArrayList<String> names = storageService.uploadFile(file);
        Homework homework = new Homework(UUID.randomUUID().toString(), exerciseId, description,
                names.get(StorageService.ORIGINAL_FILE_NAME), storageService.staticResURL +
                names.get(StorageService.UNIC_FILE_ID), UID);
        homeworkRepository.sendHomeworkToDB(homework);
        return new Result<Homework>(Result.ResultEnum.Success, homework);
    }

    @RequestMapping(value = "/homework/UploadHomework", method = RequestMethod.POST)
    public Result<Homework> uploadHomework(@RequestParam(value = "exerciseId") String exerciseId,
                                           @RequestParam(value = "description") String description,
                                           @RequestParam(value = "UID") String UID) {
        Homework homework = new Homework(UUID.randomUUID().toString(), exerciseId, description, null ,null, UID);
        homeworkRepository.sendHomeworkToDB(homework);
        return new Result<Homework>(Result.ResultEnum.Success, homework);
    }

    @GetMapping("/homework/getByExerciseId")
    public Result<ArrayList<Homework>> getByExerciseId(@RequestParam(value = "exerciseId") String exerciseId) {
        try {
            // получить все дз
            ArrayList<Homework> homeworks = homeworkRepository.getHomeworksByExerciseId(exerciseId);
            ArrayList<Homework> result = new ArrayList<>();
            for(Homework h : homeworks){
                String name = UserRepository.getNamebyUid(h.getUID());
                h.setName(name);
                result.add(h);
            }
            Result<ArrayList<Homework>> arrayListResult = new Result<ArrayList<Homework>>(
                    Result.ResultEnum.Success,
                    result
            );
            return arrayListResult;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return new Result<ArrayList<Homework>>(Result.ResultEnum.Error, null);
    }

    @ExceptionHandler(StorageException.class)
    public Result<String> handleStorageFileNotFound(StorageException e) {
        return new Result<String>(Result.ResultEnum.Error, e.getMessage());
    }
}
