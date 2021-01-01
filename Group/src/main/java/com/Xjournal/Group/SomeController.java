package com.Xjournal.Group;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/auth")
    public MyUser auth(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return new MyUser("ROMA", "secret657");
    }
}
