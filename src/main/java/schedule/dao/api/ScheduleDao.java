package schedule.dao.api;

import schedule.entity.RouteEntity;
import schedule.entity.ScheduleEntity;

import java.util.List;

public interface ScheduleDao {
    List<ScheduleEntity> findByStationsAndRoutes(List<RouteEntity> routes, String stationOfDeparture, String stationOfArrival);
}
