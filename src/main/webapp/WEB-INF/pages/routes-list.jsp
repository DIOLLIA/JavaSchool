f<%@ include file="header-admin.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/tables.css">

<section class="probootstrap-cover overflow-hidden relative"
         style="background-image: url('/resources/images/img_5.jpg');" data-stellar-background-ratio="0.5"
         id="section-home">
    <div class="overlay"></div>
    <div class="container">
        <div class="row align-items-center">
            <div class="col-md probootstrap-animate">
                <h2 class="heading mb-2 display-4 font-light probootstrap-animate"><input class="btn btn-primary"
                                                                                          type="submit" value="<<"
                                                                                          onclick="location='/schedule/scheduleList';"/>
                    Routes &#8195; <input type="submit" class="btn btn-primary" value="Add route"
                                                  onclick="location='addRoute';"/>
                </h2>
                <h2 style="color: yellow">${message}</h2>
                <table class="table_price" border="0px" cellpadding="" cellspacing="0">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="routes" items="${routes}">
                        <tr>
                            <td>${routes.routeName}</td>

                            <td>
                                <a href="${pageContext.request.contextPath}/schedule/scheduleList/routeList/${routes.id}">Show</a>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</section>

<%@ include file="footer.jsp" %>


