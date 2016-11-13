package servlet;

import model.Book;
import util.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/12.
 */
@WebServlet(urlPatterns = "/book")
public class BookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("add")) {
            add(request, response);
        }
        if (action.equals("query")) {
            query(request, response);
        }
        if (action.equals("search")) {
            search(request, response);
        }
        if (action.equals("update")) {
            update(request, response);
        }
        if (action.equals("remove")) {
            remove(request, response);
        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title").trim();
        String author = request.getParameter("author").trim();
        String publish = request.getParameter("publish").trim();
        String date = request.getParameter("date").trim();
        double price = Double.valueOf(request.getParameter("price").trim());
        int amount = Integer.valueOf(request.getParameter("amount").trim());

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO db_javaee.book VALUES(NULL, ?,?,?,?,?,?)";

        try {
            connection = DB.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            preparedStatement.setString(3, publish);
            preparedStatement.setString(4, date);
            preparedStatement.setDouble(5, price);
            preparedStatement.setInt(6, amount);

            preparedStatement.executeUpdate();

            response.sendRedirect("/book?action=query");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(null, preparedStatement, connection);
        }
    }

    private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DB.getConnection();
            String sql = "SELECT * FROM db_javaee.book ORDER BY id";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                Book book = new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("publish"),
                        resultSet.getString("date"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("amount")
                );
                books.add(book);
            }
            request.getSession().setAttribute("books", books);
            response.sendRedirect("home.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(resultSet, preparedStatement, connection);
        }
    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter("id"));

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DB.getConnection();
            String sql = "SELECT * FROM db_javaee.book WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next(); // *****
            Book book = new Book(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("author"),
                    resultSet.getString("publish"),
                    resultSet.getString("date"),
                    resultSet.getDouble("price"),
                    resultSet.getInt("amount")
            );
            request.getSession().setAttribute("book", book);
            response.sendRedirect("edit.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(resultSet, preparedStatement, connection);
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.valueOf(request.getParameter("id"));
        String title = request.getParameter("title").trim();
        String author = request.getParameter("author").trim();
        String publish = request.getParameter("publish").trim();
        String date = request.getParameter("date").trim();
        double price = Double.valueOf(request.getParameter("price").trim());
        int amount = Integer.valueOf(request.getParameter("amount").trim());

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DB.getConnection();
            String sql = "UPDATE db_javaee.book SET title = ?, author = ?, publish = ?, date=?, price = ?, amount = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            preparedStatement.setString(3, publish);
            preparedStatement.setString(4, date);
            preparedStatement.setDouble(5, price);
            preparedStatement.setInt(6, amount);
            preparedStatement.setInt(7, id);
            preparedStatement.executeUpdate();
            response.sendRedirect("/book?action=query");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(null, preparedStatement, connection);
        }
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter("id"));

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DB.getConnection();
            String sql = "DELETE FROM db_javaee.book WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            response.sendRedirect("/book?action=query");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(null, preparedStatement, connection);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
