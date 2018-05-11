<%--
  Created by IntelliJ IDEA.
  User: KotoSovik
  Date: 07.05.2018
  Time: 13:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Serach Result</title>
</head>
<body>
<table border="1px" cellpadding="0" cellspacing="0">
    <thead>
    <tr>
        <th width="10%">From</th>
        <th width="10%">Where</th>
        <th width="10%">Route</th>
        <th width="10%">Departure Time</th>
        <th width="10%">Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="train" items="${schedules}">
        <tr>
            <td>${train.stationOfArrival}</td>
            <td>${train.stationOfDeparture}</td>
            <td>${train.route}</td>
            <td>${train.departureTime}</td>

            <td><a href="${pageContext.request.contextPath}/train/edit/${train.id}.html">Edit</a><br/>
                <a href="${pageContext.request.contextPath}/train/delete/${train.id}.html">Delete</a><br/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
