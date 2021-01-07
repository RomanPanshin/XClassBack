package com.Xjournal.Group.Controller;

import com.Xjournal.Group.Entity.Date;
import com.Xjournal.Group.Entity.Lesson;
import com.Xjournal.Group.Entity.Result;
import com.Xjournal.Group.Repo.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@RestController

public class LessonController{
    @Autowired
    private LessonRepository lessonRepository;

        @GetMapping("/lessons/getSchedule")
        public Result<ArrayList<Lesson>> getSchedule(@RequestParam(value = "classId") String classId, @RequestParam(value = "dayOfWeek") Date.DayOfWeek dayOfWeek ) {
            try {
                // получить пользователя и его класс
                Result<ArrayList<Lesson>> arrayListResult = new Result<ArrayList<Lesson>>(
                        Result.ResultEnum.Success,
                        lessonRepository.getForClassIdAndDay(classId, dayOfWeek)
                );
                return arrayListResult;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return new Result<ArrayList<Lesson>>(Result.ResultEnum.Error, null);
        }
    @GetMapping("/lessons/getSchedule/teacher")
    public Result<ArrayList<Lesson>> getScheduleForTeacher(@RequestParam(value = "teacherId") String teacherId, @RequestParam(value = "dayOfWeek") Date.DayOfWeek dayOfWeek ) {
        try {
            // получить пользователя и его класс
            Result<ArrayList<Lesson>> arrayListResult = new Result<ArrayList<Lesson>>(
                    Result.ResultEnum.Success,
                    lessonRepository.getForTeacherIdAndDay(teacherId, dayOfWeek)
            );
            return arrayListResult;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return new Result<ArrayList<Lesson>>(Result.ResultEnum.Error, null);
    }
}
