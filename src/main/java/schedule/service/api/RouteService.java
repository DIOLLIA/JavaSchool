package schedule.service.api;

import schedule.model.Route;
import schedule.model.Station;

import java.util.List;

public interface RouteService {
    List<Route> routesList();

    List<Route> findByStationNames(String... stationNames);

    void addRoute(String routeName);

    List<Station> stationsList(int routeId);

    List<String> findStationsByRouteName(int routeId);

    int findByName(String routeName);
}
