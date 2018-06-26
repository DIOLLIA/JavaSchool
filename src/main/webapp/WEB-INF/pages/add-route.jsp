<%@ include file="header-admin.jsp" %>

<section class="probootstrap-cover overflow-hidden relative"
         style="background-image: url('/resources/images/img_5.jpg');" data-stellar-background-ratio="0.5"
         id="section-home">
    <div class="overlay"></div>
    <div class="container">
        <div class="row align-items-center">
            <div class="col-md probootstrap-animate">
                <h2 class="heading mb-2 display-4 font-light probootstrap-animate"> <input class="btn btn-primary"
                                                                                           type="submit" value="<<"
                                                                                           onclick="location='./';"/> Add route</h2>
                <h2 class="display-8 probootstrap-section-heading" style="color: yellow">${message}</h2>
                <p class="lead mb-4 probootstrap-animate">New <span style="color:#32CD32;">route name </span> will be
                    concatinated from two station names: </p>
                <form:form method="POST" modelAttribute="route"
                           action="${pageContext.request.contextPath}/schedule/scheduleList/addRoute">
                    <table class="table_add">
                        <tbody>
                        <tr>
                            <td><select class="js-example-basic-single js-states form-control"
                                        id="station_from" style="width: 200px;" name="stationFrom"
                                        onchange="getToStations()">
                            </select></td>
                            <td><h1 style="color: white"> - </h1></td>
                            <td><select class="js-example-basic-single js-states form-control"
                                        id="station_to" style="width: 200px;" name="stationTo">
                            </select></td>
                        </tr>
                        </tbody>
                    </table>
                    <br>
                    <input type="submit" class="btn btn-primary" value="Add route"
                           onclick="location='';"/>
                </form:form>
            </div>
        </div>
    </div>
</section>

<%@ include file="footer.jsp" %>
