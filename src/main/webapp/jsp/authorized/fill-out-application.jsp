<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/jsp/parts/header.jsp" %>

<fmt:message bundle="${loc}" key="local.appl.field.school-grade" var="schoolGrade"/>
<fmt:message bundle="${loc}" key="local.appl.field.school-grade.rule" var="schoolGradeRule"/>
<fmt:message bundle="${loc}" key="local.appl.field.exams" var="examGrades"/>
<fmt:message bundle="${loc}" key="local.appl.field.exam-grade.rule" var="examGradeRule"/>
<fmt:message bundle="${loc}" key="local.appl.button.submit" var="sendApplication"/>
<fmt:message bundle="${loc}" key="local.appl.field.speciality" var="speciality"/>

<fmt:message bundle="${loc}" key="local.subject.physics" var="physics"/>
<fmt:message bundle="${loc}" key="local.subject.mathematics" var="mathematics"/>
<fmt:message bundle="${loc}" key="local.subject.russian" var="russian"/>
<fmt:message bundle="${loc}" key="local.subject.biology" var="biology"/>
<fmt:message bundle="${loc}" key="local.subject.english" var="english"/>
<fmt:message bundle="${loc}" key="local.subject.chemistry" var="chemistry"/>
<fmt:message bundle="${loc}" key="local.subject.social-science" var="socialScience"/>
<fmt:message bundle="${loc}" key="local.subject.history" var="history"/>

<fmt:message bundle="${loc}" key="local.speciality.metaphisics-and-ontology" var="metaphisics"/>
<fmt:message bundle="${loc}" key="local.speciality.social-philosophy" var="socialPhilosophy"/>
<fmt:message bundle="${loc}" key="local.speciality.philosophy-of-science" var="philosophyOfScience"/>
<fmt:message bundle="${loc}" key="local.speciality.computer-physics" var="computerPhysics"/>
<fmt:message bundle="${loc}" key="local.speciality.physics-of-nanomaterials-and-nanotechnologies" var="nanomaterials"/>
<fmt:message bundle="${loc}" key="local.speciality.nuclear-physics" var="nuclearPhysics"/>
<fmt:message bundle="${loc}" key="local.speciality.biochemistry" var="biochemistry"/>
<fmt:message bundle="${loc}" key="local.speciality.microbiology" var="microbiology"/>
<fmt:message bundle="${loc}" key="local.speciality.bioecology" var="bioecology"/>
<fmt:message bundle="${loc}" key="local.speciality.fundamental-chemistry" var="fundamentalChemistry"/>
<fmt:message bundle="${loc}" key="local.speciality.high-energy-chemistry" var="highEnergyChemistry"/>
<fmt:message bundle="${loc}" key="local.speciality.pharmaceutical-chemistry" var="pharmaceuticalChemistry"/>
<fmt:message bundle="${loc}" key="local.speciality.software-development" var="softwareDevelopment"/>
<fmt:message bundle="${loc}" key="local.speciality.infocommunication-technologies" var="infocommunication"/>

<html>
<head>
    <title>Fill Out Application</title>
</head>
<body>
<form method="post" action="/controller">
    <input type="hidden" name="command" value="send application"/>

    <c:out value="${schoolGrade}"/><br>

    <input type="text" required="required"
           name ="schoolCertificate" id="schoolCertificate" tabindex="1"
           pattern="[0-9]{1,3}" title="${schoolGradeRule}">
    <br><br>

    <c:out value="${examGrades}"/><br><br>
    <c:choose>
        <c:when test="${requestScope.faculty.firstSubject eq 'PHYSICS'}">
        ${physics}
        </c:when>
        <c:when test="${requestScope.faculty.firstSubject eq 'MATHEMATICS'}">
            ${mathematics}
        </c:when>
        <c:when test="${requestScope.faculty.firstSubject eq 'RUSSIAN'}">
            ${russian}
        </c:when>
        <c:when test="${requestScope.faculty.firstSubject eq 'BIOLOGY'}">
            ${biology}
        </c:when>
        <c:when test="${requestScope.faculty.firstSubject eq 'ENGLISH'}">
            ${english}
        </c:when>
        <c:when test="${requestScope.faculty.firstSubject eq 'CHEMISTRY'}">
            ${chemistry}
        </c:when>
        <c:when test="${requestScope.faculty.firstSubject eq 'SOCIAL_SCIENCE'}">
            ${socialScience}
        </c:when>
        <c:when test="${requestScope.faculty.firstSubject eq 'HISTORY'}">
            ${history}
        </c:when>
    </c:choose>
    <br>
    <input type="text" required="required"
           name ="firstExamRange" id="firstExamRange" tabindex="2"
           pattern="[0-9]{1,3}" title="${examGradeRule}">
    <br>

    <c:choose>
        <c:when test="${requestScope.faculty.secondSubject eq 'PHYSICS'}">
            ${physics}
        </c:when>
        <c:when test="${requestScope.faculty.secondSubject eq 'MATHEMATICS'}">
            ${mathematics}
        </c:when>
        <c:when test="${requestScope.faculty.secondSubject eq 'RUSSIAN'}">
            ${russian}
        </c:when>
        <c:when test="${requestScope.faculty.secondSubject eq 'BIOLOGY'}">
            ${biology}
        </c:when>
        <c:when test="${requestScope.faculty.secondSubject eq 'ENGLISH'}">
            ${english}
        </c:when>
        <c:when test="${requestScope.faculty.secondSubject eq 'CHEMISTRY'}">
            ${chemistry}
        </c:when>
        <c:when test="${requestScope.faculty.secondSubject eq 'SOCIAL_SCIENCE'}">
            ${socialScience}
        </c:when>
        <c:when test="${requestScope.faculty.secondSubject eq 'HISTORY'}">
            ${history}
        </c:when>
    </c:choose>
    <br>
    <input type="text" required="required"
           name ="secondExamRange" id="secondExamRange" tabindex="3"
           pattern="[0-9]{1,3}" title="${examGradeRule}">
    <br>

    <c:choose>
        <c:when test="${requestScope.faculty.thirdSubject eq 'PHYSICS'}">
            ${physics}
        </c:when>
        <c:when test="${requestScope.faculty.thirdSubject eq 'MATHEMATICS'}">
            ${mathematics}
        </c:when>
        <c:when test="${requestScope.faculty.thirdSubject eq 'RUSSIAN'}">
            ${russian}
        </c:when>
        <c:when test="${requestScope.faculty.thirdSubject eq 'BIOLOGY'}">
            ${biology}
        </c:when>
        <c:when test="${requestScope.faculty.thirdSubject eq 'ENGLISH'}">
            ${english}
        </c:when>
        <c:when test="${requestScope.faculty.thirdSubject eq 'CHEMISTRY'}">
            ${chemistry}
        </c:when>
        <c:when test="${requestScope.faculty.thirdSubject eq 'SOCIAL_SCIENCE'}">
            ${socialScience}
        </c:when>
        <c:when test="${requestScope.faculty.thirdSubject eq 'HISTORY'}">
            ${history}
        </c:when>
    </c:choose>
    <br>
    <input type="text" required="required"
           name ="thirdExamRange" id="thirdExamRange" tabindex="4"
           pattern="[0-9]{1,3}" title="${examGradeRule}">
    <br><br>

    <c:out value="${speciality}"/><br>
    <select class="form-control selectpicker" required="required"
            name="speciality" title="Speciality">
        <c:forEach items="${requestScope.faculty.setOfSpecialities}" var="speciality">
            <option value="${speciality}">
                <c:choose>
                    <c:when test="${speciality eq 'METAPHYSICS_AND_ONTOLOGY'}">
                        ${metaphisics}
                    </c:when>
                    <c:when test="${speciality eq 'SOCIAL_PHILOSOPHY'}">
                        ${socialPhilosophy}
                    </c:when>
                    <c:when test="${speciality eq 'PHILOSOPHY_OF_SCIENCE'}">
                        ${philosophyOfScience}
                    </c:when>
                    <c:when test="${speciality eq 'COMPUTER_PHYSICS'}">
                        ${computerPhysics}
                    </c:when>
                    <c:when test="${speciality eq 'PHYSICS_OF_NANOMATERIALS_AND_NANOTECHNOLOGIES'}">
                        ${nanomaterials}
                    </c:when>
                    <c:when test="${speciality eq 'NUCLEAR_PHYSICS'}">
                        ${nuclearPhysics}
                    </c:when>
                    <c:when test="${speciality eq 'BIOCHEMISTRY'}">
                        ${biochemistry}
                    </c:when>
                    <c:when test="${speciality eq 'MICROBIOLOGY'}">
                        ${microbiology}
                    </c:when>
                    <c:when test="${speciality eq 'BIOECOLOGY'}">
                        ${bioecology}
                    </c:when>
                    <c:when test="${speciality eq 'FUNDAMENTAL_CHEMISTRY'}">
                        ${fundamentalChemistry}
                    </c:when>
                    <c:when test="${speciality eq 'HIGH_ENERGY_CHEMISTRY'}">
                        ${highEnergyChemistry}
                    </c:when>
                    <c:when test="${speciality eq 'PHARMACEUTICAL_CHEMISTRY'}">
                        ${pharmaceuticalChemistry}
                    </c:when>
                    <c:when test="${speciality eq 'COMPUTER_SECURITY'}">
                        ${metaphisics}
                    </c:when>
                    <c:when test="${speciality eq 'SOFTWARE_DEVELOPMENT'}">
                        ${softwareDevelopment}
                    </c:when>
                    <c:when test="${speciality eq 'INFOCOMMUNICATION_TECHNOLOGIES'}">
                        ${infocommunication}
                    </c:when>
                </c:choose>
            </option>
        </c:forEach>
    </select>
    <br><br>

    <input class="button" type="submit" value="${sendApplication}">
</form>
</body>
</html>
