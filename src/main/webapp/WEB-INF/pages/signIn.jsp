<%@ include file="header.jsp" %>

<section class="probootstrap-cover overflow-hidden relative"
         style="background-image: url('/resources/images/sq_img_1.jpg');" data-stellar-background-ratio="0.5"
         id="section-home">
    <div class="overlay"></div>
    <div class="container">
        <div class="row align-items-center">
            <div class="col-md">
                <h2 class="heading mb-2 display-4 font-light probootstrap-animate">KudKuda </h2>
            </div>
            <div class="col-md-6  probootstrap-animate">
                <h5 class="heading mb-2 display-8 font-light probootstrap-animate"><br> <span
                        style="color:#e0e139;">${msg}</span></h5>
                <form action="#" method="post" class="probootstrap-form probootstrap-form-box mb60">
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="username" class="sr-only sr-only-focusable">Login</label>
                                <input type="text" class="form-control" id="username" name="username"
                                       placeholder="Login (e-mail)" required>
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
                    <div class="form-group">
                        <input type="submit" class="btn btn-primary" id="submit" name="submit" value="LogIn">
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>

<%@ include file="footer.jsp" %>
