window.onload = function () {
    trainPicker();
    routesPicker();

    document.getElementById("route_picker")
        .addEventListener("change", function () {
            stationsOnRoute();
        });
};

function stationsOnRoute() {
    var route = $("#route_picker").val();
    var stationList = $("#stations_list");
    $.ajax({
        type: 'GET',
        url: '/routes/get-stations/' + route,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (stations) {
            stationList.empty();
            stations.forEach(function (item, i) {
                stationList.append(
                    '<option value=\"' + item + '\">' + item + '</option>'
                )
            });
        },
        error: function () {
        }
    })
}

function trainPicker() {
    var trains = $("#train_picker");
    trains.empty();

    $.ajax({
        type: 'GET',
        url: '/routes/get-trains',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (train) {
            train.forEach(function (item, i) {
                trains.append(
                    '<option value=\"' + item + '\">' + item + '</option>'
                )
            });
        },
        error: function (response) {
        }
    })
}

function routesPicker() {
    var routes = $("#route_picker");
    routes.empty();

    $.ajax({
        type: 'GET',
        url: '/routes/get-routes/',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (stations) {
            stations.forEach(function (item, i) {
                routes.append(
                    '<option value=\"' + item + '\">' + item + '</option>'
                )
            });
            stationsOnRoute();
        },
        error: function (response) {
        }
    })
}

function constructor_validator() {
    var res = /^-{0,1}\d*\.{0,1}\d+$/;

    var arrival_time = document.schedule_constructor.arrival_time.value;
    var departure_time = document.schedule_constructor.departure_time.value;
    var daily_route_id = document.schedule_constructor.daily_route.value;
    var number_in_order = document.schedule_constructor.number_in_order.value;
    var counter_fields = 0;
    var fields_variables = 4;
    document.getElementById("emptyTest").innerHTML ="OK";
    document.getElementById("emptyTest2").innerHTML ="OK";
    document.getElementById("emptyTest3").innerHTML ="OK";

    if (daily_route_id === null || daily_route_id === "") {
        document.getElementById("dailyIdCheck").innerHTML =
            "Required";
    } else if (!res.test(daily_route_id)) {
        document.getElementById("dailyIdCheck").innerHTML =
            "number!";
    }
    else {
        counter_fields++;
    }

    if (number_in_order === null || number_in_order === "") {
        document.getElementById("orderNumberCheck").innerHTML =
            "Required";
    } else if (!res.test(daily_route_id)) {
        document.getElementById("orderNumberCheck").innerHTML =
            "number!";
    }
    else {
        counter_fields++;
    }

    if (arrival_time === null || arrival_time === "") {
        document.getElementById("arrivalCheck").innerHTML =
            "  Enter the time";
    }
    else {
        document.getElementById("arrivalCheck").innerHTML =
            "OK";
        counter_fields++;
    }

    if (departure_time === null || departure_time === "") {
        document.getElementById("departureCheck").innerHTML =
            "  Enter the time";
    }
    else {
        document.getElementById("departureCheck").innerHTML =
            "OK";
        counter_fields++;
    }
    return counter_fields === fields_variables;
}