package com.demo.mywebapp.controller;

import com.demo.mywebapp.user.GetUserData;
import com.demo.mywebapp.user.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/login")
public class Login {
    @GetMapping
    public String login(@RequestBody User user){
        User user2 = GetUserData.getUserData()
                .getUser(user.getUsername(), user.getPassword())
                .orElse(null);
        return user.equals(user2) ? "User found!" : "User does not exists";
    }
}
