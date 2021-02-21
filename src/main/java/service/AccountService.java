package service;

import model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AccountService implements IAccountService{
    @Override
    public void Create(Account account) {
        Connection connection = MyConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into account (id, username, password, role) VALUE (?,?,?,?);");
            preparedStatement.setInt(1,account.getId());
            preparedStatement.setString(2,account.getUsername());
            preparedStatement.setString(3,account.getPassword());
            preparedStatement.setInt(4,account.getRole());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Account findById(int id) {
        return null;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Account account) {

    }
}
