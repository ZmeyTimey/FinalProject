<%--
  Created by IntelliJ IDEA.
  User: Timey
  Date: 14.09.2018
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div class="form">
    <h1>Sign in</h1><br>
    <form method="post" action="controller">
        <input type="hidden" name="command" value="login" />
        <input type="text" required placeholder="login" name ="login"><br>
        <input type="password" required placeholder="password" name ="password"><br><br>
        <input class="button" type="submit" value="Enter">
    </form>
</div>
</body>
</html>
