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

                routeList.append(
                    "<table id='routes-table'>" +
                    "<tr>" +
                    "<th>Arrival time</th>" +
                    "<th>Station</th>" +
                    "<th>Departure time</th>" +
                    "</tr>" +
                    "<tbody></tbody></table>");
                for (var i = 0; i < scheduleList.length; i++) {
                    $("#routes-table").append(
                        "<tr>" +
                        "<td>" + scheduleList[i].arrivalTime.hourOfDay + ":" + scheduleList[i].arrivalTime.minuteOfHour + "</td>" +
                        "<td>" + scheduleList[i].stationOfDeparture + "</td>" +
                        "<td>" + scheduleList[i].departureTime.hourOfDay + ":" + scheduleList[i].departureTime.minuteOfHour + "</td>" +
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