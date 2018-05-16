<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Trains list page</title>
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
    <link rel="stylesheet" href="../../resources/css/style.css">
    <!-- ВАЖНО отвечает за стиль страницы в целом и многом-->
    <link rel="stylesheet" href="../../resources/css/tables.css">
</head>
<body>

<script src="../../resources/js/jquery.min.js"></script>

<script src="../../resources/js/popper.min.js"></script>
<script src="../../resources/js/bootstrap.min.js"></script>
<script src="../../resources/js/owl.carousel.min.js"></script>

<%--<script src="../../resources/js/bootstrap-datepicker.js"></script>--%>
<script src="../../resources/js/jquery.waypoints.min.js"></script>
<script src="../../resources/js/jquery.easing.1.3.js"></script>

<script src="../../resources/js/select2.min.js"></script>
<script src="../../resources/js/main.js"></script>

<nav class="navbar navbar-expand-lg navbar-dark probootstrap_navbar" id="probootstrap-navbar">
    <div class="container">
        <a class="navbar-brand" href="../index.html"> KudKuda Home </a>
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
<!-- END nav -->


<section class="probootstrap-cover overflow-hidden relative"
         style="background-image: url('/resources/images/bg_1.jpg');" data-stellar-background-ratio="0.5"
         id="section-home">
    <div class="overlay"></div>
    <div class="container">
        <div class="row align-items-center">
            <div class="col-md probootstrap-animate">
                    <div>
                        <h2 class="display-8 probootstrap-section-heading">${message}</h2>
                    </div>
                <h2 class="heading mb-2 display-4 font-light probootstrap-animate">Trains</h2>
                <table class="table_price" border="0px" cellpadding="0" cellspacing="0" width="50%">
                    <thead>
                    <tr>
                       <%-- <th width="5%">Number</th>
                        <th width="7%">Seats</th>
                        <th width="7%">Actions</th>--%>
                           <th width="10%">Number</th>
                           <th width="20%">Seats</th>
                           <th width="20%">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="train" items="${trains}">
                        <tr>
                            <td>${train.numberOfTrain}</td>
                            <td>${train.seats}</td>
                            <td><a href="${pageContext.request.contextPath}/train/edit/${train.id}.html">Edit</a>
                                <a href="${pageContext.request.contextPath}/train/delete/${train.id}.html">Delete</a><br/>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <br>
                <input  type="submit" class="btn btn-primary" value="add train"
                       onclick="location='add';"/>
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