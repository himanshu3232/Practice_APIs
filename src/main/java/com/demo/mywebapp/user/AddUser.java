package com.demo.mywebapp.user;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class AddUser {
    @PostMapping
    public String addUser(@RequestParam String username, @RequestParam String password){
        User user = new User(username,password);
        var result =
    }
}
