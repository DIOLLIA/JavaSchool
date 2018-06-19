INSERT INTO railways.stations (id, latitude, longitude, station_name) VALUES (1, 60.0479667, 30.44874089999996, 'Murino');
INSERT INTO railways.stations (id, latitude, longitude, station_name) VALUES (2, 60.15537080000001, 30.530654099999992, 'Toksovo');
INSERT INTO railways.stations (id, latitude, longitude, station_name) VALUES (3, 59.9342802, 30.335098600000038, 'Saint Petersburg');
INSERT INTO railways.stations (id, latitude, longitude, station_name) VALUES (4, 59.8065675, 30.396003199999996, 'Shushary');
INSERT INTO railways.stations (id, latitude, longitude, station_name) VALUES (5, 60.10831379999999, 30.28637249999997, 'Yukki');
INSERT INTO railways.stations (id, latitude, longitude, station_name) VALUES (6, 59.99183900000001, 30.158335699999952, 'Lakhta');
INSERT INTO railways.stations (id, latitude, longitude, station_name) VALUES (7, 59.8490261, 30.03430860000003, 'Strelna');
INSERT INTO railways.stations (id, latitude, longitude, station_name) VALUES (8, 59.9042085, 30.51012579999997, 'Kudrovo');
INSERT INTO railways.stations (id, latitude, longitude, station_name) VALUES (9, 60.0098612, 30.660640100000023, 'Vsevolozhsk');

INSERT INTO railways.routes (id, route_name) VALUES (1, 'Toksovo-Saint Petersburg');
INSERT INTO railways.routes (id, route_name) VALUES (2, 'Yukki-Saint Petersburg');
INSERT INTO railways.routes (id, route_name) VALUES (3, 'Strelna-Saint Petersburg');
INSERT INTO railways.routes (id, route_name) VALUES (4, 'Vsevolozhsk-Saint Petersburg');
INSERT INTO railways.routes (id, route_name) VALUES (5, 'Murino-Strelna');
INSERT INTO railways.routes (id, route_name) VALUES (6, 'Toksovo-Vsevolozhsk');
INSERT INTO railways.routes (id, route_name) VALUES (7, 'Shushary-Yukki');
INSERT INTO railways.routes (id, route_name) VALUES (8, 'Vsevolozhsk-Yukki');



INSERT INTO railways.trains (id, train_number, seats) VALUES (1, 1, 100);
INSERT INTO railways.trains (id, train_number, seats) VALUES (2, 2, 100);
INSERT INTO railways.trains (id, train_number, seats) VALUES (3, 2, 100);
INSERT INTO railways.trains (id, train_number, seats) VALUES (4, 3, 100);
INSERT INTO railways.trains (id, train_number, seats) VALUES (5, 4, 100);
INSERT INTO railways.trains (id, train_number, seats) VALUES (6, 5, 100);
INSERT INTO railways.trains (id, train_number, seats) VALUES (7, 6, 100);
INSERT INTO railways.trains (id, train_number, seats) VALUES (8, 7, 100);
INSERT INTO railways.trains (id, train_number, seats) VALUES (9, 8, 100);
INSERT INTO railways.trains (id, train_number, seats) VALUES (10,9, 100);

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

INSERT INTO railways.users (id, birthDaY, email, name, password, surname, role_id) VALUES (1, '1993-05-27', 'sobaka@soboki.net', 'Kotopes', '$2a$04$G3UTqMJvAhY1m3ukRAoL3.yiqudLUeNCMgQJddMJLjFztv.FTNXwO', 'Pesokot', 2);
INSERT INTO railways.users (id, birthDaY, email, name, password, surname, role_id) VALUES (2, '1996-09-07', 'netumila@milo.com', 'Vasilii', '$2a$04$cGZO1X1k/LlVypNTML/DVucX1nPGWzRnFvM0inX38o7B79oTOqHDS', 'Katap', 2);
INSERT INTO railways.users (id, birthDaY, email, name, password, surname, role_id) VALUES (3, '1993-06-17', 'kakoeto@milo.de', 'admin', '$2a$04$6TPLsWEzNIU0dJM7POApQerCaNxgFoFjIJknivcv3f/mKdmJ1Eaii', 'Onjeadmin', 1);
INSERT INTO railways.users (id, birthDaY, email, name, password, surname, role_id) VALUES (4, '1983-06-17', 'kud@ku.da', 'admin', '$2a$04$KElh.JMtLXpNewUH008cPuJnUvr90WVtMWbM6tYiytRI64XvQpL4u', 'Onjeadmin', 1);
INSERT INTO railways.users (id, birthDaY, email, name, password, surname, role_id) VALUES (5, '2018-06-02', 'root', 'rootrAWAW', '$2a$04$ibSLyFHdk8WBVH/igrAVQOZrMQBBH6dMxOuFrRuTWc0udJmgSk0T6', 'root', 1);
INSERT INTO railways.users (id, birthDaY, email, name, password, surname, role_id) VALUES (6, '2018-06-02', 'user', 'usernAME', '$2a$04$ZnRL.pGA4hySrPqi5ti6F.4/SsvjyiOn11eFJfxSO3mYzifApBrK2', 'user', 2);
INSERT INTO railways.users (id, birthDaY, email, name, password, surname, role_id) VALUES (7, '2018-06-01', 'anonymous@rt', 'And', '$2a$04$wwCWqFqmuLL.iCivnDlCEeY5zlM3yOMgBYuK77OG5y4Z00fkwoWB.', 'and', 2);
INSERT INTO railways.users (id, birthDaY, email, name, password, surname, role_id) VALUES (8, '2018-06-01', 'anonymousUser', 'anonymousUser', '$2a$04$JDz/M8y6H19WOdJapSArMOf2obzqBr6ubTwoG.aJUn0U0uLasuU22', 'anonymousUser', 2);
INSERT INTO railways.users (id, birthDaY, email, name, password, surname, role_id) VALUES (9, '2132-03-12', 'e@mail.com', 'popo', '$2a$04$p/SvT4LOB7deSColW2SueeT8kEHQ3HUHj264Fcv/CiQfFPKZ6hIN6', 'rastro', 2);
INSERT INTO railways.users (id, birthDaY, email, name, password, surname, role_id) VALUES (10, '2111-12-11', 'ereplica@mail.com', 'vasilii', '$2a$04$C.jhcPuk0lf0yFnsFPO33OYEvlX/7jJ8.p44Tb3OgihBRMUXi7o3y', 'Rastropovich', 2);
INSERT INTO railways.users (id, birthDaY, email, name, password, surname, role_id) VALUES (11, '2156-03-12', 'Misha@mi', 'Misha', '$2a$04$TCm/YtFNgZUQ6YUYUyw/lOA3O0oDQZdoKAHSfE3FMpRspMM8BUYqe', 'Mikonk', 2);
INSERT INTO railways.users (id, birthDaY, email, name, password, surname, role_id) VALUES (12, '3212-12-31', 'kalik@wa', 'kolya', '$2a$04$6xCI8vY8A.iSYDCmyGN2ee3GVB1eyx.KN45hmS1lLPiy16fozfGzm', 'niwa', 2);
INSERT INTO railways.users (id, birthDaY, email, name, password, surname, role_id) VALUES (13, '2321-03-05', 'velik@te', 'vechaslav', '$2a$04$/Nb3R71nEQJq3PFscBzdguOo2pJ68KRTr7pe1OhvUhEyZRrlrUTnS', 'potemkin', 2);
INSERT INTO railways.users (id, birthDaY, email, name, password, surname, role_id) VALUES (14, '2312-03-12', 'e@tmail.com', 'weqe', '$2a$04$Op4WZ8DZo3KH7PI.uzgE7e0y.kaS7iT.jFufMttcukBygYy7ZNkoW', 'ewrgs', 2);
INSERT INTO railways.users (id, birthDaY, email, name, password, surname, role_id) VALUES (15, '1911-07-12', 'babka@ka', 'Elizaveta', '$2a$04$j3hKco6vAY.PttWbfsygLeQT476yNKCh5npC/LK0NqBzIe719mwVm', 'Sidorovna', 2);
INSERT INTO railways.users (id, birthDaY, email, name, password, surname, role_id) VALUES (16, '1221-12-31', 'taamara@cobaka', 'toma', '$2a$04$XFOiKCdsO/jEvYUhL7q2nOn2aGdWfVbnZBmyQ56e2v0Fo3hDIS4WS', 'psina', 2);
INSERT INTO railways.users (id, birthDaY, email, name, password, surname, role_id) VALUES (17, '2132-04-12', 'a@a', 'a@a', '$2a$04$99S/6.LPoY6dTAJICCWuRO5c4joAkiHmWdNoZqJueQXXNMf5zmYwq', 'a@a', 2);

INSERT INTO railways.ticketentity (id, ticketDateTime, departureSchedule_id, trainEntity_id, userEntity_id)
VALUES (1, '2018-05-20 17:47:17', 1, 1, 1);
INSERT INTO railways.ticketentity (id, ticketDateTime, departureSchedule_id, trainEntity_id, userEntity_id)
VALUES (2, '2018-05-21 17:43:48', 1, 1, 2);
INSERT INTO railways.ticketentity (id, ticketDateTime, departureSchedule_id, trainEntity_id, userEntity_id)
VALUES (3, '2018-05-20 15:48:07', 2, 1, 3);
INSERT INTO railways.ticketentity (id, ticketDateTime, departureSchedule_id, trainEntity_id, userEntity_id)
VALUES (4, '2018-05-20 11:48:34', 3, 1, 4);