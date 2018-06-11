package schedule.dao.api;

import schedule.entity.RouteEntity;
import schedule.model.Station;

import java.util.List;

public interface RouteDao extends GeneralCrudDao<RouteEntity> {
    List<RouteEntity> routes();

    List<RouteEntity> findByStationNames(String... stationName);

    List<Station> stationsOnRoute(int routeId);
}

