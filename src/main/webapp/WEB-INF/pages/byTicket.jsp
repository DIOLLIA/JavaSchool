<%@ include file="header.jsp" %>

<section class="probootstrap-cover overflow-hidden relative"
         style="background-image: url('/resources/images/img_2.jpg');" data-stellar-background-ratio="0.5"
         id="section-home">
    <div class="overlay"></div>
    <div class="container">
        <div class="row align-items-center">
            <div class="col-md probootstrap-animate">
                <h2 class="heading mb-2 display-4 font-light probootstrap-animate">By ticket</h2>
                <form:form method="POST" modelAttribute="ticket"
                           action="${pageContext.request.contextPath}/ticket/by">
                    <%--<form:hidden path="id"/>--%>
                    <%--<form:hidden path="numberOfTrain"/>--%>
                    <table class="table_add" border="0px" cellpadding="" cellspacing="0">
                        <tbody>
                            <%-- <tr>
                                 <td>Train №</td>
                                 <td>${train.numberOfTrain}</td>
                             </tr>--%>
                        <tr>
                            <td>First name</td>
                            <td><form:input path="name"/></td>
                        </tr>
                        <tr>
                            <td>Last name</td>
                            <td><form:input path="surname"/></td>
                        </tr>
                        <tr>
                            <td>Birth day</td>
                            <td><form:input path="birthDate" placeholder="format yyyy-mm-dd"/></td>
                        </tr>
                        </tbody>
                    </table>
                    <br>
                    <input type="submit" class="btn btn-primary" value="Take if FREE"
                           onclick="location='list';"/>
                </form:form>
            </div>
        </div>
    </div>
</section>

<%@ include file="footer.jsp" %>
