<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add train</title>
</head>
<body>
<h1>Add train page</h1>
<form:form method="POST" commandName="train" action="${pageContext.request.contextPath}/train/add">
    <table>
        <tbody>
        <tr>
            <td>Number:</td>
            <td><form:input path="numberOfTrain"/></td>
        </tr>
        <tr>
            <td>Seats:</td>
            <td><form:input path="seats"/></td>
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
