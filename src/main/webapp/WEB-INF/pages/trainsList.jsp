<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<?xml version="1.0" encoding="ISO-8859-1" ?>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
    <title>List of trains</title>
</head>
<body>
<h1>List of trains</h1>
<table border="1px" cellpadding="0" cellspacing="0">
    <thead>
    <tr>
        <th width="10%">Number</th>
        <th width="15%">Stations</th>
        <th width="10%">Seats</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="train" items="${trains}">
        <tr>
            <td>${train.number}</td>
            <td>${train.station}</td>
            <td>${train.seats}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>