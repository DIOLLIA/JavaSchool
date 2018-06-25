window.onload = function () {
    $('.route-info_class').on('click', function () {
        var tableRow = $(this).closest('tr');
        var train = tableRow.find('.train_number').text().trim();

        $.ajax({
            type: 'GET',
            url: '/routes/findRoute/',
            data: {trainNumber: train}
        }).done(function (scheduleList) {
            if (scheduleList.length > 0) {
                var routeList = $('#route-list');
                routeList.empty();
                var depTimeH;
                var depTimeM;
                var arrTimeH;
                var arrTimeM;
                routeList.append(
                    "<table id='routes-table'>" +
                    "<tr>" +
                    "<th>Arrival time</th>" +
                    "<th>Station</th>" +
                    "<th>Departure time</th>" +
                    "</tr>" +
                    "<tbody></tbody></table>");
                for (var i = 0; i < scheduleList.length; i++) {
                    arrTimeH = scheduleList[i].arrivalTime.hourOfDay;
                    if (arrTimeH < 10) {
                        arrTimeH = "0" + arrTimeH
                    }
                    arrTimeM = scheduleList[i].arrivalTime.minuteOfHour;
                    if (arrTimeM < 10) {
                        arrTimeM = "0" + arrTimeM
                    }
                    depTimeH = scheduleList[i].departureTime.hourOfDay;
                    if (depTimeH < 10) {
                        depTimeH = "0" + depTimeH
                    }
                    depTimeM = scheduleList[i].departureTime.minuteOfHour;
                    if (depTimeM < 10) {
                        depTimeM = "0" + depTimeM
                    }
                    $("#routes-table").append(
                        "<tr>" +
                        "<td>" + depTimeH + ":" + arrTimeM + "</td>" +
                        "<td>" + scheduleList[i].stationOfDeparture + "</td>" +
                        "<td>" + depTimeH + ":" + depTimeM + "</td>" +
                        "</tr>"
                    );
                }
            }
        });

        $("#modal").modal({
            backdrop: 'static',
            keyboard: false
        });
    });
};