package com.Xjournal.Group.Controller;

import com.Xjournal.Group.Entity.MyUser;
import com.Xjournal.Group.Entity.Result;
import com.Xjournal.Group.Exception.StorageException;
import com.Xjournal.Group.Service.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ExersicseController {
    @Autowired
    private Storage storageService;

    @RequestMapping(value = "/doUpload", method = RequestMethod.POST,
            consumes = {"multipart/form-data"})
    public Result<String> upload(@RequestParam MultipartFile file, @RequestParam(value = "description") String description) {
        storageService.uploadFile(file);
        return new Result<String>(Result.ResultEnum.Success, "");
    }

    @ExceptionHandler(StorageException.class)
    public Result<String> handleStorageFileNotFound(StorageException e) {
        return new Result<String>(Result.ResultEnum.Error, "");
    }
}
