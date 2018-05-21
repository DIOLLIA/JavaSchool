<%@ include file="header.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/tables.css">

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

<%@ include file="footer.jsp" %>