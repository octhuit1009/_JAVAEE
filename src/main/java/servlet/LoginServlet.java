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
 * Created by Administrator on 2016/11/12.
 */
@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);

        String email = req.getParameter("email").trim().toLowerCase();
        String password = req.getParameter("password");

        try {
            new Driver();
            Connection connection = DriverManager.getConnection("jdbc:mysql:///db_javaee", "root", "system");
            String sql = "SELECT * FROM db_javaee.user WHERE email = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                req.getSession().setAttribute("email", email);
                resp.sendRedirect("home.jsp");
            } else {
                req.setAttribute("message", "invalid email or password.");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
