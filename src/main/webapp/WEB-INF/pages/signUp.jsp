<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Sign up page</title>
    <meta name="description" content="find tickets ob KudKuda.rus">
    <meta name="keywords" content="find tickets, find trains, schedule of trains, stations list">


    <!--   <link href="https://fonts.googleapis.com/css?family=Work+Sans:300,400,700" rel="stylesheet"> -->

    <link rel="stylesheet" href="../../resources/css/bootstrap/bootstrap.css">
    <link rel="stylesheet" href="../../resources/css/animate.css">
    <!-- <link rel="stylesheet" href="../../resources/fonts/ionicons/css/ionicons.min.css">  -->

    <!--   <link rel="stylesheet" href="../../resources/css/owl.carousel.min.css"> -->
    <!-- карусель слайдов http://mel0ne.ru/2016/09/16/owl-carousel-2/ -->

    <!--   <link rel="stylesheet" href="../../resources/fonts/flaticon/font/flaticon.css"> --><!--  иконки для сайта-->

    <!--  <link rel="stylesheet" href="../../resources/fonts/fontawesome/css/font-awesome.min.css">   -->
    <!--  иконки- увеличение, анимированные икоки-->
    <link rel="stylesheet" href="../../resources/css/select2.css">
    <!-- ВАЖНО стили для from to полей   и содержащуюся в них изначально информацию-->


    <!--  -->
    <link rel="stylesheet" href="../../resources/css/helpers.css">
    <!-- ВАЖНО при выключении мешает фоны на главной странице и не создаёт эфект градиента-->
    <link rel="stylesheet" href="../../resources/css/style.css">
    <!-- ВАЖНО отвечает за стиль страницы в целом и многом-->

</head>
<body>

<script src="../../resources/js/jquery.min.js"></script>

<script src="../../resources/js/popper.min.js"></script>
<script src="../../resources/js/bootstrap.min.js"></script>
<script src="../../resources/js/owl.carousel.min.js"></script>

<script src="../../resources/js/bootstrap-datepicker.js"></script>
<script src="../../resources/js/jquery.waypoints.min.js"></script>
<script src="../../resources/js/jquery.easing.1.3.js"></script>

<script src="../../resources/js/select2.min.js"></script>
<script src="../../resources/js/main.js"></script>

<nav class="navbar navbar-expand-lg navbar-dark probootstrap_navbar" id="probootstrap-navbar">
    <div class="container">
        <a class="navbar-brand" href="../../index.html">KudKuda Home</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#probootstrap-menu"
                aria-controls="probootstrap-menu" aria-expanded="false" aria-label="Toggle navigation">
            <span><i class="ion-navicon"></i></span>
        </button>
        <div class="collapse navbar-collapse" id="probootstrap-menu">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active"><a class="nav-link" href="http://localhost:8080/train/list">Trains list</a>
                </li>
                <li class="nav-item"><a class="nav-link" href="http://localhost:8080/station/list">Station list</a></li>
                <li class="nav-item"><a class="nav-link" href="http://localhost:8080/user/signIn">Sign in</a></li>
                <li class="nav-item"><a class="nav-link" href="http://localhost:8080/user/signUp">Sign up</a></li>
            </ul>
        </div>
    </div>
</nav>
<!-- END nav -->  <!--  -->


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

