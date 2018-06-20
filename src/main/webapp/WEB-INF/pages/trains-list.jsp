<%@ include file="header-admin.jsp" %>
<link rel="stylesheet" href="../../resources/css/tables.css">

<section class="probootstrap-cover overflow-hidden relative"
         style="background-image: url('/resources/images/bg_1.jpg');" data-stellar-background-ratio="0.5"
         id="section-home">
    <div class="overlay"></div>
    <div class="container">
        <div class="row align-items-center">
            <div class="col-md probootstrap-animate">
                <h2 class="heading mb-2 display-4 font-light probootstrap-animate">  <input class="btn btn-primary"
                                                                                            type="submit" value="<<"
                                                                                            onclick="location='/index';"/>
                    <spring:message code="admin.header.trains"/> &#8195;
                    <input type="submit" class="btn btn-primary"
                           value="<spring:message code="add-train-button"/>"
                           onclick="location='add';"/>
                </h2>
                <h2 class="display-8 probootstrap-section-heading" style="color: yellow">${message}</h2>
                <table class="table_price" border="0px" cellpadding="0" cellspacing="0" width="60%">
                    <thead>
                    <tr>
                        <th width="10%">Number</th>
                        <th width="10%">Seats</th>
                        <th width="10%">Schedule</th>
                        <th width="20%">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="train" items="${trains}">
                        <tr>
                            <td>${train.numberOfTrain}</td>
                            <td>${train.seats}</td>
                            <td><a href="${pageContext.request.contextPath}/train/schedule/${train.id}">Show</a><br/>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/train/edit/${train.id}">Edit</a>
                                <a href="${pageContext.request.contextPath}/train/delete/${train.id}">Delete</a><br/>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</section>

<%@ include file="footer.jsp" %>
