<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ include file="header-admin.jsp" %>
<%@ page pageEncoding="UTF-8" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/tables.css">

<section class="probootstrap-cover overflow-hidden relative"
         style="background-image: url('/resources/images/img_4.jpg');" data-stellar-background-ratio="0.5"
         id="section-home">
    <div class="overlay"></div>
    <div class="container">
        <div class="row align-items-center">
            <div class="col-md probootstrap-animate">
                <h2 class="heading mb-2 display-4 font-light probootstrap-animate">KudKuda</h2>
                <p class="lead mb-4 probootstrap-animate">Pick <span style="color:#32CD32;">date </span> and press
                    <span style="color:#32CD32;">Show </span> button to see passengers list </p>
                <div class="row mb-3">

                    <div class="col-md-3"><h5><span style="color:white;">Departure date: </span></h5>
                        <br>
                        <div class="probootstrap-date-wrap">
                            <input type="text" id="probootstrap-date-departure" class="form-control"
                                   placeholder="click and pick" style="background-color: white; color: black"
                                   autocomplete="off">
                        </div>
                    </div>


                    <div class="col-md-4">
                        <div class="col-auto">
                            <input type="submit" value="Back to trains list" class="btn btn-primary btn-block"
                                   onclick="location='${pageContext.request.contextPath}/train/list';"/>
                        </div>
                    </div>
                </div>


                <h3 class="heading mb-2 display-8 font-light probootstrap-animate"><br> <span
                        style="color:#e0e139;">${msg}</span></h3>

                <table class="table_price" border="0px" cellpadding="0" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th width="15%">From</th>
                        <th width="15%">Where</th>
                        <th width="10%">Departure Time</th>
                        <th width="10%">Arrival Time</th>
                        <th width="10%">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="scheduleItem" items="${scheduleByTrainId}">
                        <tr>
                            <td>${scheduleItem.stationOfDeparture}</td>
                            <td>${scheduleItem.stationOfArrival}</td>
                            <td><joda:format pattern="HH:mm" value="${scheduleItem.departureTime}"/></td>
                            <td><joda:format pattern="HH:mm" value="${scheduleItem.arrivalTime}"/></td>
                            <td><a href="#"
                                   onclick="showRoutePassengersByDate('${train.id}','${scheduleItem.scheduleDailyRouteId}', '${scheduleItem.departureTime}')">Show</a><br/>
                            </td>
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
