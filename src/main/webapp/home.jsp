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
    <title>home page</title>
</head>
<body>
<c:if test="${sessionScope.email eq null}">
    <c:redirect url="index.jsp"/>
</c:if>
<h1>home page</h1>
${sessionScope.email}
<a href="/user?action=logout">LOG OUT</a>
<hr>
<form action="/book" method="post">
    <input type="hidden" name="action" value="add">
    <input type="text" name="title" placeholder="TITLE" value="title..."><br>
    <input type="text" name="author" placeholder="AUTHOR" value="author..."><br>
    <input type="text" name="publish" placeholder="PUBLISH" value="publish..."><br>
    <input type="text" name="date" placeholder="DATE" value="2016-11-1"><br>
    <input type="text" name="price" placeholder="PRICE" value="50.00"><br>
    <input type="text" name="amount" placeholder="AMOUNT" value="10"><br>
    <input type="submit" value="ADD">
</form>
<hr>
<h2>book list</h2>
<table border="1">
    <c:choose>
        <c:when test="${sessionScope.books[0] eq null}">
            NO RECORD.
        </c:when>
        <c:otherwise>
            <tr>
                <th>ID</th>
                <th>TITLE</th>
                <th>AUTHOR</th>
                <th>PUBLISH</th>
                <th>DATE</th>
                <th>PRICE</th>
                <th>AMOUNT</th>
                <th colspan="2">OPERATION</th>
            </tr>
        </c:otherwise>
    </c:choose>
    <c:forEach var="book" items="${sessionScope.books}" varStatus="vs">
        <tr>
            <td>${vs.count}</td>
            <td>${book.title}</td>
            <td>${book.author}</td>
            <td>${book.publish}</td>
            <td>${book.date}</td>
            <td>${book.price}</td>
            <td>${book.amount}</td>
            <td><a href="">MODIFY</a></td>
            <td><a href="">REMOVE</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
