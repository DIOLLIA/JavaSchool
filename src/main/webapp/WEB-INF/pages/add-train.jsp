<%@ include file="header-admin.jsp" %>

<section class="probootstrap-cover overflow-hidden relative"
         style="background-image: url('/resources/images/img_5.jpg');" data-stellar-background-ratio="0.5"
         id="section-home">
    <div class="overlay"></div>
    <div class="container">
        <div class="row align-items-center">
            <div class="col-md probootstrap-animate">
                <h2 class="heading mb-2 display-4 font-light probootstrap-animate"><input class="btn btn-primary"
                                                                                          type="submit" value="<<"
                                                                                          onclick="location='list';"/>
                    <spring:message code="page.title.add-train"/></h2>
                <form:form name='addTrain' method="POST" modelAttribute="train"
                           action="${pageContext.request.contextPath}/train/add" onsubmit="return train_validation()">
                    <table class="table_add" border="0px" cellpadding="" cellspacing="0">
                        <tbody>
                        <tr>
                            <td><spring:message code="train-number"/></td>
                            <td><form:input path="numberOfTrain"/></td>
                        <td><span id="trainNumberLoc" style="color: red"></span></td>
                        </tr>
                        <tr>
                            <td><spring:message code="train-seats"/></td>
                            <td><form:input path="seats"/></td>
                        <td><span id="seatsLoc" style="color: red"></span></td>

                        </tr>
                        </tbody>
                    </table>
                    <br>
                    <input type="submit" class="btn btn-primary" value="<spring:message code="add-train-button"/>"/>
                </form:form>
            </div>
        </div>
    </div>
</section>

<%@ include file="footer.jsp" %>
