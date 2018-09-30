<%--
  Created by IntelliJ IDEA.
  User: Timey
  Date: 14.09.2018
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ADMIN</title>
</head>
<body>
<h1>Hello, admin!</h1>
<form method="post" action="controller">
    <input type="hidden" name="command" value="logout">
    <input class="button" type="submit" value="LOGOUT">
</form>
<%--<a href="logout">Logout</a>--%>
</body>
</html>
