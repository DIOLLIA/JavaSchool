<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
    <title>Users list</title>
</head>
<body>
<h1>List of users</h1>
<table border="1px" cellpadding="0" cellspacing="0">
    <thead>
    <tr>
        <th width="10%">Name</th>
        <th width="10%">Surname</th>
        <th width="10%">Birth Date</th>
        <th width="10%">E-mail</th>
        <th width="10%">actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.name}</td>
            <td>${user.surname}</td>
            <td>${user.birthDay}</td>
            <td>${user.email}</td>
            <td><a href="${pageContext.request.contextPath}/user/edit/${user.id}.html">Edit</a><br/>
                <a href="${pageContext.request.contextPath}/user/delete/${user.id}.html">Delete</a><br/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br/>
<h2>${message}</h2>
<br>
<input type="submit" value="add user"
       onclick="location='add';"/>
<p><a href="${pageContext.request.contextPath}/index.html">Home page</a></p>

</body>
</html>