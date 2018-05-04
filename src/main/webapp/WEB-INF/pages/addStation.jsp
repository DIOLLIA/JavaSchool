<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add station</title>
</head>
<body>
<form:form method="POST" commandName="station" action="${pageContext.request.contextPath}/station/add.html">
    <table>
        <tbody>
        <tr>
            <td>Name:</td>
            <td><form:input path="stationName"/></td>
        </tr>
        <tr>
            <td>Coordinate</td>
            <td><form:input path="coordinate"/></td>
        </tr>
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
