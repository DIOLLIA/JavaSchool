<%--
  Created by IntelliJ IDEA.
  User: KotoSovik
  Date: 15.05.2018
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>search trains on station</title>
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
    <link rel="stylesheet" href="../../resources/css/bootstrap-datepicker.css">   <!--ВАЖНО это стиль календаря  -->
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
        <a class="navbar-brand" href="index.html">KudKuda</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#probootstrap-menu"
                aria-controls="probootstrap-menu" aria-expanded="false" aria-label="Toggle navigation">
            <span><i class="ion-navicon"></i></span>
        </button>
        <div class="collapse navbar-collapse" id="probootstrap-menu">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active"><a class="nav-link" href="index.html">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="city-guides.html">Contacts</a></li>
                <li class="nav-item"><a class="nav-link" href="travel.html">Sign in</a></li>
                <li class="nav-item"><a class="nav-link" href="contact.html">Log in</a></li>
            </ul>
        </div>
    </div>
</nav>
<!-- END nav -->  <!--  -->


<section class="probootstrap-cover overflow-hidden relative"
         style="background-image: url('/resources/images/bg_1.jpg');" data-stellar-background-ratio="0.5"
         id="section-home">
    <div class="overlay"></div>
    <div class="container">
        <div class="row align-items-center">
            <div class="col-md">
                <h2 class="heading mb-2 display-4 font-light probootstrap-animate">KudKuda</h2>


                <p class="lead mb-4 probootstrap-animate">If you want to see the train schedule for the station, please
                    select the <span style="color:#32CD32;">station </span>
                    <label for="station_from" style="width: 60%;">
                        <select class="js-example-basic-single js-states form-control"
                                id="station_from" style="width: 50%;">
                        </select>
                    </label>
                    <br>
                    and press:


                <p class="probootstrap-animate">
                    <a href="onepage.html" role="button"
                       class="btn btn-primary p-3 mr-5 pl-3 pr-3 text-uppercase d-lg-inline d-md-inline d-sm-block d-block mb-3">station
                        schedule search</a>
                </p>

            </div>
        </div>

</section>
<!-- END section -->


<footer class="probootstrap_section probootstrap-border-top">
    <div class="container">
        <div class="row mb-5">
            <div class="col-md-3">
                <h3 class="probootstrap_font-18 mb-3">Quick Links</h3>
                <ul class="list-unstyled">
                    <li><a href="https://free-template.co" target="_blank">Home</a></li>
                    <li><a href="https://free-template.co" target="_blank">About</a></li>
                    <li><a href="https://free-template.co" target="_blank">Services</a></li>
                    <li><a href="https://free-template.co" target="_blank">Contact</a></li>
                </ul>
            </div>

        </div>
        <div class="row pt-5">
            <div class="col-md-12 text-center">
                <p class="probootstrap_font-14">&copy; 2017. All Rights Reserved. <br> Designed &amp; Developed by <a
                        href="https://uicookies.com/" target="_blank">uiCookies</a>
                    <small> (Don't remove credit link on this footer. See <a href="https://uicookies.com/license/">license</a>)
                    </small>
                </p>
                <p class="probootstrap_font-14">Demo Images: <a href="https://unsplash.com/"
                                                                target="_blank">Unsplash</a></p>
            </div>
        </div>
    </div>
</footer>
</body>
</html>
