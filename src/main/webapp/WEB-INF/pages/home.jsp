<%@ include file="header.jsp" %>

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
                <form:form method="POST" modelAttribute="stationSearch"
                           action="${pageContext.request.contextPath}/schedule/search" class="probootstrap-form">
                    <div class="form-group">
                        <div class="row mb-3">
                            <div class="col-md">
                                <div class="form-group">
                                    <label for="station_from">From</label>
                                    <label for="station_from" style="width: 100%;">
                                        <select class="js-example-basic-single js-states form-control"
                                                onchange="getToStations()"
                                                id="station_from" name="stationFrom"
                                                style="width: 100%;">
                                        </select>
                                    </label>
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
                        <!-- END row -->
                        <div class="row mb-5">
                            <div class="col-md">
                                <div class="form-group">
                                    <label for="probootstrap-date-departure">Departure date</label>
                                    <div class="probootstrap-date-wrap">
                                        <span class="icon ion-calendar"></span>
                                        <input type="text" id="probootstrap-date-departure" class="form-control"
                                               placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-md">
                                <div class="form-group">
                                    <label for="departure-time">Departure time</label>
                                    <div class="probootstrap-date-wrap">
                                        <span class="icon ion-calendar"></span>
                                        <input type="time" id="departure-time" value="now" class="form-control"
                                               placeholder="">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- END row -->
                        <div class="row">
                            <div class="col-auto">
                                <input type="submit" value="Search" class="btn btn-primary btn-block">
                            </div>
                        </div>
                    </div>
                </form:form> <%-- добавили form: --%>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6 probootstrap-animate">

        </div>
        <div class="col-md-6  probootstrap-animate">
            <p class="display-4  mb-4 probootstrap-animate">
                <a>go to search<br></a>
                <a href="../schedule/searchTrainOnStation">Station schedule</a></p>
        </div>
    </div>
</section>

<%@ include file="footer.jsp" %>