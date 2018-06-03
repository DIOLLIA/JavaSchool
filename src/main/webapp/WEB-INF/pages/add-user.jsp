<%@ include file="header-admin.jsp" %>

<section class="probootstrap-cover overflow-hidden relative"
         style="background-image: url('/resources/images/sq_img_1.jpg');" data-stellar-background-ratio="0.5"
         id="section-home">
    <div class="overlay"></div>
    <div class="container">
        <div class="row align-items-center">
            <div class="col-md-6  probootstrap-animate">
                <h3 class="heading mb-2 display-8 font-light probootstrap-animate"><br> <span
                        style="color:#e0e139;">${message}</span></h3>
                <form:form method="POST" modelAttribute="user" action="${pageContext.request.contextPath}/user/add"
                           class="probootstrap-form probootstrap-form-box mb60">
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="email" style="width: 100%;">Login (your E-mail)</label>
                                <input type="email" class="form-control" id="email" name="email"
                                       placeholder="E-mail only" required>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="password" style="width: 100%;">Password</label>
                                <input type="password" class="form-control" id="password" name="password"
                                       placeholder="Password" required>
                            </div>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="name" style="width: 100%;">First name</label>
                                <input type="text" class="form-control" id="name" name="name"
                                       placeholder="Name" required>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="surname" style="width: 100%;">Second name</label>
                                <input type="text" class="form-control" id="surname" name="surname"
                                       placeholder="Surname" required>
                            </div>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="name" style="width: 100%;">Birth day</label>
                                <input type="date" class="form-control" id="birthDay" name="birthDay"
                                       placeholder="Date of Birth" required>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="role" style="width: 100%;">Role</label>
                                <select id="role" name="role"
                                        required class="js-example-basic-single js-states form-control"
                                        style="width: 100%;">
                                    <option value="Admin">Admin</option>
                                    <option value="User">User</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <input type="submit" class="btn btn-primary" id="submit" name="submit" value="Create">
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</section>

<%@ include file="footer.jsp" %>
