package com.Xjournal.Group.Controller;

import com.Xjournal.Group.Entity.*;
import com.Xjournal.Group.Repo.TestRepository;
import com.Xjournal.Group.Repo.TestResultRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;


@RestController
public class TestController {
    @Autowired
    TestRepository testRepository;
    @Autowired
    TestResultRepository testResultRepository;

    @RequestMapping(value = "/test/UploadTest", method = RequestMethod.POST)
    @ResponseBody
    public Result<Test> uploadHomework(@RequestParam(value = "description") String description,
                                       @RequestParam(value = "answers") String questions,
                                       @RequestParam(value = "lessonId") String lessonId,
                                       @RequestParam(value = "date") String date){
        ArrayList<Question> questionArrayList = new Gson().fromJson(questions, new TypeToken<List<Question>>(){}.getType());
        Test test = new Test(description, questionArrayList, lessonId, date);

        testRepository.sendTestToDB(test);
        return new Result<Test>(Result.ResultEnum.Success, test);
    }

    @RequestMapping(value = "/test/result/Upload", method = RequestMethod.POST)
    public  Result<Double> uploadTestResult(@RequestParam(value = "decision") Test test,
                                            @RequestParam(value = "uId") String uId) {
        TestResult result = new TestResult(test, uId);
        try {
            result.checkBy(testRepository.getTest(result.getTestId()));
        } catch (InterruptedException e) {
            e.printStackTrace();
            return new Result<Double>(Result.ResultEnum.Error, null);
        } catch (ExecutionException e) {
            e.printStackTrace();
            return new Result<Double>(Result.ResultEnum.Error, null);
        }
        testResultRepository.sendTestResToDB(result);
        return new Result<Double>(Result.ResultEnum.Success, result.getPercentage());
    }

    @GetMapping("/test/result/byId")
    public Result<ArrayList<TestResult>> getById(@RequestParam(value = "testId") String testId) {
        try {
            ArrayList<TestResult> result = testResultRepository.getResTests(testId);
            return new Result<ArrayList<TestResult>>(Result.ResultEnum.Success, result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Result<ArrayList<TestResult>>(Result.ResultEnum.Error, null);
    }

    @GetMapping("/test/getResultExample")
    public Result<TestResult> getExample() {
        Test tnew = null;
        try {
            tnew = testRepository.getTest("b507f743-f297-4703-aa62-d55b38fb672e");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ArrayList<Question> questions = tnew.getQuestions();
        HashMap v1 = new HashMap<String, Boolean>();
        v1.put("верно", false);
        v1.put("неверно", true);
        questions.get(0).setAnswers(v1);

        HashMap v2  = new HashMap<String, Boolean>();
        v2.put("неверно", false);
        v2.put("верно", true);
        questions.get(1).setAnswers(v2);

        HashMap v3 = new HashMap<String, Boolean>();
        v3.put("неверно", false);
        v3.put("верно", true);
        questions.get(2).setAnswers(v3);

        System.out.println(questions.toString());

        TestResult testResult = new TestResult(tnew, "4Trmhj1eBsNyjTd2gVITIicjiku1");
        System.out.println(testResult.getQuestions().toString());
        testResult.setQuestions(questions);
        System.out.println(testResult.getQuestions().toString());
        try {
            testResult.checkBy(testRepository.getTest("b507f743-f297-4703-aa62-d55b38fb672e"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        testResultRepository.sendTestResToDB(testResult);

        return new Result<TestResult>(Result.ResultEnum.Success, testResult);
    }


    @GetMapping("/test/getExample")
    public Result<Test> getExample2() {
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

    @ResponseBody
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public String handleHttpMediaTypeNotAcceptableException() {
        return "acceptable MIME type:" + MediaType.APPLICATION_JSON_VALUE;
    }
}
