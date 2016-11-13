<%@ page import="java.util.Arrays" %>
<%@ page import="com.mysql.jdbc.Driver" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/13
  Time: 11:05
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
    String[] cities = request.getParameterValues("cities");
    String[] hobbies = request.getParameterValues("hobbies");

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

//    request.setAttribute("message", "注册成功！");

//    request.getRequestDispatcher("index.jsp").forward(request, response); // 转发 forward
    response.sendRedirect("index.jsp"); // 重定向 redirect
%>
</body>
</html>
