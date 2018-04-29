<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: KotoSovik
  Date: 26.04.2018
  Time: 12:12
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>stations list</title>
</head>
<body>
<h1>List of stations</h1>
<table border="1px" cellpadding="0" cellspacing="0">
    <thead>
    <tr>
        <th width="10%">Name</th>
        <th width="15%">Number of train</th>
        <th width="10%">time</th>
        <th width="10%">actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="station" items="${stations}">
        <tr>
            <td>${station.name}</td>
            <td>${station.numberOfTrain}</td>
            <td>${station.time}</td>
            <td><a href="${pageContext.request.contextPath}/station/edit/${station.id}.html">Edit</a><br/>
                <a href="${pageContext.request.contextPath}/station/delete/${station.id}.html">Delete</a><br/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br/>
<h2>${message}</h2>
<br>
<input type="submit" value="add station"
       onclick="location='add';"/>
<p><a href="${pageContext.request.contextPath}/index.html">Home page</a></p>

</body>
</html>
