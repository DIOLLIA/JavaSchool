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
                    <span style="color:#32CD32;">are you going?</span>
            </div>
            <div class="col-md probootstrap-animate">
                <form:form name='mainSearch' method="POST" modelAttribute="stationSearch"
                           action="${pageContext.request.contextPath}/schedule/search" class="probootstrap-form"
                           onsubmit="return main_search_validation();">
                    <div class="form-group">
                        <div class="row mb-3">
                            <div class="col-md">
                                <div class="form-group">
                                    <label for="station_from" style="width: 100%;">From</label>
                                    <select class="js-example-basic-single js-states form-control"
                                            onchange="getToStations()"
                                            id="station_from" name="stationFrom"
                                            style="width: 100%;">
                                    </select>
                                </div>
                            </div>
                            <div class="col-md">
                                <div class="form-group">
                                    <label for="station_to">To</label>
                                    <div class="probootstrap_select-wrap">
                                        <label for="station_to" style="width: 100%;">
                                            <select class="js-example-basic-single js-states form-control"
                                                    id="station_to" style="width: 100%;" name="stationTo">
                                            </select>
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row mb-5">
                            <div class="col-md">
                                <div class="form-group">
                                    <label for="probootstrap-date-departure">Departure date</label>
                                    <div class="probootstrap-date-wrap">
                                        <input type="text" id="probootstrap-date-departure" class="form-control"
                                               placeholder="click and pick" name="searchDate" style="color: black"
                                               autocomplete="off">
                                        <span id="dateloc" style="color: red"></span></td></tr>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md">
                                <div class="form-group">
                                    <label for="departure-time">Departure time</label>
                                    <div class="probootstrap-date-wrap">
                                        <input type="time" id="departure-time" value="now" class="form-control"
                                               name="searchTime">
                                        <span id="timeloc" style="color: red"></span></td></tr>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-auto">
                                <input type="submit" value="Search" class="btn btn-primary btn-block">
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
                            <th width="15%">From</th>
                            <th width="15%">Where</th>
                            <th width="10%">Train number</th>
                            <th width="10%">Departure Time</th>
                            <th width="10%">Arrival Time</th>
                            <th width="10%">Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="scheduleItem" items="${searchResult}">
                            <tr>
                                <td>${scheduleItem.stationOfDeparture}</td>
                                <td>${scheduleItem.stationOfArrival}</td>
                                <td>${scheduleItem.trainNumber}</td>
                                <td>${scheduleItem.departureTime}</td>
                                <td>${scheduleItem.arrivalTime}</td>
                                <td><a href="${pageContext.request.contextPath}/ticket/buy/">buy ticket</a><br/>
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