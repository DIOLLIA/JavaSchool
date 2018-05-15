<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add user</title>
</head>
<body>
<h1>Add user page</h1>
<form:form method="POST" modelAttribute="user" action="${pageContext.request.contextPath}/user/add2">
    <table>
        <tbody>
        <tr>
            <td>Name:</td>
            <td><form:input path="name"/></td>
        </tr>
        <tr>
            <td>Surname:</td>
            <td><form:input path="surname"/></td>
        </tr>
        <tr>
            <td>Birth day</td>
            <td><form:input path="birthDay" placeholder="yyyy-MM-dd"/></td>
        </tr>
        <tr>
            <td>E-mail:</td>
            <td><form:input path="email"/></td>
        </tr>
        <%--<tr>--%>
            <%--<td>E-mail:</td>--%>
            <%--<td><form:input path="email"/></td>--%>
        <%--</tr>--%>
        <tr>
            <td><input type="submit" value="Add"/></td>
            <td></td>
        </tr>
        </tbody>
    </table>
</form:form>
<p><a href="${pageContext.request.contextPath}/index.html">Home page</a></p>
</body>
</html>
