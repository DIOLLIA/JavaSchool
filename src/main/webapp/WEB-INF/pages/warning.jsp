<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title><c:out value="${pageTitle}"></c:out></title>

    <meta name="description" content="find tickets ob KudKuda.rus">
    <meta name="keywords" content="find tickets, find trains, schedule of trains, stations list">


    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/animate.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/select2.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/helpers.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">

    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/owl.carousel.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark probootstrap_navbar" id="probootstrap-navbar">
    <div class="container">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#probootstrap-menu"
                aria-controls="probootstrap-menu" aria-expanded="false" aria-label="Toggle navigation">
            <span><i class="ion-navicon"></i></span>
        </button>
        <div class="collapse navbar-collapse" id="probootstrap-menu">
        </div>
    </div>
</nav>



<section class="probootstrap-cover overflow-hidden relative"
         style="background-image: url('/resources/images/err2.jpg');">
    <div class="overlay"></div>
    <div class="container">
        <div class="row align-items-center">
            <div class="col-md"></div>
            <div class="col-md">
                <h2 class="heading mb-2 display-4 font-light probootstrap-animate">WOooops!</h2>
                <p class="lead mb-4 probootstrap-animate">
                    <span style="color:#32CD32;font-size: 140%">Something wrong, don't you think so...</span>
                    <br>
                    <a  style="color:#c6c600;font-size: 140%" href="/home.jsp"> go home  </a>
                    or
                    <a style="color:#c6c600;font-size: 140%" href="./">  go back </a>
<br>
<br>
<br>
<br>
                </p>
            </div>
        </div>
    </div>
</section>


<script src="${pageContext.request.contextPath}/resources/js/bootstrap-datepicker.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.waypoints.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.easing.1.3.js"></script>

<script src="${pageContext.request.contextPath}/resources/js/select2.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
</body>
</html>