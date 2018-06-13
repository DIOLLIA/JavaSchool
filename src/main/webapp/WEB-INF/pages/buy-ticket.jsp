<%@ include file="header.jsp" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section class="probootstrap-cover overflow-hidden relative"
         style="background-image: url('/resources/images/img_2.jpg');" data-stellar-background-ratio="0.5"
         id="section-home">
    <div class="overlay"></div>
    <div class="container">
        <div class="row align-items-center">
            <div class="col-md probootstrap-animate">
                <h2 class="heading mb-2 display-4 font-light probootstrap-animate">
                    <spring:message code="header.buy-ticket"/>
                </h2>
                <h3 class="heading mb-2 display-8 font-light probootstrap-animate"><br> <span
                        style="color:#e0e139;">${message}</span></h3>
                <div class="col-md probootstrap-animate">
                    <form:form name='buyTicket' method="POST" modelAttribute="ticket"
                               action="${pageContext.request.contextPath}/ticket/save" class="probootstrap-form"
                               onsubmit="return ticket_validation();">
                    <div class="form-group">
                        <h4 style="text-align: center"><spring:message code="buy-ticket.header.route"/></h4>
                        <div class="row mb-3">
                            <div class="col-md">
                                <div class="form-group">
                                    <label for="station_from" style="font-size: 120%">
                                        <spring:message code="common.label.from"/>
                                    </label>
                                    <select class="js-example-basic-single js-states form-control"
                                            onchange="getToStations()"
                                            id="station_from" name="stationFrom" style="width: 100%">
                                    </select>
                                </div>
                            </div>
                            <div class="col-md">
                                <div class="form-group">
                                    <label for="station_to" style="font-size:120%">
                                        <spring:message code="common.label.to"/>
                                    </label>
                                    <div class="probootstrap_select-wrap">
                                        <select class="js-example-basic-single js-states form-control"
                                                id="station_to" name="stationTo" style="width: 100%">
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="probootstrap-date-departure" style="font-size: 120%">
                                        <spring:message code="common.label.departure-date"/>
                                    </label>
                                    <div class="probootstrap-date-wrap">
                                        <span class="icon ion-calendar"></span>
                                        <input type="text" id="probootstrap-date-departure" class="form-control"
                                               placeholder="click and pick" name="departureDate" autocomplete="off"
                                               style="width: 100%">
                                        <span id="dateloc" style="color: red"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="departure_time" style="font-size: 120%">
                                        <spring:message code="common.label.departure-time"/>
                                    </label>
                                    <select class="js-example-basic-single js-states form-control"
                                            id="departure_time" name="departureTime" style="width: 100%"  onchange="trainOnTimeSelector()">
                                        <td><joda:format pattern="HH:mm" value="${scheduleItem.departureTime}"/></td>
                                    </select>
                                    <span id="timeloc" style="color: red"></span>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <br>
                                    <input type="button" onclick="trainsAndDateSelector()"
                                           value="<spring:message code="buy-ticket.btn.find"/>"
                                           class="btn btn-primary btn-block" style="width: 100%">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="train" style="font-size: 120%">
                                        <spring:message code="common.label.train-number"/>
                                    </label>
                                    <select class="js-example-basic-single js-states form-control"
                                            id="train" name="trainNumber" style="width: 100%" disabled>

                                    </select>
                                    <span id="trainloc" style="color: red"></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <h4 style="text-align: center"><spring:message code="buy-ticket.header.passenger-data"/></h4>
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="name" style="font-size:120%">
                                    <spring:message code="common.user-first-name"/>
                                </label>
                                <input type="text" class="form-control" id="name" name="name">
                                <span id="nameloc" style="color: red"></span>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="surname" style="font-size:120%">
                                    <spring:message code="common.user-surname"/>
                                </label>
                                <input type="text" class="form-control" id="surname" name="surName">
                                <span id="surnameloc" style="color: red"></span>
                            </div>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-3">
                            <div class="form-group">
                                <label for="birth_date" style="font-size:120%">
                                    <spring:message code="common.user-birthday"/>
                                </label>
                                <input type="date" class="form-control" id="birth_date" name="birthDay">
                                <span id="birthdayloc" style="color: red"></span>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <label for="submit" style="width: 100%;font-size: 120%"> &nbsp;</label>
                            <input type="submit" id="submit" value="Get IT! FREE" class="btn btn-primary btn-block">
                        </div>
                    </div>
                </div>
                </form:form>
            </div>
        </div>
    </div>
    </div>
</section>
<sec:csrfMetaTags/>
<%@ include file="footer.jsp" %>
