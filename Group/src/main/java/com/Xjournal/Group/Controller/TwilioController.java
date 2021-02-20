package com.Xjournal.Group.Controller;

import com.Xjournal.Group.Entity.MyUser;
import com.Xjournal.Group.Entity.Result;
import com.Xjournal.Group.Entity.VideoLesson;
import com.Xjournal.Group.Repo.UserRepository;
import com.Xjournal.Group.Repo.VideoLessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

@RestController
public class TwilioController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VideoLessonRepository videoLessonRepository;

    private static HashMap<String, VideoLesson> presentLessons = new HashMap<>();

    @GetMapping("/twilio/getAccessToken")
    public Result<String> getAccessToken(@RequestParam(value = "UID") String uId,
                                         @RequestParam(value = "lessonId") String lessonId){
        String name = UserRepository.getNamebyUid(uId);
        Process p = null;
        String command = ("python3 /home/XClassBack/twilio-twilio-python-2fb5d37/token_2.py " + name);
        try {
            p = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result<String>(Result.ResultEnum.Error, null);
        }

        InputStream stdout = p.getInputStream();
        InputStreamReader isr = new InputStreamReader(stdout);
        BufferedReader br = new BufferedReader(isr);
        String result;
        try {
            result = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return new Result<String>(Result.ResultEnum.Error, null);
        }
        if(userRepository.getClaimsByUid(uId) == UserRepository.STUDENT) {
            VideoLesson videoLesson = presentLessons.get(lessonId);
            videoLesson.getPresentStudents().put(uId, true);
        }
        return new Result<String>(Result.ResultEnum.Success, result);
    }

    @RequestMapping(value = "/twilio/lesson/start", method = RequestMethod.POST)
        public Result<String> StartVideoLesson(@RequestParam(value = "lessonId") String lessonId,
                                               @RequestParam(value = "UID") String uId,
                                               @RequestParam(value = "simpleDate") String simpleDate,
                                               @RequestParam(value = "classId") String classId){
        Process p = null;
        String command = ("python3 /home/XClassBack/twilio-twilio-python-2fb5d37/room.py " + lessonId);
        String result;
        try {
            p = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result<String>(Result.ResultEnum.Error, null);
        }
        InputStream stdout = p.getInputStream();
        InputStreamReader isr = new InputStreamReader(stdout);
        BufferedReader br = new BufferedReader(isr);
        try {
            result = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return new Result<String>(Result.ResultEnum.Error, null);
        }

        ArrayList<MyUser> studentsByClass = null;
        try {
            studentsByClass = userRepository.usersByClassId(classId);
            System.out.println(studentsByClass.toString());
            HashMap<String, Boolean> presentStudents = new HashMap<>();
            for(MyUser student : studentsByClass){
                presentStudents.put(student.getuId(), false);
                System.out.println("add student "+student.getuId());
            }
            VideoLesson videoLesson = new VideoLesson(result, lessonId, presentStudents, simpleDate, uId);
            videoLesson.toString();
            presentLessons.put(lessonId, videoLesson);
            return new Result<String>(Result.ResultEnum.Success, result);
        } catch (ExecutionException e) {
            e.printStackTrace();
            return new Result<String>(Result.ResultEnum.Error, null);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return new Result<String>(Result.ResultEnum.Error, null);
        }

    }

    @RequestMapping(value = "/twilio/lesson/stop", method = RequestMethod.POST)
    public Result<VideoLesson> stopLesson(@RequestParam(value = "lessonId") String lessonId){
        VideoLesson result = presentLessons.get(lessonId);
        presentLessons.remove(lessonId);
        return new Result<VideoLesson>(Result.ResultEnum.Success, result);
    }

    @RequestMapping(value = "/twilio/lesson/results/upload", method = RequestMethod.POST)
    public Result<String> uploadVLessonResults(@RequestParam(value = "videoLesson")  VideoLesson videoLesson) {
        videoLessonRepository.sendFileToDB(videoLesson);
        return new Result<String>(Result.ResultEnum.Success, "");
    }
    @GetMapping("/twilio/lesson/results/get")
    public Result<VideoLesson> getVLessonResults(@RequestParam(value = "lessonId")  String lessonId){
        try {
            VideoLesson videoLesson = videoLessonRepository.getByLessonId(lessonId);
            return new Result<VideoLesson>(Result.ResultEnum.Success, videoLesson);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return new Result<VideoLesson>(Result.ResultEnum.Error, null);
    }

    @GetMapping("/twilio/lesson/present/get")
    public Result<HashMap<String, VideoLesson>> getCurrentVideoLessons(){
        return new Result<HashMap<String, VideoLesson>>(Result.ResultEnum.Success, presentLessons);
    }
}
