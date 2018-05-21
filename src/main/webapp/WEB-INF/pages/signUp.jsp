<%@ include file="header.jsp" %>

<section class="probootstrap-cover overflow-hidden relative"
         style="background-image: url('/resources/images/sq_img_1.jpg');" data-stellar-background-ratio="0.5"
         id="section-home">
    <div class="overlay"></div>
    <div class="container">
        <div class="row align-items-center">
            <div class="col-md">
                <h2 class="heading mb-2 display-4 font-light probootstrap-animate">KudKuda <br> Registration</h2>
            </div>
            <div class="col-md-6  probootstrap-animate">
                <h5 class="heading mb-2 display-8 font-light probootstrap-animate"><br> <span
                        style="color:#e0e139;">${msg}</span></h5>
                <form action="#" method="POST" modelAttribute="user" class="probootstrap-form probootstrap-form-box mb60">
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <div class="form-group">
                                <%-- <input type="text" id="usrname" name="usrname" required>--%>
                                <label for="email" class="sr-only sr-only-focusable">Login</label>
                                <input type="email" class="form-control" id="email" name="email"
                                       placeholder="Your email" required>
                            </div>
                        </div>


                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="password" class="sr-only sr-only-focusable">Password</label>
                                <input type="password" class="form-control" id="password" name="password"
                                       placeholder="Password" required>
                            </div>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="name" class="sr-only sr-only-focusable">First name</label>
                                <input type="text" class="form-control" id="name" name="name"
                                       placeholder="Name" required>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="surname" class="sr-only sr-only-focusable">Surname</label>
                                <input type="text" class="form-control" id="surname" name="surname"
                                       placeholder="Surname" required>
                            </div>
                        </div>
                    </div>
                    <div class="row mb-3">
                       <%-- <div class="col-md-6">
                            <div class="form-group">
                                <label for="email" class="sr-only sr-only-focusable">E-mail</label>
                                <input type="email" class="form-control" id="email" name="email"
                                       placeholder="email" required>
                            </div>
                        </div>--%>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="birthDay" class="sr-only sr-only-focusable">Birth Day</label>
                                <input type="date" class="form-control" id="birthDay" name="birthDay"
                                       placeholder="Date of Birth
                                    " required>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <input type="submit" class="btn btn-primary" id="submit" name="submit" value="Sign Up">
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>

<%@ include file="footer.jsp" %>
