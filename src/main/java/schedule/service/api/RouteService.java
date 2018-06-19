package schedule.service.api;

import schedule.model.Route;
import schedule.model.Station;

import java.util.List;

public interface RouteService {
    /**
     * Return list of Route objects from database
     *
     * @return list of routes from database
     * @see Route
     */
    List<Route> routesList();

    /**
     * Return list of Route objects from database
     *
     * @param stationNames provides  by stations, that must be in the desired route
     * @return list of routes from database with requested @param
     * @see Route
     */
    List<Route> findByStationNames(String... stationNames);

    /**
     * Create new Route in database with represent @param
     *
     * @param routeName provides  by stations, that must be in the desired route
     * @see Route
     */
    void addRoute(String routeName);

    /**
     * Return list of Stations from database that found by @param
     *
     * @param routeId selects the route from which the list of stations are taken
     * @return list of Station from database with requested @param
     * @see Route
     * @see Station
     */
    List<Station> stationsList(int routeId);

    /**
     * Return list of stations names that is a field of {@link Station} from database that found by @param
     *
     * @param routeId selects the route from which the list of stations are taken
     * @return list of stations names
     * @see Route
     * @see Station
     */
    List<String> findStationsByRouteName(int routeId);

    /**
     * Return Route id from database founded by requested @param - routeName, as a field of  {@link Route}
     *
     * @param routeName selects must contain routeName of existed station in database
     * @return Route id
     * @see Route
     */
    int findByName(String routeName);

    void addStationToRoute(int routeId, Station stationName);
}
