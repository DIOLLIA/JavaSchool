<%@ include file="header.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/tables.css">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section class="probootstrap-cover overflow-hidden relative"
         style="background-image: url('/resources/images/img_4.jpg');" data-stellar-background-ratio="0.5"
         id="section-home">
    <div class="overlay"></div>
    <div class="container">
        <div class="row align-items-center">
            <div class="col-md probootstrap-animate">
                <h2 class="heading mb-2 display-4 font-light probootstrap-animate">KudKuda</h2>
                <p class="lead mb-4 probootstrap-animate">Select <span style="color:#32CD32;">station </span> for daily
                    schedule</p>
                <form:form method="POST" modelAttribute="stationSearch"
                           action="${pageContext.request.contextPath}/schedule/searchTrainOnStation">
                    <label for="station_from" style="width: 60%;">
                        <select class="js-example-basic-single js-states form-control"
                                id="station_from" id="station_from" name="stationFrom" style="width: 35%;" >
                        </select>
                    </label>
                    <br>
                    <div class="row">
                        <div class="col-auto">
                            <input type="submit" value="Show trains on station" class="btn btn-primary btn-block">
                        </div>
                    </div>
                    <br>
                    <h3 class="heading mb-2 display-8 font-light probootstrap-animate"><br> <span
                            style="color:#e0e139;">${msg}</span></h3>
                </form:form>
                <table class="table_price" border="0px" cellpadding="0" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th width="5%">Train №</th>
                        <th width="15%">Route</th>
                        <th width="10%">Arrival Time</th>
                        <th width="10%">Departure Time</th>
                        <th width="5%">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="scheduleItems" items="${scheduleItems}">
                        <tr>
                            <td>${scheduleItems.trainNumber.getNumberOfTrain()}</td>
                            <td>${scheduleItems.routeName.routeName}</td>
                            <td>${scheduleItems.arrivalTime}</td>
                            <td>${scheduleItems.departureTime}</td>
                            <td><a href="${pageContext.request.contextPath}/ticket/edit/${train}.html">by ticket</a><br/>
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
