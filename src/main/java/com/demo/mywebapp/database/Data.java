package com.demo.mywebapp.database;

import com.demo.mywebapp.user.User;

import java.sql.*;
import java.util.Optional;

public class Data {
    private final Connection con;
    public Data() {
        ConnectDb db = new ConnectDb();
        this.con = db.getConnection();
    }
    public Optional<User> getUser(String username, String password){
        String sql = SQL.GET_USER;
        try{
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1,username);
            statement.setString(2,password);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                System.out.println(resultSet.getString(1));
                System.out.println(resultSet.getString(2));
            }
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public boolean setUser(User user){
        boolean result = false;
        String sql = SQL.SET_USER;
        try{
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1,user.getUsername());
            statement.setString(2, user.getPassword());
            result = statement.execute();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }
}
