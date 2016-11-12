package util;

import com.mysql.jdbc.Driver;

import java.sql.*;

/**
 * Created by Administrator on 2016/11/12.
 */

// refactor 重构
public class DB {

    private static final String URL = "jdbc:mysql:///db_javaee";
    private static final String USER = "root";
    private static final String PASSWORD = "system";

    public static Connection getConnection() {
        try {
            new Driver();
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
