<%@ include file="header-admin.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/tables.css">


<section class="probootstrap-cover overflow-hidden relative"
         style="background-image: url('/resources/images/sq_img_2.jpg');" data-stellar-background-ratio="0.5"
         id="section-home">
    <div class="overlay"></div>
    <div class="container">
        <div class="row align-items-center">
            <div class="col-md probootstrap-animate">

                <h5 class="heading mb-2 display-8 font-light probootstrap-animate"><br> <span
                        style="color:#32CD32;">Find user by e-mail or Surname</span></h5>
                <form:form method="POST" modelAttribute="userSearch"
                           action="${pageContext.request.contextPath}/user/findUser">

                    <div class="row mb-3">
                        <div class="col-md-3">
                            <div class="form-group">
                                <input type="text" class="form-control" id="loginOrSurname" name="loginOrSurname"
                                       placeholder="Login (e-mail)" required style="background-color: white">
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-group">
                                <input type="submit" value="Search" class="btn btn-primary btn-block"
                                       style="width: 50%;">
                            </div>
                        </div>
                        <div class="col-md-3">
                            <input type="submit" class="btn btn-primary" value="Add new user"
                                   onclick="location='add';"/>
                        </div>
                    </div>
                </form:form>
                <h4 class="heading mb-2 display-8 font-light probootstrap-animate"><span
                        style="color:#e0e139;">Users list</span></h4>
                <table class="table_price" border="0px" cellpadding="0" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th width="10%">Name</th>
                        <th width="10%">Surname</th>
                        <th width="7%">Birth date</th>
                        <th width="10%">E-mail (login)</th>
                        <th width="5%">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="user" items="${users}">
                        <tr>
                            <td>${user.name}</td>
                            <td>${user.surname}</td>
                            <td>${user.birthDay}</td>
                            <td>${user.email}</td>
                            <td><a href="${pageContext.request.contextPath}/user/edit/${user.id}.html">Block</a><br/>
                            </td>
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