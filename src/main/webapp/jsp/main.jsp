<%--
  Created by IntelliJ IDEA.
  User: Timey
  Date: 28.09.2018
  Time: 1:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu</title>
</head>
<body>
<h1>Main menu</h1>
<form method="post" action="/controller">
    <input type="hidden" name="command" value="sign in">
    <input class="button" type="submit" value="SIGN IN">
</form>
<form method="post" action="/controller">
    <input type="hidden" name="command" value="sign up">
    <input class="button" type="submit" value="SIGN UP">
</form>
</body>
</html>
