<%@ include file="header-admin.jsp" %>

<section class="probootstrap-cover overflow-hidden relative"
         style="background-image: url('/resources/images/img_5.jpg');" data-stellar-background-ratio="0.5"
         id="section-home">
    <div class="overlay"></div>
    <div class="container">
        <div class="row align-items-center">
            <div class="col-md probootstrap-animate">
                <h2 class="heading mb-2 display-4 font-light probootstrap-animate">Add Train</h2>
                <form:form method="POST" modelAttribute="train" action="${pageContext.request.contextPath}/train/add">
                    <table class="table_add" border="0px" cellpadding="" cellspacing="0">
                        <tbody>
                        <tr>
                            <td>Number:</td>
                            <td><form:input path="numberOfTrain"/></td>
                        </tr>
                        <tr>
                            <td>Seats:</td>
                            <td><form:input path="seats"/></td>
                        </tr>
                        </tbody>
                    </table>
                    <br>
                    <input type="submit" class="btn btn-primary" value="add train"
                           onclick="location='list';"/>
                </form:form>
            </div>
        </div>
    </div>
</section>

<%@ include file="footer.jsp" %>
