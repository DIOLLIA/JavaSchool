<%@ include file="header.jsp" %>

<section class="probootstrap-cover overflow-hidden relative"
         style="background-image: url('/resources/images/bg_1.jpg');" data-stellar-background-ratio="0.5"
         id="section-home">
    <div class="overlay"></div>
    <div class="container">
        <div class="row align-items-center">
            <div class="col-md probootstrap-animate">
                <h2 class="heading mb-2 display-4 font-light probootstrap-animate">KudKuda</h2>
                <p class="lead mb-4 probootstrap-animate"><span style="color:#32CD32;">Search result </span>
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
                            <td><a href="${pageContext.request.contextPath}/train/edit/${train}.html">by ticket</a><br/>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="row">
                    <div class="col-md-4">
                        <p>&nbsp;</p>
                    </div>
                    <div class="col-md-4">
                        <p>&nbsp;</p>
                    </div>
                    <div class="col-md-3">
                        <br>
                        <a href="../index.html" value="New Search" class="btn btn-primary btn-block">New Search</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
</section>


<footer class="probootstrap_section probootstrap-border-top">
    <div class="container">
        <h3 class="probootstrap_font-18 mb-3">Links</h3>
        <div class="row mb-5">
            <div class="col-md-3">
                <h3 class="probootstrap_font-18 mb-3"></h3>
                <ul class="list-unstyled">
                    <li><a href="../index.html">Home</a></li>
                </ul>
            </div>

            <div class="col-md-3">
                <h3 class="probootstrap_font-18 mb-3"></h3>
                <ul class="list-unstyled">
                    <li><a href="../about.html">About</a></li>
                </ul>
            </div>
            <div class="col-md-3">
                <h3 class="probootstrap_font-18 mb-3"></h3>
                <ul class="list-unstyled">
                    <li><a href="https://free-template.co" target="_blank">??????????</a></li>
                </ul>
            </div>
            <div class="col-md-3">
                <h3 class="probootstrap_font-18 mb-3"></h3>
                <ul class="list-unstyled">
                    <li><a href="https://free-template.co" target="_blank">??????????</a></li>
                </ul>
            </div>
        </div>
        <div class="row pt-1">
            <div class="col-md-12 text-center">
                <p class="probootstrap_font-22">Show sponsor <a href="https://t-systems.com/"
                                                                target="_blank"><span
                        style="color:#E20074;">T-Systems</span></a></p>
            </div>
        </div>
    </div>
</footer>
</body>
</html>