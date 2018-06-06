function formSubmit() {
    document.getElementById("logoutForm").submit();
}

function trainsAndDateSelector() {
    var stationsFrom = $("#station_from").val();
    var stationsTo = $("#station_to").val();
    var train = $("#train");
    train.empty();
    var departure_time = $("#departure_time");
    departure_time.empty();

    var stations = {};
    stations["stationFrom"] = stationsFrom;
    stations["stationTo"] = stationsTo;

    $.ajax({
        headers: {"X-CSRF-TOKEN": $("meta[name='_csrf']").attr("content")},
        type: 'POST',
        url: '/searchForUser/get-train-and-time/',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify(stations),
        success: function (trainsAndTime) {
            trainsAndTime.forEach(function (item) {
                train.append(
                    '<option value=\"' + item['trainNumber'] + '\">' + item['trainNumber'] + '</option>'
                );
                departure_time.append(
                    '<option value=\"' + item['departureTime'] + '\">' + item['departureTime'] + '</option>'
                )
            });
        },
        error: function (response) {
            alert("Something is wrong!")
        }
    });
}

function showRoutePassengersByDate(trainId, dailyRouteId, startTime) {
    var departureDate = $("#probootstrap-date-departure").val();

    window.location.replace('/train/schedule/' + trainId + '/passengers/' + dailyRouteId + '?date=' + departureDate + '&startTime=' + startTime)
}

function initFromStations() {
    var stationsFrom = $("#station_from");
    stationsFrom.empty();

    $.ajax({
        type: 'GET',
        url: '/mainSearch/get-stations/',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (stations) {
            stations.forEach(function (item, i) {
                stationsFrom.append(
                    '<option value=\"' + item + '\">' + item + '</option>'
                )
            });
            getToStations();
        },
        error: function (response) {
            alert("Something is wrong!")
        }
    })
}

function getToStations() {
    var stationsFrom = $("#station_from").val();
    var stationsTo = $("#station_to");
    $.ajax({
        type: 'GET',
        url: '/mainSearch/get-stations/' + stationsFrom,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (stations) {
            stationsTo.empty();
            stations.forEach(function (item, i) {
                stationsTo.append(
                    '<option value=\"' + item + '\">' + item + '</option>'
                )
            });
        },
        error: function () {
            alert("Something is wrong!")
        }
    })

}

$(document).ready(function ($) {

    initFromStations();

        var scrollWindow = function () {
            var lastScrollTop = 0;
            $(window).scroll(function () {
                var $w = $(this),
                    st = $w.scrollTop(),
                    navbar = $('.probootstrap_navbar'),
                    sd = $('.js-scroll-wrap');


                if (st > 150) {
                    if (!navbar.hasClass('scrolled')) {
                        navbar.addClass('scrolled');
                    }
                }
                if (st < 150) {
                    if (navbar.hasClass('scrolled')) {
                        navbar.removeClass('scrolled sleep');
                    }
                }
                if (st > 350) {
                    if (!navbar.hasClass('awake')) {
                        navbar.addClass('awake');
                    }
                }
                if (st < 350) {
                    if (navbar.hasClass('awake')) {
                        navbar.removeClass('awake');
                        navbar.addClass('sleep');
                    }
                }

            });
        };
        scrollWindow();


        // navigation
        var OnePageNav = function () {
            var navToggler = $('.navbar-toggler');
            $(".smoothscroll[href^='#'], #probootstrap-navbar ul li a[href^='#']").on('click', function (e) {
                e.preventDefault();
                var hash = this.hash;

                $('html, body').animate({

                    scrollTop: $(hash).offset().top
                }, 700, 'easeInOutExpo', function () {
                    window.location.hash = hash;
                });
            });
            $("#probootstrap-navbar ul li a[href^='#']").on('click', function (e) {
                if (navToggler.is(':visible')) {
                    navToggler.click();
                }
            });

        };
        OnePageNav();


        var select2 = function () {
            $('.js-dropdown-multiple, .js-example-basic-single').select2();
        }
        select2();


        var contentWayPoint = function () {
            var i = 0;
            if ($('.probootstrap-animate').length > 0) {
                $('.probootstrap-animate').waypoint(function (direction) {

                    if (direction === 'down' && !$(this.element).hasClass('probootstrap-animated')) {

                        i++;

                        $(this.element).addClass('item-animate');
                        setTimeout(function () {

                            $('body .probootstrap-animate.item-animate').each(function (k) {
                                var el = $(this);
                                setTimeout(function () {
                                    var effect = el.data('animate-effect');
                                    if (effect === 'fadeIn') {
                                        el.addClass('fadeIn probootstrap-animated');
                                    } else if (effect === 'fadeInLeft') {
                                        el.addClass('fadeInLeft probootstrap-animated');
                                    } else if (effect === 'fadeInRight') {
                                        el.addClass('fadeInRight probootstrap-animated');
                                    } else {
                                        el.addClass('fadeInUp probootstrap-animated');
                                    }
                                    el.removeClass('item-animate');
                                }, k * 50, 'easeInOutExpo');
                            });

                        }, 50);

                    }

                }, {offset: '95%'});
            }
        };
        contentWayPoint();


        var owlCarouselFunc = function () {
            $('.js-owl-carousel').owlCarousel({
                loop: false,
                margin: 20,
                nav: true,
                stagePadding: 50,
                navText: ["<span class='ion-chevron-left'></span>", "<span class='ion-chevron-right'></span>"],
                responsive: {
                    0: {
                        items: 1
                    },
                    600: {
                        items: 2
                    },
                    1000: {
                        items: 3
                    }
                }
            });

            $('.js-owl-carousel-2').owlCarousel({
                loop: false,
                margin: 20,
                nav: true,
                stagePadding: 0,
                navText: ["<span class='ion-chevron-left'></span>", "<span class='ion-chevron-right'></span>"],
                responsive: {
                    0: {
                        items: 1
                    },
                    600: {
                        items: 1
                    },
                    800: {
                        items: 2
                    },
                    1000: {
                        items: 3
                    }
                }
            });
        };
        owlCarouselFunc();

        var ThumbnailOpacity = function () {
            var t = $('.probootstrap-thumbnail');
            t.hover(function () {
                var $this = $(this);
                t.addClass('sleep');
                $this.removeClass('sleep');
            }, function () {
                var $this = $(this);
                t.removeClass('sleep');
            });
        }
        ThumbnailOpacity();

        var datePicker = function () {
            $('#probootstrap-date-departure, #probootstrap-date-arrival').datepicker({
                'format': 'm/d/yyyy',
                'autoclose': true
            });
        };
        datePicker();
    }
);

function validate() {
    var name = document.signInForm.username.value;
    var password = document.signInForm.password.value;
    var status = false;

    if (name.length < 2) {
        document.getElementById("nameloc").innerHTML =
            " <img src='resources/images/Close-2-icon.png'/> Please enter your name";
        status = false;
    } else {
        document.getElementById("nameloc").innerHTML = " <img src='resources/images/check_sign_icon_green.png'/>";
        status = true;
    }
    if (password.length < 4) {
        document.getElementById("passwordloc").innerHTML =
            " <img src='resources/images/Close-2-icon.png'/> Password must be at least 4 char long";
        status = false;
    } else {
        document.getElementById("passwordloc").innerHTML = " <img src='resources/images/check_sign_icon_green.png'/>";
    }
    return status;
}


function reg_validate() {
    var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    var email = document.signUpForm.email.value;
    var password = document.signUpForm.password.value;
    var passwordConf = document.signUpForm.passwordConf.value;
    var name = document.signUpForm.name.value;
    var surname = document.signUpForm.surname.value;
    var birthday = document.signUpForm.birthDay.value;
    var fields_variables = 6;
    var counter_fields = 0;

    if (email.length === "" || email === null || reg.test(email) === false) {
        document.getElementById("emailloc").innerHTML =
            " <img src='../resources/images/Close-2-icon.png'> Please enter valid email";
    }
    else {
        document.getElementById("emailloc").innerHTML = " <img src='../resources/images/check_sign_icon_green.png'/>";
        counter_fields++;
    }

    if (password.length < 4) {
        document.getElementById("passwordloc").innerHTML =
            " <img src='../resources/images/Close-2-icon.png'> Password must be at least 4 char long";
    } else {
        document.getElementById("passwordloc").innerHTML = " <img src='../resources/images/check_sign_icon_green.png'/>";
        counter_fields++;
    }

    if (passwordConf.length > 3) {
        if (password !== passwordConf) {
            document.getElementById("passwordConfloc").innerHTML =
                " <img src='../resources/images/Close-2-icon.png'>Passwords do not match";
        }
        else {
            document.getElementById("passwordConfloc").innerHTML = " <img src='../resources/images/check_sign_icon_green.png'/>";
            counter_fields++;
        }
    } else {
        document.getElementById("passwordConfloc").innerHTML =
            " <img src='../resources/images/Close-2-icon.png'>Password must be at least 4 char long";
    }

    if (name.length < 2) {
        document.getElementById("nameloc").innerHTML =
            " <img src='../resources/images/Close-2-icon.png'> Name can't be least 2 chars";
    } else {
        document.getElementById("nameloc").innerHTML = " <img src='../resources/images/check_sign_icon_green.png'/>";
        counter_fields++;
    }

    if (surname.length < 2) {
        document.getElementById("surnameloc").innerHTML =
            " <img src='../resources/images/Close-2-icon.png'> Surname can't be least 2 chars";
    } else {
        document.getElementById("surnameloc").innerHTML = " <img src='../resources/images/check_sign_icon_green.png'/>";
        counter_fields++;
    }

    if (birthday == null || birthday === "") {
        document.getElementById("birthdayloc").innerHTML =
            " <img src='../resources/images/Close-2-icon.png'> select date";
    } else {
        document.getElementById("birthdayloc").innerHTML = " <img src='../resources/images/check_sign_icon_green.png'/>";
        counter_fields++;
    }
    if (counter_fields === fields_variables) {
        return true;
    }
    else {
        return false;
    }
}

function main_search_validation() {

    var departure_date = document.mainSearch.searchDate.value;
    var departure_time = document.mainSearch.searchTime.value;
    var counter_fields = 0;
    var fields_variables = 2;

    if (departure_time === null || departure_time === "") {
        document.getElementById("timeloc").innerHTML =
            " <img src='../resources/images/Close-2-icon.png'> Please, enter the time";
    } else {
        document.getElementById("timeloc").innerHTML = "<img src='../resources/images/check_sign_icon_green.png'/>";
        counter_fields++;

    }
    if (departure_date === null || departure_date === "") {
        document.getElementById("dateloc").innerHTML = "<img src='../resources/images/Close-2-icon.png'>Pick date";
    } else {
        document.getElementById("dateloc").innerHTML = "<img src='../resources/images/check_sign_icon_green.png'/>";
        counter_fields++;
    }

    return counter_fields === fields_variables;

}