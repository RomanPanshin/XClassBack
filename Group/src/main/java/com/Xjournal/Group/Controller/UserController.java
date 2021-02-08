package com.Xjournal.Group.Controller;
import com.Xjournal.Group.DBGenerator;
import com.Xjournal.Group.Entity.MyUser;
import com.Xjournal.Group.Entity.Result;
import com.Xjournal.Group.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DBGenerator dbGenerator;

    @GetMapping("/getUserByClassId")
    public Result<ArrayList<MyUser>> getUserById(@RequestParam(value = "idclass") String idclass){
        try {
            ArrayList<MyUser> res = userRepository.usersByClassId(idclass);
            return new Result<>(Result.ResultEnum.Success, res);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Result<>(Result.ResultEnum.Error, null);
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



}
