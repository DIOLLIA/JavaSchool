<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="<c:url value='/resources/css/map.css' />" rel="stylesheet">
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>

<%--
  Created by IntelliJ IDEA.
  User: KotoSovik
  Date: 02.06.2018
  Time: 20:52
  To change this template use File | Settings | File Templates.
--%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/tables.css">
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- Replace the value of the key parameter with your own API key. -->
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBJF0U-ipBRCIh7wn9NftxO-vZbaHwU994&language=en&region=GB&libraries=places&callback=initMap"
        async defer></script>

<script src="../../resources/js/map.js"></script>


<div class="pac-card" id="pac-card">
    <div>
        <div id="title">
            Add new station
        </div>
    </div>
    <div id="pac-container">
        <input id="pac-input" type="text" style="color: black" placeholder="Enter a location">
    </div>
    <div>
        <button class="" id="add-station-btn" data-role="button">Add</button>
    </div>

</div>
<div id="map"></div>
<div id="infowindow-content">
    <img src="" width="16" height="16" id="place-icon">
    <span id="place-name" class="title"></span><br>
    <span id="place-address"></span>
</div>



-
</body>
</html>
