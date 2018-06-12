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
                <form:form name='mainSearch' method="POST" modelAttribute="stationSearch"
                           action="${pageContext.request.contextPath}/schedule/search" class="probootstrap-form"
                           onsubmit="return main_search_validation();">
                    <div class="form-group">
                        <div class="row mb-3">
                            <div class="col-md">
                                <div class="form-group">
                                    <label for="station_from" style="width: 100%;font-size: 120%">
                                        <spring:message code="common.label.from"/>
                                    </label>
                                    <select class="js-example-basic-single js-states form-control"
                                            onchange="getToStations()"
                                            id="station_from" name="stationFrom"
                                            style="width: 100%;">
                                    </select>
                                </div>
                            </div>
                            <div class="col-md">
                                <div class="form-group">
                                    <label for="station_to" style="width: 100%;font-size: 120%">
                                        <spring:message code="common.label.to"/>
                                    </label>
                                    <div class="probootstrap_select-wrap">
                                        <select class="js-example-basic-single js-states form-control"
                                                id="station_to" style="width: 100%;" name="stationTo">
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row mb-5">
                            <div class="col-md">
                                <div class="form-group">
                                    <label for="probootstrap-date-departure" style="font-size: 120%">
                                        <spring:message code="common.label.departure-date"/>
                                    </label>
                                    <div class="probootstrap-date-wrap">
                                        <input type="text" id="probootstrap-date-departure" class="form-control"
                                               placeholder="<spring:message code="home.placeholder.click-and-pick"/>"
                                               name="searchDate" style="color: black"
                                               autocomplete="off">
                                        <span id="dateloc" style="color: red"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md">
                                <div class="form-group">
                                    <label for="departure-time" style="font-size: 120%">
                                        <spring:message code="common.label.departure-time"/>
                                    </label>
                                    <div class="probootstrap-date-wrap">
                                        <input type="time" id="departure-time" value="now" class="form-control"
                                               name="searchTime">
                                        <span id="timeloc" style="color: red"></span>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-auto">
                                <input type="submit" value="<spring:message code="common.btn.search"/>"
                                       class="btn btn-primary btn-block">
                            </div>
                        </div>
                    </div>
                </form:form>
            </div>

            <c:if test="${searchResult != null}">
                <div class="row-md probootstrap-animate">
                    <table class="table_price" border="0px" cellpadding="0" cellspacing="0" width="100%">
                        <thead>
                        <tr>
                            <th width="15%"><spring:message code="common.label.from"/></th>
                            <th width="15%"><spring:message code="common.label.to"/></th>
                            <th width="10%"><spring:message code="home.btn.train-number"/></th>
                            <th width="10%"><spring:message code="common.label.departure-time"/></th>
                            <th width="10%"><spring:message code="common.label.arrival-time"/></th>
                            <th width="10%"><spring:message code="common.label.arrival-action"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="scheduleItem" items="${searchResult}">
                            <tr>
                                <td>${scheduleItem.stationOfDeparture}</td>
                                <td>${scheduleItem.stationOfArrival}</td>
                                <td>${scheduleItem.trainNumber}</td>
                                <td><joda:format pattern="HH:mm" value="${scheduleItem.departureTime}"/></td>
                                <td><joda:format pattern="HH:mm" value="${scheduleItem.arrivalTime}"/></td>
                                <td><a href="${pageContext.request.contextPath}/ticket/buy/">
                                    <spring:message code="common.btn.buy-ticket"/></a>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>


        </div>
    </div>
</section>
<%@ include file="footer.jsp" %>