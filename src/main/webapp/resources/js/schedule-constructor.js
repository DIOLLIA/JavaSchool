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