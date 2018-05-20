<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title><c:out value="${pageTitle}"></c:out></title>
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
        <a class="navbar-brand" href="/index">KudKuda Home</a>
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