package servlet;

import com.mysql.jdbc.Driver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * Created by Administrator on 2016/11/13.
 */
@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password");
        String[] cities = request.getParameterValues("cities");
        String[] hobbies = request.getParameterValues("hobbies");

        try {
            new Driver();
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/db_javaee", "root", "system");
            String sql = "INSERT INTO db_javaee.user VALUE (NULL ,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, Arrays.asList(cities).toString());
            preparedStatement.setString(4, Arrays.asList(hobbies).toString());

            preparedStatement.executeUpdate(); // DML insert update delete

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("index.jsp"); // 重定向 redirect
    }
}
