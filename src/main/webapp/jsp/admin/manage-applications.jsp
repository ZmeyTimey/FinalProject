<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/parts/header.jsp" %>
<fmt:message bundle="${loc}" key="local.appl-manage.button.certificates" var="certButton"/>
<html>
<head>
    <title>Entrant Applications</title>
</head>
<body>
<table>
    <tr>
        <th>ID</th>
        <th>Login</th>
        <th>Name</th>
        <th>Patronymic</th>
        <th>Surname</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Faculty</th>
        <th>Speciality</th>
        <th>Confirmed</th>
    </tr>
    <c:forEach items="${requestScope.userList}" var ="user">
        <tr>
            <th><c:out value="${user.id}"/></th>
            <th><c:out value="${user.login}"/></th>
            <th><c:out value="${user.name}"/></th>
            <th><c:out value="${user.middlename}"/></th>
            <th><c:out value="${user.surname}"/></th>
            <th><c:out value="${user.email}"/></th>
            <th><c:out value="375${user.phone}"/></th>
            <th><c:out value="${user.facultyId}"/></th>
            <th><c:out value="${user.speciality}"/></th>
            <th><c:out value="${user.isApplicationConfirmed}"/></th>
            <th>
                <%--<form method="post" action="/controller">--%>
                    <%--<input type="hidden" name="command" value="view_certificate">--%>
                    <a href="/controller?command=view_certificate=${user.id}">
                    <button>${certButton}</button>
                    </a>
                    <%--<input class="button" type="submit" value="${certButton}">--%>
                <%--</form>--%>
            </th>
        </tr>
    </c:forEach>
</table>

<form method="post" action="/controller">
    <input type="hidden" name="command" value="logout">
    <input class="button" type="submit" value="LOGOUT">
</form>
</body>
</html>
