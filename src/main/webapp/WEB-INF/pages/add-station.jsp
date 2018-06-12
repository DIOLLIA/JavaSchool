<%@ include file="header-admin.jsp" %>

<section class="probootstrap-cover overflow-hidden relative"
         style="background-image: url('/resources/images/img_5.jpg');" data-stellar-background-ratio="0.5"
         id="section-home">
    <div class="overlay"></div>
    <div class="container">
        <div class="row align-items-center">
            <div class="col-md probootstrap-animate">
                <h2 class="heading mb-2 display-4 font-light probootstrap-animate">
                    <spring:message code="page.title.add-station"/>
                </h2>
                <form:form method="POST" modelAttribute="station"
                           action="${pageContext.request.contextPath}/station/add">
                    <table class="table_add" border="0px" cellpadding="" cellspacing="0">
                        <tbody>
                        <tr>
                            <td><spring:message code="station-name"/> </td>
                            <td><form:input path="stationName"/></td>
                        </tr>
                        <tr>
                            <td><spring:message code="station-latitude"/> </td>
                            <td><form:input path="latitude"/></td>
                        </tr>
                        <tr>
                            <td><spring:message code="station-longitude"/></td>
                            <td><form:input path="longitude"/></td>
                        </tr>
                        </tbody>
                    </table>
                    <br>
                    <input type="submit" class="btn btn-primary"
                           value="<spring:message code="add-station-button"/>"
                           onclick="location='list';"/>
                </form:form>
            </div>
        </div>
    </div>
</section>

<%@ include file="footer.jsp" %>
