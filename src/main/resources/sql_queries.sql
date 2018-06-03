INSERT INTO railways.stations (id, station_name, latitude, longitude) VALUES (1, 'devyatkino', 1, 2);
INSERT INTO railways.stations (id, station_name, latitude, longitude) VALUES (2, 'lesnaya', 1, 2);
INSERT INTO railways.stations (id, station_name, latitude, longitude) VALUES (3, 'pushkinskaya', 1, 2);
INSERT INTO railways.stations (id, station_name, latitude, longitude) VALUES (4, 'veteranov', 1, 2);
INSERT INTO railways.stations (id, station_name, latitude, longitude) VALUES (5, 'komenda', 1, 2);
INSERT INTO railways.stations (id, station_name, latitude, longitude) VALUES (6, 'central station', 1, 2);
INSERT INTO railways.stations (id, station_name, latitude, longitude) VALUES (7, 'mejdunarodnaya', 1, 2);
INSERT INTO railways.stations (id, station_name, latitude, longitude) VALUES (8, 'kupchino', 1, 2);
INSERT INTO railways.stations (id, station_name, latitude, longitude) VALUES (9, 'dibenko', 1, 2);
INSERT INTO railways.stations (id, station_name, latitude, longitude) VALUES (10, 'ribatskoe', 1, 2);
INSERT INTO railways.stations (id, station_name, latitude, longitude) VALUES (11, 'primorskaya', 1, 2);

INSERT INTO railways.routes (id, route_name) VALUES (1, 'devyatkino-veteranov');
INSERT INTO railways.routes (id, route_name) VALUES (2, 'dibenko-central station');
INSERT INTO railways.routes (id, route_name) VALUES (3, 'primorskaya-ribatskoe');
INSERT INTO railways.routes (id, route_name) VALUES (4, 'kupchino-central station');
INSERT INTO railways.routes (id, route_name) VALUES (5, 'komenda-mejdunarodnaya');
INSERT INTO railways.routes (id, route_name) VALUES (6, 'veteranov-devyatkino');
INSERT INTO railways.routes (id, route_name) VALUES (7, 'central station-dibenko');
INSERT INTO railways.routes (id, route_name) VALUES (8, 'ribatskoe-primorskaya');
INSERT INTO railways.routes (id, route_name) VALUES (9, 'central station-kupchino');
INSERT INTO railways.routes (id, route_name) VALUES (10, 'komenda-mejdunarodnaya');

INSERT INTO railways.trains (id, train_number, seats) VALUES (1, 0, 100);
INSERT INTO railways.trains (id, train_number, seats) VALUES (2, 111, 100);
INSERT INTO railways.trains (id, train_number, seats) VALUES (3, 222, 100);
INSERT INTO railways.trains (id, train_number, seats) VALUES (4, 333, 100);
INSERT INTO railways.trains (id, train_number, seats) VALUES (5, 444, 100);
INSERT INTO railways.trains (id, train_number, seats) VALUES (6, 555, 100);
INSERT INTO railways.trains (id, train_number, seats) VALUES (7, 666, 100);
INSERT INTO railways.trains (id, train_number, seats) VALUES (8, 777, 100);
INSERT INTO railways.trains (id, train_number, seats) VALUES (9, 888, 100);
INSERT INTO railways.trains (id, train_number, seats) VALUES (10, 999, 100);

INSERT INTO railways.routes_stations (routes_id, stationsList_id) VALUES (1, 1);
INSERT INTO railways.routes_stations (routes_id, stationsList_id) VALUES (1, 2);
INSERT INTO railways.routes_stations (routes_id, stationsList_id) VALUES (1, 3);
INSERT INTO railways.routes_stations (routes_id, stationsList_id) VALUES (1, 4);
INSERT INTO railways.routes_stations (routes_id, stationsList_id) VALUES (2, 9);
INSERT INTO railways.routes_stations (routes_id, stationsList_id) VALUES (2, 3);
INSERT INTO railways.routes_stations (routes_id, stationsList_id) VALUES (2, 6);
INSERT INTO railways.routes_stations (routes_id, stationsList_id) VALUES (3, 11);
INSERT INTO railways.routes_stations (routes_id, stationsList_id) VALUES (3, 3);
INSERT INTO railways.routes_stations (routes_id, stationsList_id) VALUES (3, 10);
INSERT INTO railways.routes_stations (routes_id, stationsList_id) VALUES (4, 8);
INSERT INTO railways.routes_stations (routes_id, stationsList_id) VALUES (4, 6);
INSERT INTO railways.routes_stations (routes_id, stationsList_id) VALUES (5, 5);
INSERT INTO railways.routes_stations (routes_id, stationsList_id) VALUES (5, 6);
INSERT INTO railways.routes_stations (routes_id, stationsList_id) VALUES (5, 7);


INSERT INTO railways.schedule (id, arrival_time, departure_time, routeDailyId, routeStationIndex, timeInterval, routeName_id, stationName_id, trainNumber_id)
VALUES (1, NULL, '09:20', 1, 1, 1, 1, 1, 1);
INSERT INTO railways.schedule (id, arrival_time, departure_time, routeDailyId, routeStationIndex, timeInterval, routeName_id, stationName_id, trainNumber_id)
VALUES (2, '09:00', '09:20', 1, 2, 1, 1, 2, 1);
INSERT INTO railways.schedule (id, arrival_time, departure_time, routeDailyId, routeStationIndex, timeInterval, routeName_id, stationName_id, trainNumber_id)
VALUES (3, '12:00', '12:20', 1, 3, 1, 1, 3, 1);
INSERT INTO railways.schedule (id, arrival_time, departure_time, routeDailyId, routeStationIndex, timeInterval, routeName_id, stationName_id, trainNumber_id)
VALUES (4, '16:00', NULL, 1, 4, 1, 1, 4, 1);
INSERT INTO railways.schedule (id, arrival_time, departure_time, routeDailyId, routeStationIndex, timeInterval, routeName_id, stationName_id, trainNumber_id)
VALUES (5, NULL, '18:20', 3, 1, 1, 2, 9, 2);
INSERT INTO railways.schedule (id, arrival_time, departure_time, routeDailyId, routeStationIndex, timeInterval, routeName_id, stationName_id, trainNumber_id)
VALUES (6, '20:00', '20:20', 3, 2, 1, 2, 3, 2);
INSERT INTO railways.schedule (id, arrival_time, departure_time, routeDailyId, routeStationIndex, timeInterval, routeName_id, stationName_id, trainNumber_id)
VALUES (7, '22:00', NULL, 3, 3, 1, 2, 6, 2);
INSERT INTO railways.schedule (id, arrival_time, departure_time, routeDailyId, routeStationIndex, timeInterval, routeName_id, stationName_id, trainNumber_id)
VALUES (8, NULL, '15:20', 4, 1, 1, 3, 11, 3);
INSERT INTO railways.schedule (id, arrival_time, departure_time, routeDailyId, routeStationIndex, timeInterval, routeName_id, stationName_id, trainNumber_id)
VALUES (9, '17:00', '17:20', 4, 2, 1, 3, 3, 3);
INSERT INTO railways.schedule (id, arrival_time, departure_time, routeDailyId, routeStationIndex, timeInterval, routeName_id, stationName_id, trainNumber_id)
VALUES (10, '19:00', NULL, 4, 3, 1, 3, 10, 3);
INSERT INTO railways.schedule (id, arrival_time, departure_time, routeDailyId, routeStationIndex, timeInterval, routeName_id, stationName_id, trainNumber_id)
VALUES (11, NULL, '19:25', 5, 1, 1, 4, 8, 4);
INSERT INTO railways.schedule (id, arrival_time, departure_time, routeDailyId, routeStationIndex, timeInterval, routeName_id, stationName_id, trainNumber_id)
VALUES (12, '19:00', NULL, 5, 2, 1, 4, 6, 4);
INSERT INTO railways.schedule (id, arrival_time, departure_time, routeDailyId, routeStationIndex, timeInterval, routeName_id, stationName_id, trainNumber_id)
VALUES (13, NULL, '19:25', 6, 1, 1, 5, 5, 5);
INSERT INTO railways.schedule (id, arrival_time, departure_time, routeDailyId, routeStationIndex, timeInterval, routeName_id, stationName_id, trainNumber_id)
VALUES (14, '19:00', '19:25', 6, 2, 1, 5, 6, 5);
INSERT INTO railways.schedule (id, arrival_time, departure_time, routeDailyId, routeStationIndex, timeInterval, routeName_id, stationName_id, trainNumber_id)
VALUES (15, '19:00', NULL, 6, 3, 1, 5, 7, 5);

/*INSERT INTO railways.schedule (id, arrival_time, departure_time, routeDailyId,routeStationIndex, routeStationIndex, timeInterval, routeName_id, stationName_id, trainNumber_id)
VALUES (16, NULL, '19:20', 2,1, 1, 1, 1, 1);
INSERT INTO railways.schedule (id, arrival_time, departure_time, routeDailyId, routeStationIndex, timeInterval, routeName_id, stationName_id, trainNumber_id)
VALUES (17, '19:00', '19:20', 2, 1, 1, 2, 1);
INSERT INTO railways.schedule (id, arrival_time, departure_time, routeDailyId, routeStationIndex, timeInterval, routeName_id, stationName_id, trainNumber_id)
VALUES (18, '22:00', '22:20', 2, 1, 1, 3, 1);
INSERT INTO railways.schedule (id, arrival_time, departure_time, routeDailyId, routeStationIndex, timeInterval, routeName_id, stationName_id, trainNumber_id)
VALUES (19, '23:00', NULL, 2, 1, 1, 4, 1);*/

INSERT INTO railways.role (id, role) VALUES (1, 'ROLE_ADMIN');
INSERT INTO railways.role (id, role) VALUES (2, 'ROLE_USER');

INSERT INTO railways.users (id, birthDaY, email, name, password, surname, role_id) VALUES (1, '1993-05-27', 'sobaka@soboki.net', 'Kotopes', 'kotopes', 'Pesokot', 2);
INSERT INTO railways.users (id, birthDaY, email, name, password, surname, role_id) VALUES (2, '1996-09-07', 'netumila@milo.com', 'Vasilii', 'vasilii', 'Katap', 2);
INSERT INTO railways.users (id, birthDaY, email, name, password, surname, role_id) VALUES (3, '1993-06-17', 'kakoeto@milo.de', 'admin', 'admin', 'Onjeadmin', 1);
INSERT INTO railways.users (id, birthDaY, email, name, password, surname, role_id) VALUES (4, '1983-06-17', 'kud@ku.da', 'admin', 'kudkuda', 'Onjeadmin', 1);
INSERT INTO railways.users (id, birthDaY, email, name, password, surname, role_id) VALUES (5, '2018-06-02', 'root', 'root', 'root', 'root', 1);
INSERT INTO railways.users (id, birthDaY, email, name, password, surname, role_id) VALUES (6, '2018-06-02', 'user', 'user', 'user', 'user', 2);

INSERT INTO railways.ticketentity (id, ticketDateTime, departureSchedule_id, trainEntity_id, userEntity_id)
VALUES (1, '2018-05-20 17:47:17', 1, 1, 1);
INSERT INTO railways.ticketentity (id, ticketDateTime, departureSchedule_id, trainEntity_id, userEntity_id)
VALUES (2, '2018-05-21 17:43:48', 1, 1, 2);
INSERT INTO railways.ticketentity (id, ticketDateTime, departureSchedule_id, trainEntity_id, userEntity_id)
VALUES (3, '2018-05-20 15:48:07', 2, 1, 3);
INSERT INTO railways.ticketentity (id, ticketDateTime, departureSchedule_id, trainEntity_id, userEntity_id)
VALUES (4, '2018-05-20 11:48:34', 3, 1, 4);