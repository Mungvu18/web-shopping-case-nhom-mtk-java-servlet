package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlgiay","root","1010");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("khong co ket noi");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("that bai");
        }
        System.out.println("ket noi thanh cong");
        return  connection;
    }
}
