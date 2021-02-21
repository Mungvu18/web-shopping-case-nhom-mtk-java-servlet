package service;

import jdbc.ConnectionJDBC;
import model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService {
    public Account getAccount(String username, String password){
        Account account = null;
        Connection connection = ConnectionJDBC.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from account where username = ? and password = ?");
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                username = resultSet.getString(2);
                password = resultSet.getString(3);
                int role = resultSet.getInt(4);
                account = new Account(id,username,password,role);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return account;
    }
}
