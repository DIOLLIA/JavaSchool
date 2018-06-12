<%@ include file="header-admin.jsp" %>
<%@ page pageEncoding="UTF-8" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/tables.css">

<section class="probootstrap-cover overflow-hidden relative"
         style="background-image: url('/resources/images/img_4.jpg');" data-stellar-background-ratio="0.5"
         id="section-home">
    <div class="overlay"></div>
    <div class="container">
        <div class="row align-items-center">
            <div class="col-md probootstrap-animate">
                <h2 class="heading mb-2 display-4 font-light probootstrap-animate">KudKuda</h2>
                <p class="lead mb-4 probootstrap-animate"><span
                        style="color:#32CD32; font-size: 120%">Passengers </span>list are on the train
                </p>
                <br>
                <div class="row">
                    <div class="col-auto">
                        <input type="submit" value="Back to train routes" class="btn btn-primary btn-block"
                               onclick="location='../';"/>
                    </div>
                </div>
                <br>
                <h3 class="heading mb-2 display-8 font-light probootstrap-animate"><br> <span
                        style="color:#e0e139;">${msg}</span></h3>

                <table class="table_price" border="0px" cellpadding="0" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th width="5%">Surname</th>
                        <th width="15%">Name</th>
                        <th width="10%">Birth Day</th>
                        <th width="5%">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="passengersOnRoute" items="${passengersOnRoute}">
                        <tr>
                            <td>${passengersOnRoute.getSurname()}</td>
                            <td>${passengersOnRoute.getName()}</td>
                            <td>${passengersOnRoute.getBirthDay()}</td>
                            <td><a href="${pageContext.request.contextPath}/train/edit/${train}.html">Nothing</a><br/>
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
