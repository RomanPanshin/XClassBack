package com.Xjournal.Group.Controller;

import com.Xjournal.Group.Entity.ClassInfo;
import com.Xjournal.Group.Entity.Result;
import com.Xjournal.Group.Repo.ClassInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@RestController
public class ClassController {
    @Autowired
    ClassInfoRepository classInfoRepository;
    @GetMapping(value = "/classes/getAll")
    public Result<ArrayList<ClassInfo>> getClasses(){
        try {
            ArrayList<ClassInfo> res = classInfoRepository.getClasses();
            return new Result<ArrayList<ClassInfo>>(Result.ResultEnum.Success, res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return new Result<ArrayList<ClassInfo>>(Result.ResultEnum.Error, null);
    }
}
