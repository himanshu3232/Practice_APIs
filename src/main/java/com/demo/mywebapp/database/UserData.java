package com.demo.mywebapp.database;

import com.demo.mywebapp.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserData implements DataManipulation{
    private final Connection con;
    public UserData() {
        ConnectDB db = new ConnectDB();
        this.con = db.getConnection();
    }
    @Override
    public Optional<User> getUser(String username, String password){
        User user = null;
        String sql = SQL.GET_USER;
        try{
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1,username);
            statement.setString(2,password);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                user = new User(resultSet.getString(1),resultSet.getString(2));
            }
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(user);
    }
    @Override
    public boolean setUser(String username,String password){
        boolean result;
        String sql = SQL.SET_USER;
        try{
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1,username);
            statement.setString(2, password);
            result = statement.executeUpdate()>0;
            con.close();
        }catch(SQLException e){
            return false;
        }
        return result;
    }
    @Override
    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        String sql = SQL.GET_ALL_USERS;
        try{
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                User user = new User(resultSet.getString(1),resultSet.getString(2));
                users.add(user);
            }
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return users;
    }
}
