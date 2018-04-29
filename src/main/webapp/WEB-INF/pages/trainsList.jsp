<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<?xml version="1.0" encoding="ISO-8859-1" ?>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
    <title>trains list</title>
</head>
<body>
<h1>List of trains</h1>
<table border="1px" cellpadding="0" cellspacing="0">
    <thead>
    <tr>
        <th width="10%">Number</th>
        <th width="15%">Stations</th>
        <th width="10%">Seats</th>
        <th width="10%">actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="train" items="${trains}">
        <tr>
            <td>${train.numberOfTrain}</td>
            <td>${train.station}</td>
            <td>${train.seats}</td>
            <td><a href="${pageContext.request.contextPath}/train/edit/${train.id}.html">Edit</a><br/>
                <a href="${pageContext.request.contextPath}/train/delete/${train.id}.html">Delete</a><br/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br/>
<h2>${message}</h2>
<br>
<input type="submit" value="add train"
       onclick="location='add';"/>
<p><a href="${pageContext.request.contextPath}/index.html">Home page</a></p>

</body>
</html>