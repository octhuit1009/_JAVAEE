<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/13
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index page</title>
</head>
<body>
<h1>index page</h1>
<%=session.getId()%>
<form action="/login" method="post">
    <input type="text" name="email" placeholder="EMAIL" value="tester@test.com"><br>
    <input type="password" name="password" placeholder="PASSWORD" value="123"><br>
    <input type="submit" value="LOG IN">
</form>
<hr>
<a href="signup.jsp">SIGN UP</a>
<br>
<%=(request.getAttribute("message") != null) ? request.getAttribute("message") : ""%>
</body>
</html>
