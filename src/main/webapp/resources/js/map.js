// This example requires the Places library. Include the libraries=places
// parameter when you first load the API. For example:
// <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&libraries=places">
function initMap() {
    var map = new google.maps.Map(document.getElementById('map'), {
        center: {
            lat: 59.903507,
            lng: 30.511574
        },
        zoom: 12
    });
    var card = document.getElementById('pac-card');
    var input = document.getElementById('pac-input');
    var options = {
        types: ['(cities)'],
        componentRestrictions: {
            country: 'RU'
        }
    };

    map.controls[google.maps.ControlPosition.TOP_RIGHT].push(card);

    var autocomplete = new google.maps.places.Autocomplete(input, options);

    // Bind the map's bounds (viewport) property to the autocomplete object,
    // so that the autocomplete requests use the current map bounds for the
    // bounds option in the request.
    autocomplete.bindTo('bounds', map);

    var infowindow = new google.maps.InfoWindow();
    var infowindowContent = document.getElementById('infowindow-content');
    infowindow.setContent(infowindowContent);
    var marker = new google.maps.Marker({
        map: map,
        anchorPoint: new google.maps.Point(0, -29)
    });

    autocomplete.addListener('place_changed', function () {
        infowindow.close();
        marker.setVisible(false);
        var place = autocomplete.getPlace();
        if (!place.geometry) {
            // User entered the name of a Place that was not suggested and
            // pressed the Enter key, or the Place Details request failed.
            window.alert("No details available for input: '" + place.name + "'");
            return;
        }

        // If the place has a geometry, then present it on a map.
        if (place.geometry.viewport) {
            map.fitBounds(place.geometry.viewport);
        } else {
            map.setCenter(place.geometry.location);
            map.setZoom(8); // Why 17? Because it looks good.
        }
        marker.setPosition(place.geometry.location);
        marker.setVisible(true);

        var address = '';
        if (place.address_components) {
            address = [
                (place.address_components[0] && place.address_components[0].short_name || ''),
                (place.address_components[1] && place.address_components[1].short_name || ''),
                (place.address_components[2] && place.address_components[2].short_name || '')
            ].join(' ');
        }

        infowindowContent.children['place-icon'].src = place.icon;
        infowindowContent.children['place-name'].textContent = place.name;
        infowindowContent.children['place-address'].textContent = address;
        infowindow.open(map, marker);
    });
    window.onload = function () {
        document.getElementById("add-station-btn").addEventListener("click", function () {
            addStation(autocomplete);
        });
    };
}

function addStation(autocomplete) {
    var place = autocomplete.getPlace(),
        lat = place.geometry.location.lat(),
        lng = place.geometry.location.lng(),
        stationName = place.name;

    $.ajax({
        type: 'GET',
        url: '/add-station/',
        data: {
            placeName: stationName,
            lat: lat,
            lng: lng
        },
        success: function () {
            alert("Station was successfully created.")
        },
        error: function (e) {
            //todo add message try again later or contact technical support
        }
    })

}
