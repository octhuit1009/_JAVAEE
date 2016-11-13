<%@ page import="java.util.List" %>
<%@ page import="model.Book" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/13
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>edit page</title>
</head>
<body>
<c:if test="${sessionScope.email eq null}">
    <c:redirect url="index.jsp"/>
</c:if>
<h1>edit page</h1>
${sessionScope.email}
<a href="/user?action=logout">LOG OUT</a>
<hr>
<form action="/book" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="${sessionScope.book.id}">
    <input type="text" name="title" placeholder="TITLE" value="${sessionScope.book.title}"><br>
    <input type="text" name="author" placeholder="AUTHOR" value="${sessionScope.book.author}"><br>
    <input type="text" name="publish" placeholder="PUBLISH" value="${sessionScope.book.publish}"><br>
    <input type="text" name="date" placeholder="DATE" value="${sessionScope.book.date}"><br>
    <input type="text" name="price" placeholder="PRICE" value="${sessionScope.book.price}"><br>
    <input type="text" name="amount" placeholder="AMOUNT" value="${sessionScope.book.amount}"><br>
    <input type="submit" value="SAVE">
</form>
</body>
</html>
