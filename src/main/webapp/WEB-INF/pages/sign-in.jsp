<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="header.jsp" %>


<section class="probootstrap-cover overflow-hidden relative"
         style="background-image: url('/resources/images/img_4.jpg');" data-stellar-background-ratio="0.5"
         id="section-home">
    <div class="overlay"></div>
    <div class="container">
        <div class="row align-items-center">
            <div class="col-md">
                <h3 class="heading mb-2 display-4 font-light probootstrap-animate"><br> <span
                        style="color:#32CD32;"><spring:message code="header.authentication"/></span></h3>
            </div>
            <div class="col-md-6  probootstrap-animate">


                <c:if test="${not empty error}">
                    <h5 class="heading mb-2 display-8 font-light probootstrap-animate"><br> <span
                            style="color: #E20074;">${error}</span></h5>
                </c:if>

                <c:if test="${not empty msg}">
                    <h5 class="heading mb-2 display-8 font-light probootstrap-animate"><br> <span
                            style="color:#e0e139;">${msg}</span></h5>
                </c:if>


                <form name='signInForm' onsubmit="return login_validation()"
                      action="<c:url value='/signIn' />" method='POST'
                      class="probootstrap-form probootstrap-form-box mb60">
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <div class="form-group">
                                <input type="text" class="form-control" id="username" name="username"
                                       placeholder="<spring:message code="label.login"/>">
                                <span id="nameloc" style="color: red"></span>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <input type="password" class="form-control" id="password" name="password"
                                       placeholder="<spring:message code="label.password"/>">
                                <span id="passwordloc" style="color: red"></span>
                            </div>
                        </div>
                    </div>

                    <div class="row mb-6">
                        <div class="col-md-6">
                            <div class="form-group">
                                <input type="submit" class="btn btn-primary" id="submit" name="submit"
                                       value="<spring:message code="btn.log-in"/>">
                            </div>
                        </div>
                        <div class="col-md-6" style="margin: auto; padding: auto;"
                             onmouseover="this.style.backgroundColor='#00CA4C';"
                             onmouseout="this.style.backgroundColor='#fff';">
                            <a href="/signUp"><spring:message code="register-new-user"/></a>
                        </div>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                </form>
            </div>
        </div>
    </div>
</section>

<%@ include file="footer.jsp" %>
