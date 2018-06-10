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
                <h2 class="heading mb-2 display-4 font-light probootstrap-animate">Schedule &#8195;<input type="submit"
                                                                                                          class="btn btn-primary"
                                                                                                          value="New schedule"
                                                                                                          onclick="location='';"/>&#8195;
                    <input type="submit" class="btn btn-primary" value="New route" onclick="location='';"/>
                </h2>

                <table class="table_price" border="0px" cellpadding="0" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th width="10%">Train №</th>
                        <th width="20%">Station</th>
                        <th width="10%">Arrival time</th>
                        <th width="10%">Departure time</th>
                        <th width="20%">Route</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="schedule" items="${schedule}">
                        <tr>
                            <td>${schedule.trainNumber.numberOfTrain}</td>
                            <td>${schedule.stationName.stationName}</td>
                            <td><joda:format pattern="HH:mm" value="${schedule.arrivalTime}"/></td>
                            <td><joda:format pattern="HH:mm" value="${schedule.departureTime}"/></td>
                            <td>${schedule.routeName.routeName}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</section>
<%@ include file="footer.jsp" %>
