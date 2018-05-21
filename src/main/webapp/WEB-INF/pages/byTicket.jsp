<%@ include file="header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section class="probootstrap-cover overflow-hidden relative"
         style="background-image: url('/resources/images/img_2.jpg');" data-stellar-background-ratio="0.5"
         id="section-home">
    <div class="overlay"></div>
    <div class="container">
        <div class="row align-items-center">
            <div class="col-md probootstrap-animate">
                <h2 class="heading mb-2 display-4 font-light probootstrap-animate">By ticket</h2>
                <div class="col-md probootstrap-animate">
                    <form:form method="POST" modelAttribute="ticket"
                               action="${pageContext.request.contextPath}/index" class="probootstrap-form">
                    <div class="form-group">
                        <h5 style="text-align: center"> Your route data: </h5>
                        <div class="row mb-3">
                            <div class="col-md">
                                <div class="form-group">
                                    <label for="station_from">From</label>
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
                        <!-- END row -->
                        <div class="row mb-3">
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="probootstrap-date-departure">Departure date</label>
                                    <div class="probootstrap-date-wrap">
                                        <span class="icon ion-calendar"></span>
                                        <input type="text" id="probootstrap-date-departure" class="form-control"
                                               placeholder="click and pick">
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="departure-time">Departure time</label>
                                    <div class="probootstrap-date-wrap">
                                        <span class="icon ion-calendar"></span>
                                        <input type="time" id="departure-time" value="now" class="form-control"
                                               placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="train">Train №</label>
                                    <div class="form-group">
                                        <input type="text" id="train" class="form-control">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                                     <h4 style="text-align: center"> Personal data: </h4>
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="name">First name</label>
                                <input type="text" class="form-control" id="name" name="name"
                                       placeholder="Name" required>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="surname">Surname</label>
                                <input type="text" class="form-control" id="surname" name="surname"
                                       placeholder="Surname" required>
                            </div>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label for="birth_date">Click and take it</label>
                                <input type="submit" value="Search" class="btn btn-primary btn-block">
                        </div>
                        <div class="col-md-3">
                            <div class="form-group">
                                <label for="birth_date">Birth date</label>
                                <input type="number" class="form-control" id="birth_date" name="surname"
                                       placeholder="format ddmmyyyy" required>
                            </div>
                        </div>
                    </div>
                </div>
                </form:form>
            </div>
        </div>
    </div>
    </div>
</section>

<%@ include file="footer.jsp" %>
