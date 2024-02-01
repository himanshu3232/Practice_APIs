package com.demo.mywebapp.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Data
public class User {
    private static int userCount;
    private String username;
    private String password;
    public User(){
        userCount++;
    }
}
