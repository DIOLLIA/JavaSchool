<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ include file="header.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}resources/css/tables.css">

<section class="probootstrap-cover overflow-hidden relative"
         style="background-image: url('/resources/images/bg_1.jpg');" data-stellar-background-ratio="0.5"
         id="section-home">
    <div class="overlay"></div>
    <div class="container">
        <div class="row">
            <div class="col-md probootstrap-animate">
                <h2 class="heading mb-2 display-4 font-light probootstrap-animate">
                    <spring:message code="personal-data.profile"/>
                </h2>
                <h2>${message}</h2>
                <table class="table_user_info" style="width: 100%">
                    <thead>
                    <tr>
                        <th><spring:message code="common.user-first-name"/></th>
                        <td><c:out value="${user.name}"/></td>
                    </tr>
                    <tr>
                        <th><spring:message code="common.user-surname"/></th>
                        <td><c:out value="${user.surname}"/></td>
                    </tr>
                    <tr>
                        <th><spring:message code="common.user-birthday"/></th>
                        <td><c:out value="${user.birthDay}"/></td>
                    </tr>
                    <tr>
                        <th><spring:message code="common.user-email"/></th>
                        <td><c:out value="${user.email}"/></td>
                    </tr>
                    <%--TODO This realization are planned--%>
                    <%--      <tr>
                              <th><a href="${pageContext.request.contextPath}/user/changePassword/${user.id}">
                                  <spring:message code="personal-data.btn.change-password"/></a>
                                  <br>
                                  <br>
                                  <a href="${pageContext.request.contextPath}/user/edit/${user.id}">
                                      <spring:message code="personal-data.btn.edit"/></a>
                              </th>
                              <td>
                                  <a href="${pageContext.request.contextPath}/user/edit/${user.id}">
                                      <spring:message code="personal-data.btn.delete"/></a>
                              </td>
                          </tr>--%>
                    </thead>
                </table>
                <br>
            </div>
            <div class="col-md">
                <h2 class="heading mb-2 display-4 font-light probootstrap-animate">
                    <spring:message code="personal-data.header.your-tickets"/>
                </h2>
                <c:forEach var="ticket" items="${ticket}">
                    TICKET#${ticket.departureDateTime}(&@${ticket.user.surname})
                    <p><joda:format pattern="d MMMM yyyy ,HH:mm" value="${ticket.departureDateTime}"/></p>
                    <p><spring:message code="home.btn.train-number"/>: ${ticket.train.numberOfTrain}</p>
                    <p><spring:message
                            code="common.label.station"/>: ${ticket.departureSchedule.stationName.stationName}</p>
                    -----------------------------------------------------------------------------<br>

                </c:forEach>
            </div>
        </div>
    </div>
    </div>
</section>

<%@ include file="footer.jsp" %>
