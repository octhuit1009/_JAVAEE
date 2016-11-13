<%@ page import="com.mysql.jdbc.Driver" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/13
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String email = request.getParameter("email").trim().toLowerCase();
    String password = request.getParameter("password");

    new Driver();
    Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/db_javaee?user=root&password=system");
    String sql = "SELECT * FROM db_javaee.user WHERE email=? AND password=?";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setString(1, email);
    preparedStatement.setString(2, password);

    ResultSet resultSet = preparedStatement.executeQuery();

    if (resultSet.next()) {
        session.setAttribute("email", email);
        response.sendRedirect("home.jsp");
    } else {
        request.setAttribute("message", "invalid email or password!");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    resultSet.close();
    preparedStatement.close();
    connection.close();
%>
</body>
</html>
