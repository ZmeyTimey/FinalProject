<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h1>Sign up</h1><br>
<form method="post" action="/controller">
    <input type="hidden" name="command" value="registration" />

    <input type="text" required placeholder="login" name ="login"><br><br>
    <input type="password" required placeholder="password" name ="password"><br><br>
    <input type="password" required placeholder="confirm password" name="confirm password"><br><br>
    <input type="text" required placeholder="name" name="name"><br><br>
    <input type="text" required placeholder="middlename" name="middlename"><br><br>
    <input type="text" required placeholder="surname" name="surname"><br><br>
    <input type="text" required placeholder="email" name="email"><br><br>
    <input type="text" required placeholder="telephone" name="telephone"><br><br>

    <select class="form-control" name="faculty" title="Faculty" required="required">
        <%--<option selected="selected" value="Факультет Физики">Факультет Физики</option>--%>
        <%--<option value="Биологический Факультет">Биологический Факультет</option>--%>
        <%--<option value="Химический Факультет">Химический Факультет</option>--%>
        <%--<option value="Философский Факультет">Философский Факультет</option>--%>
        <%--<option value="Факультет Информатики">Факультет Информатики</option>--%>
            <option disabled>Select faculty</option>
        <option value="Faculty of Physics">Faculty of Physics</option>
        <option value="Department of Biology">Department of Biology</option>
        <option value="Chemical faculty">Chemical Faculty</option>
        <option value="Faculty of Philosophy">Faculty of Philosophy</option>
        <option value="Faculty of Informatics">Faculty of Informatics</option>
    </select>

    <input type="submit" value="Enter">
</form>
</body>
</html>
