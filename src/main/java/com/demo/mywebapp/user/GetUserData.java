package com.demo.mywebapp.user;

import com.demo.mywebapp.database.UserData;

public class GetUserData {
    private GetUserData(){}
    public static UserData getUserData(){
        return new UserData();
    }
}
