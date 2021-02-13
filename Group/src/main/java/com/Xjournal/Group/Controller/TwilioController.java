package com.Xjournal.Group.Controller;

import com.Xjournal.Group.Entity.Result;
import com.Xjournal.Group.Repo.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@RestController
public class TwilioController {
    @GetMapping("/twilio/getAccessToken")
    public Result<String> getAccessToken(@RequestParam(value = "UID") String uId,
                                         @RequestParam(value = "lessonId") String lessonId){
        String name = UserRepository.getNamebyUid(uId);
        Process p = null;
        String command = ("python3 /home/XClassBack/twilio-twilio-python-2fb5d37/token.py " + name);
        try {
            p = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result<>(Result.ResultEnum.Error, null);
        }

        InputStream stdout = p.getInputStream();
        InputStreamReader isr = new InputStreamReader(stdout);
        BufferedReader br = new BufferedReader(isr);
        try {
            String result = br.readLine();
            return new Result<>(Result.ResultEnum.Success, result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result<>(Result.ResultEnum.Error, null);
    }

    @RequestMapping(value = "/twilio/lesson/start", method = RequestMethod.POST)
        public Result<String> getAccessToken(@RequestParam(value = "lessonId") String lessonId){
        Process p = null;
        String command = ("python3 /home/XClassBack/twilio-twilio-python-2fb5d37/room.py " + lessonId);
        try {
            p = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result<>(Result.ResultEnum.Error, null);
        }
        InputStream stdout = p.getInputStream();
        InputStreamReader isr = new InputStreamReader(stdout);
        BufferedReader br = new BufferedReader(isr);
        try {
            String result = br.readLine();
            return new Result<>(Result.ResultEnum.Success, result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result<>(Result.ResultEnum.Error, null);
    }
}
