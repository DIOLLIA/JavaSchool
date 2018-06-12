<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp" %>

<section class="probootstrap-cover overflow-hidden relative"
         style="background-image: url('/resources/images/img_5.jpg');" data-stellar-background-ratio="0.5"
         id="section-home">
    <c:choose>
        <c:when test="${empty username}">
            <h2><spring:message code="403-page.user-dont-have-permission"/></h2>
        </c:when>
        <c:otherwise>
            <h2><spring:message code="common.label.username"/> : ${username} <br/>
                <spring:message code="403-page.user-dont-have-permission"/></h2>
        </c:otherwise>
    </c:choose>
    <img src="${pageContext.request.contextPath}/resources/images/access denied.jpg">
</section>
</body>
</html>