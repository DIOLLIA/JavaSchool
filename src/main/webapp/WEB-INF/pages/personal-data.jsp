<%@ include file="header.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/tables.css">

<section class="probootstrap-cover overflow-hidden relative"
         style="background-image: url('/resources/images/img_5.jpg');" data-stellar-background-ratio="0.5"
         id="section-home">
    <div class="overlay"></div>
    <div class="container">
        <div class="row">
            <div class="col-md probootstrap-animate">
                <h2 class="heading mb-2 display-4 font-light probootstrap-animate">Profile</h2>
                <h2>${message}</h2>
                <table class="table_user_info"style="width: 100%">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <td><c:out value="${user.name}"/></td>
                    </tr>
                    <tr>
                        <th>Surname</th>
                        <td><c:out value="${user.surname}"/></td>
                    </tr>
                    <tr>
                        <th>Birth Date</th>
                        <td><c:out value="${user.birthDay}"/></td>
                    </tr>
                    <tr>
                        <th>e-mail</th>
                        <td><c:out value="${user.email}"/></td>
                    </tr>


                    <th><a href="${pageContext.request.contextPath}/user/changePassword/${user.id}">Change password</a>
                        <a href="${pageContext.request.contextPath}/user/edit/${user.id}">Edit personal data</a></th>
                    <td>

                        <a href="${pageContext.request.contextPath}/user/edit/${user.id}">Delete account</a>
                    </td>
                    </tr>
                    </thead>
                </table>
                <br>

                <br>
                <input type="submit" class="btn btn-primary" style="font-size: 150%" value="create user"
                       onclick="location='user/add';"/>
            </div>
            <div class="col-md">
                <h2 class="heading mb-2 display-4 font-light probootstrap-animate">Your tickets</h2>

            </div>
        </div>
    </div>

</section>

<%@ include file="footer.jsp" %>
