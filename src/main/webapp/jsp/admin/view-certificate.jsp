<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/parts/header.jsp" %>
<html>
<head>
    <title>Certificate</title>
</head>
<body>
<table>
    <tr>
        <th>School Average Grade</th>
        <th><c:out value="${requestScope.certificate.schoolGrade}"/></th>
    </tr>
</table>
<c:out value="Exam grades"/>
<table>
    <c:forEach items="${requestScope.certificate.examGrades}" var="exam">
        <tr>
            <th><c:out value="${exam.subject}"/></th>
            <th><c:out value="${exam.grade}"/></th>
        </tr>
    </c:forEach>
</table>
</body>
</html>
