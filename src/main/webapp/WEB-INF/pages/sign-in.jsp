<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="header.jsp" %>


<section class="probootstrap-cover overflow-hidden relative"
         style="background-image: url('/resources/images/img_4.jpg');" data-stellar-background-ratio="0.5"
         id="section-home">
    <div class="overlay"></div>
    <div class="container">
        <div class="row align-items-center">
            <div class="col-md">
                <h2 class="heading mb-2 display-4 font-light probootstrap-animate">KudKuda </h2>
                <h3 class="heading mb-2 display-4 font-light probootstrap-animate"><br> <span
                        style="color:#32CD32;">Authentication</span></h3>
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


                <form name='loginForm'
                      action="<c:url value='/signIn' />" method='POST'
                      class="probootstrap-form probootstrap-form-box mb60">
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="username" style="width: 100%;">Login (your E-mail)</label>
                                <input type="text" class="form-control" id="username" name="username"
                                       placeholder="Login (e-mail)" required>
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

                    <div class="row mb-6">
                        <div class="col-md-6">
                            <div class="form-group">
                                <input type="submit" class="btn btn-primary" id="submit" name="submit" value="LogIn">
                            </div>
                        </div>
                        <div class="col-md-6" style="margin: auto; padding: auto;">
                            <a href="/signUp">Click to create new account</a>
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
