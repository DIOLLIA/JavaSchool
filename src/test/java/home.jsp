<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Train Search</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--adding css files-->
    <link rel="stylesheet" type="text/css" href="./reset.css">
    <link rel="stylesheet prefetch" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.0/css/bootstrap-datepicker3.standalone.css">
    <link rel="stylesheet" type="text/css" href="./style.css?v_1207">
    <!--adding js files-->
    <script src=".jquery.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-dateFormat/1.0/jquery.dateFormat.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.0/js/bootstrap-datepicker.min.js"></script>
    <script src="jquery.twidget.js?v_1207"></script>



    <!--adding google font-->
    <!--<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,300,400italic,300italic,600,600italic,700,700italic,800,800italic" rel="stylesheet" type="text/css">-->
    <link href='https://fonts.googleapis.com/css?family=Open+Sans&subset=latin,cyrillic' rel='stylesheet' type='text/css'>
</head>
<body style="margin: 0;background: #a6691d">
<br><br>
<p><b>Example of avia search form on Russian</b></p>
<br><br>
<div class="twidget-container" id="twidget"></div>
<br><br>
<script>
    $('#twidget').twidget({
        locale: 'ru',
        marker: 78606,
        type: 'avia'
    });
</script>


<br>
go to
<a href="${pageContext.request.contextPath}/train/list">train list</a><br/>
go to
<a href="${pageContext.request.contextPath}/station/list">station list</a><br/>
</body>
</html>
