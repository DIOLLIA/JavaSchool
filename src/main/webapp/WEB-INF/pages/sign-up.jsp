<%@ include file="header.jsp" %>

<section class="probootstrap-cover overflow-hidden relative"
         style="background-image: url('/resources/images/sq_img_1.jpg');" data-stellar-background-ratio="0.5"
         id="section-home">
    <div class="overlay"></div>
    <div class="container">
        <div class="row align-items-center">
            <div class="col-md-4">
                <h2 class="heading mb-2 display-4 font-light probootstrap-animate">KudKuda</h2>
                <h3 class="heading mb-2 display-4 font-light probootstrap-animate"><br><span
                        style="color:#32CD32;">Registration</span></h3>
            </div>
            <div class="col-md-8  probootstrap-animate">
                <h5 class="heading mb-2 display-8 font-light probootstrap-animate"><br> <span
                        style="color:#e0e139;">${msg}</span></h5>
                <form:form name='signUpForm' action="${pageContext.request.contextPath}/signUp" method="POST"
                           modelAttribute="user"
                           class="probootstrap-form probootstrap-form-box mb60" onsubmit="return registration_validation()">
                    <div class="row mb-3">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="email" style="width: 100%;font-size: 120%"><spring:message code="label.login"/></label>
                                <input type="text" class="form-control" id="email" name="email"
                                       placeholder="Your email">
                                <span id="emailloc" style="color: red"></span>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="password" style="width: 100%;font-size: 120%">Password</label>
                                <input type="password" class="form-control" id="password" name="password"
                                       placeholder="Password">
                                <span id="passwordloc" style="color: red"></span>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="passwordConf" style="width: 100%;font-size: 120%">Confirm password</label>
                                <input type="password" class="form-control" id="passwordConf" name="passwordConf"
                                       placeholder="Confirm password">
                                <span id="passwordConfloc" style="color: red"></span>
                            </div>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="name" style="width: 100%;font-size: 120%">First name</label>
                                <input type="text" class="form-control" id="name" name="name"
                                       placeholder="Name">
                                <span id="nameloc" style="color: red"></span>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="surname" style="width: 100%;font-size: 120%">Surname</label>
                                <input type="text" class="form-control" id="surname" name="surname"
                                       placeholder="Surname">
                                <span id="surnameloc" style="color: red"></span>
                            </div>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="birthDay" style="width: 100%;font-size: 120%">Birth day</label>
                                <input type="date" class="form-control" id="birthDay" name="birthDay"
                                       placeholder="Date of Birth">
                                <span id="birthdayloc" style="color: red"></span>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="submit" style="width: 100%;font-size: 120%"> &nbsp;</label>
                                <input type="submit" class="btn btn-primary" id="submit" name="submit" value="Sign Up">
                            </div>
                        </div>
                    </div>
                    <input type="hidden"
                           name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                </form:form>
            </div>
        </div>
    </div>
</section>

<%@ include file="footer.jsp" %>
