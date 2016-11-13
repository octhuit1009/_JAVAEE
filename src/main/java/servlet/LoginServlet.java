package servlet;

import com.mysql.jdbc.Driver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * Created by Administrator on 2016/11/13.
 */
@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email").trim().toLowerCase();
        String password = request.getParameter("password");

        try {
            new Driver();
            Connection connection = DriverManager.getConnection("jdbc:mysql://166.111.201.133:3306/db_javaee?user=root&password=system");
            String sql = "SELECT * FROM db_javaee.user WHERE email=? AND password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                request.getSession().setAttribute("email", email);
                response.sendRedirect("home.jsp");
            } else {
                request.setAttribute("message", "invalid email or password!");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
