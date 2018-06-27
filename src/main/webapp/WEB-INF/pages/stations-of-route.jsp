<%@ include file="header-admin.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/tables.css">

<section class="probootstrap-cover overflow-hidden relative"
         style="background-image: url('/resources/images/img_5.jpg');" data-stellar-background-ratio="0.5"
         id="section-home">
    <div class="overlay"></div>
    <div class="container">
        <div class="row align-items-center">
            <div class="col-md probootstrap-animate">


                <h2 class="heading mb-2 display-4 font-light probootstrap-animate">  <input class="btn btn-primary"
                                                                                            type="submit" value="<<"

                                                                                            onclick="location='./';"/> Stations of&#8195;
                    <form:form method="POST" modelAttribute="route"
                               action="${pageContext.request.contextPath}">
                    <input type="submit"
                           class="btn btn-primary"
                           value="+"
                           onclick="location='add';"/>
                    <select class="js-example-basic-single js-states form-control"
                            id="station_from" name="station"
                            style="width: 20%;">
                    </select>
                        ${routeName}
                </h2>
                </form:form>


                <h2>${message}</h2>
                <table class="table_price" style="width: 80%;">
                    <thead>
                    <tr>
                        <th width="30%">Name</th>
                        <th width="10%">Latitude</th>
                        <th width="10%">Longitude</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="station" items="${stations}">
                        <tr>
                            <td>${station.stationName}</td>
                            <td>${station.latitude}</td>
                            <td>${station.longitude}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</section>

<%@ include file="footer.jsp" %>


