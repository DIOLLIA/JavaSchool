package schedule.service.api;

import schedule.model.Route;

import java.util.List;

public interface RouteService {
    List<Route> routesList();

    List<Route> findByStationNames(String... stationNames);

    void addRoute(String routeName);
}
