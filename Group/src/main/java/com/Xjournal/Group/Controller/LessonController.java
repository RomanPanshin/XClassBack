package com.Xjournal.Group.Controller;
/*
 * Wrote by Panshin Roman (roma.super@icloud.com) in 13.01.21
 * LessonController for Xclass
 * Xclass - mobile study application
 * Copyright (c) 2021, Roman Panshin
 * All rights reserved.
 */

import com.Xjournal.Group.Entity.*;
import com.Xjournal.Group.Repo.LessonRepository;
import com.Xjournal.Group.Repo.LessonTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@RestController
public class LessonController{
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private LessonTopicRepository lessonTopicRepository;

        @GetMapping("/lessons/getSchedule")
        public Result<ArrayList<Lesson>> getSchedule(@RequestParam(value = "classId") String classId, @RequestParam(value = "dayOfWeek") GroupDate.DayOfWeek dayOfWeek ) {
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
    public Result<ArrayList<Lesson>> getScheduleForTeacher(@RequestParam(value = "teacherId") String teacherId,
                                                           @RequestParam(value = "dayOfWeek") GroupDate.DayOfWeek dayOfWeek ) {
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
    @PostMapping("/lesson/topic/upload")
    public Result<LessonTopic> uploadTopic(@RequestParam(value = "lessonId") String lessonId,
                                           @RequestParam(value = "simpleDate") String simpleDate,
                                           @RequestParam(value = "topic") String topic) {
            LessonTopic result = new LessonTopic(lessonId, topic, simpleDate);
            lessonTopicRepository.sendTopicToDB(result);
            return new Result<>(Result.ResultEnum.Success, result);
    }

    @GetMapping("/lessons/topic/get")
    public Result<LessonTopic> getScheduleForTeacher(@RequestParam(value = "lessonId") String lessonId,
                                                     @RequestParam(value = "simpleDate") String simpleDate) {
        try {
            // получить пользователя и его класс
            Result<LessonTopic> result = new Result<LessonTopic>(
                    Result.ResultEnum.Success,
                    lessonTopicRepository.getTopicByDateAndLessonId(simpleDate, lessonId)
            );
            return result;
        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return new Result<>(Result.ResultEnum.Error, null);
    }

}
