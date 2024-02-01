package com.demo.mywebapp.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/login")
public class Login {
    @GetMapping
    public String authenticateUser(@RequestBody User user){
        return user.toString();
    }
}
