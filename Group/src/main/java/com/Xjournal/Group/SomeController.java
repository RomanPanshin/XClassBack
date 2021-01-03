package com.Xjournal.Group;
import org.springframework.web.bind.annotation.*;

import static com.Xjournal.Group.Repo.postReqest;


@RestController
public class SomeController {
    @GetMapping("/")
    public String sayHello() {
        return String.format("Hello %s!", "Roma");
    }

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @PostMapping("/auth")
    public MyUser auth(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {
        return  postReqest(email, password);
    }

    @GetMapping("/UserList")
    public String userList(@RequestHeader(value = "key") String key) {
        return String.format("Hello %s!", key);
    }

}
