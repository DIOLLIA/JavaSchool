<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<%@ include file="header.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/tables.css">

<section class="probootstrap-cover overflow-hidden relative"
         style="background-image: url('/resources/images/bg_1.jpg');" data-stellar-background-ratio="0.5"
         id="section-home">
    <div class="overlay"></div>
    <div class="container">
        <div class="row align-items-center">
            <div class="col-md">
                <h2 class="heading mb-2 display-4 font-light probootstrap-animate">KudKuda</h2>
                <p class="lead mb-4 probootstrap-animate">
                    <span style="color:#32CD32;font-size: 140%"><spring:message code="subheader.are-you-going"/></span>
            </div>
            <div class="col-md probootstrap-animate">
                <h4 class="display-8 probootstrap-section-heading" style="color:#32CD32 "><input class="btn btn-primary"
                                                                                                 type="submit"
                                                                                                 value="<<"
                                                                                                 onclick="location='./';"/>
                    Train
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

        </div>
    </div>
    </div>
</section>

<%@ include file="footer.jsp" %>