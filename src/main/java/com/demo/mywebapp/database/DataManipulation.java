package com.demo.mywebapp.database;

import java.util.List;
import java.util.Optional;

public interface DataManipulation {
    Optional<?> getUser(String para1, String para2);
    boolean setUser(String para1, String para2);
    List<?> getAllUsers();
}
