<%@ include file="header-admin.jsp" %>

<section class="probootstrap-cover overflow-hidden relative"
         style="background-image: url('/resources/images/sq_img_1.jpg');" data-stellar-background-ratio="0.5"
         id="section-home">
    <div class="overlay"></div>
    <div class="container">
        <div class="row align-items-center">
            <div class="col-md-9  probootstrap-animate">
                <h3 class="heading mb-2 display-8 font-light probootstrap-animate"><br> <span
                        style="color:#e0e139;">${message}</span></h3>
                <form:form name='signUpForm' onsubmit="return registration_validation()" method="POST"
                           modelAttribute="user"
                           action="${pageContext.request.contextPath}/user/add"
                           class="probootstrap-form probootstrap-form-box mb60">
                    <div class="row mb-3">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="email" style="width: 100%;font-size: 120%"><spring:message
                                        code="user-login"/> </label>
                                <input type="text" class="form-control" id="email" name="email"
                                       placeholder="Your email">
                                <span id="emailloc" style="color: red"></span>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="password" style="width: 100%;font-size: 120%"><spring:message
                                        code="user-password"/> </label>
                                <input type="password" class="form-control" id="password" name="password"
                                       placeholder="Password">
                                <span id="passwordloc" style="color: red"></span>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="passwordConf" style="width: 100%;font-size: 120%"><spring:message
                                        code="user-confirm-pswd"/> </label>
                                <input type="password" class="form-control" id="passwordConf" name="passwordConf"
                                       placeholder="Confirm password">
                                <span id="passwordConfloc" style="color: red"></span>
                            </div>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="name" style="width: 100%;font-size: 120%">
                                    <spring:message code="common.user-first-name"/>
                                </label>
                                <input type="text" class="form-control" id="name" name="name"
                                       placeholder="Name">
                                <span id="nameloc" style="color: red"></span>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="surname" style="width: 100%;font-size: 120%">
                                    <spring:message code="common.user-surname"/>
                                </label>
                                <input type="text" class="form-control" id="surname" name="surname"
                                       placeholder="Surname">
                                <span id="surnameloc" style="color: red"></span>
                            </div>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-3">
                            <div class="form-group">
                                <label for="birthDay" style="width: 100%;font-size: 120%">
                                    <spring:message code="common.user-birthday"/>
                                </label>
                                <input type="date" class="form-control" id="birthDay" name="birthDay"
                                       placeholder="Date of Birth">
                                <span id="birthdayloc" style="color: red"></span>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-group">
                                <label for="role" style="width: 100%;font-size: 120% "><spring:message
                                        code="user-role"/> </label>
                                <select id="role" name="role"
                                        required class="js-example-basic-single js-states form-control"
                                        style="width: 100%;">
                                    <option value="User"><spring:message code="user-role.user"/></option>
                                    <option value="Admin"><spring:message code="user-role.admin"/></option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="birthDay" style="width: 100%;font-size: 120%"> &nbsp;</label>
                                <input type="submit" class="btn btn-primary" id="submit" name="submit" value="Create">
                            </div>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</section>

<%@ include file="footer.jsp" %>
