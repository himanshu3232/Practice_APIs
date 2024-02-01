package com.demo.mywebapp.database;

public class SQL {
    static final String GET_USER = "SELECT * FROM users WHERE username = ? AND password = ?";
    static final String SET_USER = "INSERT INTO users(username,password) VALUES (?,?)";
    static final String GET_ALL_USERS = "SELECT * FROM users";
}
