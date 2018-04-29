<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%--
  Created by IntelliJ IDEA.
  User: KotoSovik
  Date: 25.04.2018
  Time: 23:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add train</title>
</head>
<body>
<h1>Add train page</h1>
<form:form method="POST" commandName="train" action="${pageContext.request.contextPath}/train/add.html">
    <table>
        <tbody>
        <tr>
            <td>Number:</td>
            <td><form:input path="numberOfTrain"/></td>
        </tr>
        <tr>
            <td>Stations:</td>
            <td><form:input path="station"/></td>
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