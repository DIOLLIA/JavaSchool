<%@ include file="header-admin.jsp" %>

<section class="probootstrap-cover overflow-hidden relative"
         style="background-image: url('/resources/images/bg_1.jpg');" data-stellar-background-ratio="0.5"
         id="section-home">
    <div class="overlay"></div>
    <h4 class="heading mb-2 display-4 font-light probootstrap-animate" style="text-align:  center; color: whitesmoke">
        KudKuda Master
        Page</h4>
    <div class="container">
        <div class="row align-items-center " style="padding: 2px;">
            <div class="col-md">
                <form:form class="probootstrap-form" cssStyle=" background-color:transparent">
                    <div class="form-group " style="font-size: 130%; color:white">
                        <h4 style="color:#32CD32 "> Stations</h4>
                        <li> Allows to see the list of stations</li>
                        <li> add new station or edit\remove exist station</li>
                        <li> view routes for station</li>
                        <br>
                        <br>
                        <input style="font-size: 100%" type="button" value="Go to stations"
                               class="btn btn-primary btn-block" onclick="location='/station/list'">
                    </div>
                </form:form>
            </div>
            <div class="col-md">
                <form:form class="probootstrap-form" cssStyle=" background-color:transparent">
                    <div class="form-group " style="font-size: 130%; color:white">
                        <h4 style="color:#32CD32 "> Trains</h4>
                        <li> Allows to see the list of trains</li>
                        <li> Add new train or edit\remove exist train</li>
                        <li> View schedule and route for train</li>
                        <li> View registered passengers on train by date</li>
                        <br>
                        <input style="font-size: 100%" type="button" value="Go to trains"
                               class="btn btn-primary btn-block" onclick="location='/train/list'">
                    </div>
                </form:form>
            </div>
        </div>
        <div class="row align-items-center " style="padding: 2px;">
            <div class="col-md">
                <form:form class="probootstrap-form" cssStyle=" background-color:transparent">
                    <div class="form-group " style="font-size: 130%; color:white">
                        <h4 style="color:#32CD32 "> Users</h4>
                        <li> Allows to see the list of user</li>
                        <li> Add new station or enable/disable exist user</li>
                        <li> Create user</li>
                        <br>
                        <input style="font-size: 100%" type="button" value="Go to users"
                               class="btn btn-primary btn-block" onclick="location='/user/list'">
                    </div>
                </form:form>
            </div>
            <div class="col-md">
                <form:form class="probootstrap-form" cssStyle=" background-color:transparent">
                    <div class="form-group " style="font-size: 130%; color:white">
                        <h4 style="color:red"> Schedule editor SOON</h4>
                        <li> Allows to see existing schedules</li>
                        <li> Add new schedule or edit existing</li>
                        <br>
                        <br>
                        <input style="font-size: 100%" type="button" value="Nowhere to go"
                               class="btn btn-primary btn-block" <%--onclick="location='/station/list'"--%>>
                    </div>
                </form:form>
            </div>
        </div>
</section>
<%@ include file="footer.jsp" %>