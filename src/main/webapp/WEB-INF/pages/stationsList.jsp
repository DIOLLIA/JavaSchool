<%@ include file="header.jsp" %>
<link rel="stylesheet" href="../../resources/css/tables.css">

<section class="probootstrap-cover overflow-hidden relative"
         style="background-image: url('/resources/images/img_5.jpg');" data-stellar-background-ratio="0.5"
         id="section-home">
    <div class="overlay"></div>
    <div class="container">
        <div class="row align-items-center">
            <div class="col-md probootstrap-animate">
                <h2 class="heading mb-2 display-4 font-light probootstrap-animate">Stations</h2>
                <table class="table_price" border="0px" cellpadding="" cellspacing="0">
                    <thead>
                    <tr>
                        <th width="70%">Name</th>
                        <th width="50%">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="station" items="${stations}">
                        <tr>
                            <td>${station.stationName}</td>
                            <td><a href="${pageContext.request.contextPath}/station/edit/${station.id}.html">Edit</a>
                                <a href="${pageContext.request.contextPath}/station/delete/${station.id}.html">Delete</a><br/>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <br>
                <h2>${message}</h2>
                <br>
                <input type="submit" class="btn btn-primary" value="add station"
                       onclick="location='add';"/>
            </div>
        </div>
    </div>

</section>

<%@ include file="footer.jsp" %>
