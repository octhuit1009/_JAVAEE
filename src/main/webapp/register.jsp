<%@ page import="java.util.Arrays" %><%--
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
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String[] cities = request.getParameterValues("cities");
    String[] hobbies = request.getParameterValues("hobbies");
    out.print(email + ", " + password + "<br>");
    out.print(Arrays.asList(cities) + "<br>");
    out.print(Arrays.asList(hobbies) + "<br>");
%>
</body>
</html>
