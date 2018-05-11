<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h1>Search train page</h1>
<form:form method="POST" modelAttribute="stationSearch" action="${pageContext.request.contextPath}/schedule/search">
    <table>
        <tbody>
        <tr>
            <td>station of arrival</td>
            <td><form:input path="arrivalStation" placeholder="from" name="arrivalStation" required="required"/></td>
        </tr>
        <tr>
            <td>station of destination</td>
            <td><form:input path="departureStation" placeholder="to" name="departureStation" required="required"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Search"/></td>
            <td></td>
        </tr>
        </tbody>
    </table>
</form:form>
</body>
</html>