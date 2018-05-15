<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <th width="10%">Train number</th>
        <th width="10%">Departure Time</th>
        <th width="10%">Arrival Time</th>
        <th width="10%">Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="scheduleItem" items="${searchResult}">
        <tr>
            <td>${scheduleItem.stationOfDeparture}</td>
            <td>${scheduleItem.stationOfArrival}</td>
            <td>${scheduleItem.trainNumber}</td>
            <td>${scheduleItem.departureTime}</td>
            <td>${scheduleItem.arrivalTime}</td>

            <td><a href="${pageContext.request.contextPath}/train/edit/${train.id}.html">Edit</a><br/>
                <a href="${pageContext.request.contextPath}/train/delete/${train.id}.html">Delete</a><br/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
