package com.demo.mywebapp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public class ConnectDb {
    public ConnectDb(){
        try{
            Class.forName("org.postgresql.Driver");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    public Connection getConnection(){
        Connection con = null;
        try{
            con = DriverManager.getConnection(Keys.URL,Keys.USER,Keys.PASSWORD);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return con;
    }
}
