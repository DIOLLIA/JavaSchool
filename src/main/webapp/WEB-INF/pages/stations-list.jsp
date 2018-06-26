<%@ include file="header-admin.jsp" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/tables.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/map.css">

<!-- Replace the value of the key parameter with your own API key. -->
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAs1aa0zAhhVFSu6u9iROPYC0zABdX64b4&language=en&libraries=places&callback=initialize"
        async defer></script>

<script src="${pageContext.request.contextPath}/resources/js/stations.js"></script>

<section class="probootstrap-cover overflow-hidden relative"
         style="background-image: url('/resources/images/img_5.jpg');" data-stellar-background-ratio="0.5"
         id="section-home">
    <div class="overlay"></div>
    <div class="container">
        <div class="row align-items-center">
            <div class="col-md probootstrap-animate">
                <h2 class="heading mb-2 display-4 font-light probootstrap-animate"> <input class="btn btn-primary"
                                                                                           type="submit" value="<<"
                                                                                           onclick="location='/index';"/>
                    <spring:message code="admin.header.stations"/> &#8195;
                    <input id="add-station" type="submit"
                           class="btn btn-primary"
                           value="<spring:message code="add-station-button"/>"/>
                </h2>
                <h2 style="color: yellow">${message}</h2>
                <table class="table_price" border="0px" cellpadding="" cellspacing="0">
                    <thead>
                    <tr>
                        <th width="30%">Name</th>
                        <th width="10%">Latitude</th>
                        <th width="10%">Longitude</th>
                        <th width="10%">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="station" items="${stations}">
                        <tr>
                            <td>${station.stationName}</td>
                            <td>${station.latitude}</td>
                            <td>${station.longitude}</td>
                               <td> <a href="${pageContext.request.contextPath}/station/delete/${station.id}">Delete</a><br/>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div id="modal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Add new station</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="pac-card" id="pac-card">
                        <div id="pac-container">
                            <input id="pac-input" type="text" placeholder="Enter a location">
                        </div>
                        <div>
                            <button id="add-station-btn" data-role="button">Add</button>
                        </div>
                    </div>
                    <div id="map-canvas"></div>
                    <div id="infowindow-content">
                        <img src="" width="16" height="16" id="place-icon">
                        <span id="place-name" class="title"></span><br>
                        <span id="place-address"></span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<%@ include file="footer.jsp" %>