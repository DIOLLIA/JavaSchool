<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <th width="10%">Latitude</th>
        <th width="10%">Longitude</th>
        <th width="10%">actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="station" items="${stations}">
        <tr>
            <td>${station.stationName}</td>
            <td>${station.latitude}</td>
            <td>${station.longitude}</td>
            <td><a href="${pageContext.request.contextPath}/station/edit/${station.id}.html">Edit</a><br/>
                <a href="${pageContext.request.contextPath}/station/delete/${station.id}.html">Delete</a><br/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br/>
<h2>${message}</h2>
<br>
<input type="submit" value="<spring:message code="add-station-button"/>"
       onclick="location='add';"/>
<p><a href="${pageContext.request.contextPath}/index.html">Home page</a></p>

</body>
</html>
