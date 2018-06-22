<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ include file="header-admin.jsp" %>

<link rel="stylesheet" href="../../resources/css/tables.css">
<script src="${pageContext.request.contextPath}/resources/js/schedule-constructor.js"></script>

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
                <h2 class="heading mb-2 display-4 font-light probootstrap-animate">
                    <input type="submit"
                           class="btn btn-primary"
                           value="<<"
                           onclick="location='./scheduleList';"/>
                    Schedule &#8195;
                    <form:form method="POST" modelAttribute="schedule"
                               action="/schedule/constructor">
                    <input type="submit" class="btn btn-primary" value="+"/>
                </h2>
                <div class="row align-items-center">
                    <table class="table_user_info" style="width: 100%;">


                        <th style="width: 15%"><select class="js-example-basic-single js-states form-control"
                                                       id="route_picker" name="route_picker" style="width: 100%"
                                                       onchange="stationsOnRoute()">
                        </select></th>

                        <th style="width: 5%"><input type="time" class="form-control" id="arrival_time"
                                                     name="arrival_time">
                        </th>
                        <th style="width: 20%"><select class="js-example-basic-single js-states form-control"
                                                       id="stations_list" name="stations_list" style="width: 100%">
                        </select></th>
                        <th style="width: 5%"><input type="time" class="form-control" id="departure_time"
                                                     name="departure_time">
                        </th>
                        <th style="width: 5%"><input type="text" class="form-control" id="number_in_order"
                                                     name="number_in_order" style="background: white;color:black">
                        </th>
                        <th style="width: 5%"><input type="text" class="form-control" id="daily_route"
                                                     name="daily_route" style="background: white;color:black">
                        </th>
                        <th style="width: 10%"><select class="js-example-basic-single js-states form-control"
                                                       id="train_picker" name="train_picker" style="width: 100%">
                        </select></th>
                    </table>
                </div>
                </form:form>
                <br>
                <div class="row align-items-center">
                    <table class="table_price">
                        <thead>
                        <tr>
                            <th style="width: 15%;">Route</th>
                            <th style="width: 7%;">Arrival time</th>
                            <th style="width: 15%;">Station</th>
                            <th style="width: 7%;">Departure time</th>
                            <th style="width: 5%;">NIO</th>
                            <th style="width: 5%;">DailyId</th>
                            <th style="width: 5%;">Train №</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="schedule" items="${schedule}">
                            <tr>
                                <td>${schedule.routeName.routeName}</td>
                                <td><joda:format pattern="HH:mm" value="${schedule.arrivalTime}"/></td>
                                <td>${schedule.stationName.stationName}</td>
                                <td><joda:format pattern="HH:mm" value="${schedule.departureTime}"/></td>
                                <td>${schedule.routeStationIndex}</td>
                                <td>${schedule.routeDailyId}</td>
                                <td>${schedule.trainNumber.numberOfTrain}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

</section>
<%@ include file="footer.jsp" %>
