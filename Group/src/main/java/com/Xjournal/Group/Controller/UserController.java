package com.Xjournal.Group.Controller;
import com.Xjournal.Group.DBGenerator;
import com.Xjournal.Group.Entity.MyUser;
import com.Xjournal.Group.Entity.Result;
import com.Xjournal.Group.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.concurrent.ExecutionException;


@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DBGenerator dbGenerator;


    @GetMapping("/")
    public Result<String> sayHello() throws ExecutionException, InterruptedException {
        return new Result<String>(Result.ResultEnum.Success, String.format("Hello %s!", "Roma"));
    }

    @PostMapping("/auth")
    public Result<MyUser> auth(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {

        MyUser myUser = userRepository.postReqest(email, password);
        return myUser == null?
                new Result<MyUser>(Result.ResultEnum.Error, null) :
                new Result<MyUser>(Result.ResultEnum.Success,myUser);
    }

    @GetMapping("/userList")
    public String userList(@RequestHeader(value = "key") String key) {
        return String.format("Hello %s!", key);
    }

//    @GetMapping("/generateDB")
//    public String generateDB() {
////            fileRepository.createBucket();
////            return "aa";
//
//    }
}
