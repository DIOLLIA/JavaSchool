<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ include file="header-admin.jsp" %>
<link rel="stylesheet" href="../../resources/css/tables.css">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section class="probootstrap-cover overflow-hidden relative"
         style="background-image: url('/resources/images/bg_1.jpg');" data-stellar-background-ratio="0.5"
         id="section-home">
    <div class="overlay"></div>
    <div class="container">
        <div class="row align-items-center">
            <div class="col-md probootstrap-animate">
                <div>
                    <h2 class="display-8 probootstrap-section-heading">${message}</h2>
                </div>
                <h2 class="heading mb-2 display-4 font-light probootstrap-animate"><input class="btn btn-primary"
                                                                                          type="submit" value="<<"
                                                                                          onclick="location='/index';"/>
                    Schedule<br>
                    <input type="submit" class="btn btn-primary" value="Route list"
                           onclick="location='/schedule/scheduleList/routeList';"/>&#8195;
                    <input type="submit" class="btn btn-primary" value="Constructor"
                           onclick="location='/schedule/constructor';"/> &#8195;
                </h2>

                <div class="row align-items-center">

                    <div class="col-md probootstrap-animate">

                        <table class="table_price">
                            <thead>
                            <tr>
                                <th>Train №</th>
                                <th>Route</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="schedule" items="${schedule}">
                                <tr>
                                    <td>${schedule.trainNumber.numberOfTrain}</td>
                                    <td>${schedule.routeName.routeName}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/schedule/scheduleList/${schedule.id}
                            ">Show
                                            details</a>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-md probootstrap-animate">
                        <c:if test="${details != null}">
                        <h4 class="display-8 probootstrap-section-heading" style="color:#32CD32 ">Train
                            №${details.get(0).getTrainNumber().getNumberOfTrain()}
                            on ${details.get(0).getRouteName().getRouteName()} route:</h4>
                        <table class="table_price">
                            <thead>
                            <tr>
                                <th>Arrival time</th>
                                <th>Station</th>
                                <th>Departure time</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="schedule" items="${details}">
                                <tr>
                                    <td><joda:format pattern="HH:mm" value="${schedule.arrivalTime}"/></td>
                                    <td>${schedule.stationName.stationName}</td>
                                    <td><joda:format pattern="HH:mm" value="${schedule.departureTime}"/></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>

</section>
<%@ include file="footer.jsp" %>
