package schedule.dao.api;

import schedule.entity.RouteEntity;
import schedule.entity.StationEntity;

import java.util.List;

public interface RouteDao extends GeneralCrudDao<RouteEntity> {
    List<RouteEntity> routes();

    List<RouteEntity> findByStationNames(String... stationName);

    List<StationEntity> stationsOnRoute(int routeId);

    int findByName(String routeName);

    RouteEntity routeById (int id);
}

