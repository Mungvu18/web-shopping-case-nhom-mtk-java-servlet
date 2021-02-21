package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC {
    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/QLGIAY",
                    "root",
                    "123456"
            );
            System.out.println("kết nối ok");
        } catch (ClassNotFoundException e) {
            System.out.println("Lỗi tải Driver");
        } catch (SQLException throwables) {
            System.out.println("Kết nối thất bại");;
        }
        return connection;
    }
}
