<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/13
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>home page</title>
</head>
<body>
<%
    if (session.getAttribute("email") == null) {
        response.sendRedirect("index.jsp");
    }
%>
<h1>home page</h1>
<%=session.getId()%>
<%=(session.getAttribute("email") != null) ? session.getAttribute("email") : ""%>
<br>
<a href="logout.jsp">LOG OUT</a>
</body>
</html>
