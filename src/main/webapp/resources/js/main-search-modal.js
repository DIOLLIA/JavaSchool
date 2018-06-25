window.onload = function () {
    $('#route-info').on('click', function () {
        var tableRow = $(this).closest('tr');
        var train = tableRow.find('.train_number').text().trim();

        $.ajax({
            type: 'GET',
            url: '/routes/findRoute/',
            data: {trainNumber: train}
        }).done(function (formatedSchedule) {
            formatedSchedule.forEach(function (item, i) {
               document.getElementById("routeItem").append("<tr>" +
                   "<td>" + item.stationOfDeparture + "</td>" + "<td> " + item.arrivalTime.hourOfDay + ":" + item.arrivalTime.minuteOfHour +
                    +"</td>" + "<td>" + item.arrivalTime.hourOfDay + ":" + item.arrivalTime.minuteOfHour + "</td>"+" </tr>")
            });

        });

        $("#modal").modal({
            backdrop: 'static',
            keyboard: false
        });
    });
};