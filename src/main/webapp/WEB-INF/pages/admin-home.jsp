<%@ include file="header-admin.jsp" %>

<section class="probootstrap-cover overflow-hidden relative"
         style="background-image: url('/resources/images/bg_1.jpg');" data-stellar-background-ratio="0.5"
         id="section-home">
    <div class="overlay"></div>
    <div class="container">
        <div class="row align-items-center " style="padding: 2px;">
            <div class="col-md">
                <form:form class="probootstrap-form" cssStyle=" background-color:transparent">
                    <div class="form-group " style="font-size: 130%; color:white">
                        <h4 style="color:#32CD32"><spring:message code="admin.header.schedule"/></h4>
                        <ul>
                            <li><spring:message code="admin.see-schedule"/></li>
                            <li><spring:message code="admin.add-new-schedule-item"/></li>
                            <li><spring:message code="admin.edit-schedule"/></li>
                            <li><spring:message code="admin.view-station-routes"/></li>
                        </ul>
                        <input style="font-size: 100%" type="button"
                               value="<spring:message code="admin.btn.manage-schedule"/>"
                               class="btn btn-primary btn-block" onclick="location='/schedule/scheduleList'">
                    </div>
                </form:form>
            </div>
            <div class="col-md">
                <form:form class="probootstrap-form" cssStyle=" background-color:transparent">
                    <div class="form-group " style="font-size: 130%; color:white">
                        <h4 style="color:#32CD32 "><spring:message code="admin.header.trains"/></h4>
                        <ul>
                            <li><spring:message code="admin.see-list-of-trains"/></li>
                            <li><spring:message code="admin.add-new-train"/></li>
                            <li><spring:message code="admin.edit-remove-train"/></li>
                            <li><spring:message code="admin.view-registered-passengers"/></li>
                        </ul>
                        <input style="font-size: 100%" type="button"
                               value="<spring:message code="admin.btn.manage-trains"/>"
                               class="btn btn-primary btn-block" onclick="location='/train/list'">
                    </div>
                </form:form>
            </div>
        </div>
        <div class="row align-items-center " style="padding: 2px;">
            <div class="col-md">
                <form:form class="probootstrap-form" cssStyle=" background-color:transparent">
                    <div class="form-group " style="font-size: 130%; color:white">
                        <h4 style="color:#32CD32 "><spring:message code="admin.header.stations"/></h4>
                        <ul>
                            <li><spring:message code="admin.see-list-of-stations"/></li>
                            <li><spring:message code="admin.add-new-station"/></li>
                            <li><spring:message code="admin.edit-remove-station"/></li>
                        </ul>
                        <input style="font-size: 100%" type="button"
                               value="<spring:message code="admin.btn.manage-stations"/> "
                               class="btn btn-primary btn-block" onclick="location='/station/list'">
                    </div>
                </form:form>
            </div>
            <div class="col-md">
                <form:form class="probootstrap-form" cssStyle=" background-color:transparent">
                    <div class="form-group " style="font-size: 130%; color:white">
                        <h4 style="color:#32CD32 "><spring:message code="admin.header.users"/></h4>
                        <ul>
                            <li><spring:message code="admin.see-users-list"/></li>
                            <li><spring:message code="admin.enable-disable-user"/></li>
                            <li><spring:message code="admin.create-user"/></li>
                        </ul>
                        <input style="font-size: 100%" type="button"
                               value="<spring:message code="admin.btn.manage-users"/>"
                               class="btn btn-primary btn-block" onclick="location='/user/list'">
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</section>
<%@ include file="footer.jsp" %>