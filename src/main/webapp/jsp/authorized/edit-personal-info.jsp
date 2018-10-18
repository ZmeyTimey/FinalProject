<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/jsp/parts/header.jsp" %>
<fmt:message bundle="${loc}" key="local.reg.field.name.rule" var="regNameRule"/>
<fmt:message bundle="${loc}" key="local.reg.field.middlename.rule" var="regMiddlenameRule"/>
<fmt:message bundle="${loc}" key="local.reg.field.surname.rule" var="regSurnameRule"/>
<fmt:message bundle="${loc}" key="local.reg.field.email.rule" var="regEmailRule"/>
<fmt:message bundle="${loc}" key="local.reg.field.phone.rule" var="regPhoneRule"/>
<fmt:message bundle="${loc}" key="local.edit-user-info.button.submit" var="editUserButtonSubmit"/>

<html>
<head>
    <title>Personal Information</title>
</head>
<body>
<form method="post" action="/controller">
    <input type="hidden" name="command" value="edit personal info"/>

    <input type="text" name="name" id="name"
           required placeholder="name"
           value="${requestScope.user.name}"
           tabindex="1"
           pattern="[a-zA-Zа-яА-Я]{2,20}"
           title="${regNameRule}"><br><br>
    <input type="text" name="middlename" id="middlename"
           required placeholder="middlename"
           value="${requestScope.user.middlename}"
           tabindex="2"
           pattern="[a-zA-Zа-яА-Я]{2,20}"
           title="${regMiddlenameRule}"><br><br>
    <input type="text" name="surname" id="surname"
           required placeholder="surname"
           value="${requestScope.user.surname}"
           tabindex="3"
           pattern="[a-zA-Zа-яА-Я]{2,20}"
           title="${regSurnameRule}"><br><br>
    <input type="email" name="email" id="email" required="required"
           value="${requestScope.user.email}"
           tabindex="4"
           pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"
           title="${regEmailRule}"><br><br>
    <input type="text" name="phone" id="phone" required="required"
           value="${requestScope.user.phone}"
           tabindex="5"
           pattern="^[0-9]{2,2}\s[0-9]{7,7}$"
           title="title="${regPhoneRule}"><br><br>
    <input class="button" type="submit" value="${editUserButtonSubmit}">
</form>
</body>
</html>
