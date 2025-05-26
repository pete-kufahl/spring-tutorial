package com.prk.spring_mvc.controller;

import com.prk.spring_mvc.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// annotation makes every endpoint return a JSON representation of an object
@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @GetMapping("hello")
    public User getUser() {
        User user = new User();
        user.setId(1234);
        user.setName("Petey");
        return user;
    }
}
