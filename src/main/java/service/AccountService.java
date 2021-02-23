package service;

import model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountService implements IAccountService {
    @Override
    public void Create(Account account) {
        Connection connection = MyConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into account (username, password, role) VALUE (?,?,?);");
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setInt(3, account.getRole());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Account findById(int id) {
        Connection connection = MyConnection.getConnection();
        Account account = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from account where id = ?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                int role = resultSet.getInt("role");
                account = new Account(id, username, password, role);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return account;

    }


    @Override
    public List findAll() {
        List<Account> accountList = new ArrayList<>();
        Connection connection = MyConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from account");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                int role = resultSet.getInt("role");
                Account account = new Account(id, username, password, role);

                accountList.add(account);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return accountList;
    }

    @Override
    public void delete(int id) {
        Connection connection = MyConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from account where id = ?;");
            preparedStatement.setInt(1,id);
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(Account account) {
        Connection connection = MyConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update account set username = ?,password = ?,role = ? where id = ?; ");
            preparedStatement.setString(1,account.getUsername());
            preparedStatement.setString(2,account.getPassword());
            preparedStatement.setInt(3,account.getRole());
            preparedStatement.setInt(4,account.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
