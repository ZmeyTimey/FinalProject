<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/parts/header.jsp" %>
<html>
<head>
    <title>ADMIN</title>
</head>
<body>
<h1>Hello, admin!</h1>
<form method="post" action="/controller">
    <input type="hidden" name="command" value="go to user list"/>
    <input class="button" type="submit" value="CERTIFICATE VERIFICATION MENU"/>
</form>
<form method="post" action="/controller">
    <input type="hidden" name="command" value="go to competition management">
    <input class="button" type="submit" value="COMPETITION MANAGEMENT MENU">
</form>
<form method="post" action="/controller">
    <input type="hidden" name="command" value="logout">
    <input class="button" type="submit" value="LOGOUT">
</form>
</body>
</html>
