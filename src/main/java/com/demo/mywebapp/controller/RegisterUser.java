package com.demo.mywebapp.controller;

import com.demo.mywebapp.user.GetUserData;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterUser {
    @PostMapping
    public String addUser(@RequestParam String username, @RequestParam String password){
        boolean result = GetUserData.getUserData().setUser(username,password);
        return result ? "Success!" : "User already exists!";
    }
}
