<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/parts/header.jsp" %>

<fmt:message bundle="${loc}" key="local.message.application-sent" var="applicationSent"/>
<fmt:message bundle="${loc}" key="local.message.enlisted" var="enlisted"/>
<fmt:message bundle="${loc}" key="local.message.not-enlisted" var="notEnlisted"/>

<html>
<head>
    <title>ENTRANT</title>
</head>
<body>
<h1>Hello, entrant!</h1>
<form method="post" action="/controller">

    <c:if test="${sessionScope.enlisted == true}">
        <c:out value="${enlisted}"/>
        <br><br>
    </c:if>

    <c:if test="${sessionScope.enlisted == false}">
        <c:out value="${notEnlisted}"/>
        <br><br>
    </c:if>

    <c:if test="${sessionScope.enlisted == null}">
    <c:if test="${sessionScope.applicationSent == false}">
        <input type="hidden" name="command" value="go to application">
        <input class="button" type="submit" value="ACADEMIC INFO">
        <br>
    </c:if>
    <c:if test="${sessionScope.applicationSent == true}">
        <c:out value="${applicationSent}"/>
    </c:if>
    </c:if>
</form>
<form method="post" action="/controller">
    <input type="hidden" name="command" value="personal info">
    <input class="button" type="submit" value="PERSONAL INFO">
</form>

<form method="post" action="/controller">
    <input type="hidden" name="command" value="logout">
    <input class="button" type="submit" value="LOGOUT">
</form>
</body>
</html>
