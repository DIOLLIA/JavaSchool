<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp" %>

<section class="probootstrap-cover overflow-hidden relative"
		 style="background-image: url('/resources/images/img_5.jpg');" data-stellar-background-ratio="0.5"
		 id="section-home">
	<c:choose>
		<c:when test="${empty username}">
			<h2>You do not have permission to access this page!</h2>
		</c:when>
		<c:otherwise>
			<h2>Username : ${username} <br/>You do not have permission to access this page!</h2>
		</c:otherwise>
	</c:choose>
	<img src="${pageContext.request.contextPath}/resources/images/access denied.jpg">
</section>
</body>
</html>