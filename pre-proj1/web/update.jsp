<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 01.04.2020
  Time: 19:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>update</title>
</head>
<body>
<h2>${bdMethod}</h2>
    <form action="/update" method="post">
        <input type="text" name="name"  value=${userName}>
        <input type="hidden" title="name" name="userID" value=${userID}>
        <input type="text" title="password" name="password" value=${userPassword}>
        <input type="submit" value="submit">
    </form>
</body>
</html>
