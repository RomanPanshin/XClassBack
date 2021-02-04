package com.Xjournal.Group.Controller;

import com.Xjournal.Group.Entity.Homework;
import com.Xjournal.Group.Entity.Question;
import com.Xjournal.Group.Entity.Result;
import com.Xjournal.Group.Entity.Test;
import com.Xjournal.Group.Repo.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;


@RestController
public class TestController {
    @Autowired
    TestRepository testRepository;

    @RequestMapping(value = "/test/UploadTest", method = RequestMethod.POST)
    public Result<Test> uploadHomework(@RequestParam(value = "description") String description,
                                       @RequestParam(value = "answers") ArrayList<Question> questions,
                                       @RequestParam(value = "lessonId") String lessonId,
                                       @RequestParam(value = "date") String date){
        Test test = new Test(description, questions, lessonId, date);
        testRepository.sendTestToDB(test);
        return new Result<Test>(Result.ResultEnum.Success, test);
    }
    @GetMapping("/test/getExample")
    public Result<Test> getExample() {
        ArrayList<Question> questions = new ArrayList<Question>();
        HashMap v = new HashMap<String, Boolean>();
        v.put("abcd", true);
        v.put("abe", false);
        v.put("efg", true);
        questions.add(0, new Question("Alphabet", v));
        Test test = new Test("Интеграллы", questions, "004f47b3-49a4-4e56-afc6-0f7925230eaf", "23.02.2003");
        testRepository.sendTestToDB(test);
        return new Result<Test>(Result.ResultEnum.Success, test);
    }

    @GetMapping("/test/getForLesson")
    public Result<ArrayList<Test>> getForSolve(@RequestParam(value = "lessonId") String lessonId,
                                               @RequestParam(value = "date") String date){
        ArrayList<Test> tests = null;
        try {
            tests = testRepository.getTests(lessonId, date);
            ArrayList<Test> result = new ArrayList<Test>();
            for(Test t : tests){
                t.setQuestions(null);
                result.add(t);
            }
            return new Result<ArrayList<Test>>(Result.ResultEnum.Success, result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Result<ArrayList<Test>>(Result.ResultEnum.Error, null);
    }

    @GetMapping("/test/getForSolve")
    public Result<Test> getExample(@RequestParam(value = "id") String id){
        Test test = null;
        try {
            test = testRepository.getTestForSolve(id);
            return new Result<Test>(Result.ResultEnum.Success, test);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Result<Test>(Result.ResultEnum.Error, null);
    }
}
