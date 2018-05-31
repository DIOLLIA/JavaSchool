<%@ include file="header.jsp" %>

<section class="probootstrap-cover overflow-hidden relative"
         style="background-image: url('/resources/images/bg_1.jpg');" data-stellar-background-ratio="0.5"
         id="section-home">
    <div class="overlay"></div>
    <div class="container">
        <div class="row align-items-center">
            <div class="col-md probootstrap-animate">
                <div>
                    <h2 class="display-8 probootstrap-section-heading">${message}</h2>
                </div>
                <h2 class="heading mb-2 display-4 font-light probootstrap-animate">Trains</h2>
                <table class="table_price" border="0px" cellpadding="0" cellspacing="0" width="50%">
                    <thead>
                    <tr>
                        <th width="10%">Number</th>
                        <th width="20%">Seats</th>
                        <th width="20%">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="train" items="${trains}">
                        <tr>
                            <td>${train.numberOfTrain}</td>
                            <td>${train.seats}</td>
                            <td><a href="${pageContext.request.contextPath}/train/edit/${train.id}">Edit</a>
                                <a href="${pageContext.request.contextPath}/train/delete/${train.id}">Delete</a><br/>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <br>
                <input type="submit" class="btn btn-primary" value="add train"
                       onclick="location='add';"/>
            </div>
        </div>
    </div>

</section>
<!-- END section -->


<footer class="probootstrap_section probootstrap-border-top">
    <div class="container">
        <h3 class="probootstrap_font-18 mb-3">Links</h3>
        <div class="row mb-5">
            <div class="col-md-3">
                <h3 class="probootstrap_font-18 mb-3"></h3>
                <ul class="list-unstyled">
                    <li><a href="../index.html">Home</a></li>
                </ul>
            </div>

            <div class="col-md-3">
                <h3 class="probootstrap_font-18 mb-3"></h3>
                <ul class="list-unstyled">
                    <li><a href="../about.html">About</a></li>
                </ul>
            </div>
            <div class="col-md-3">
                <h3 class="probootstrap_font-18 mb-3"></h3>
                <ul class="list-unstyled">
                    <li><a href="https://free-template.co" target="_blank">??????????</a></li>
                </ul>
            </div>
            <div class="col-md-3">
                <h3 class="probootstrap_font-18 mb-3"></h3>
                <ul class="list-unstyled">
                    <li><a href="https://free-template.co" target="_blank">??????????</a></li>
                </ul>
            </div>
        </div>
        <div class="row pt-1">
            <div class="col-md-12 text-center">
                <p class="probootstrap_font-22">Show sponsor <a href="https://t-systems.com/"
                                                                target="_blank"><span
                        style="color:#E20074;">T-Systems</span></a></p>
            </div>
        </div>
    </div>
</footer>
</body>
</html>