package schedule.dao.api;

import org.joda.time.LocalTime;
import schedule.entity.RouteEntity;
import schedule.entity.ScheduleEntity;
import schedule.entity.StationEntity;

import java.util.List;

public interface ScheduleDao extends GeneralCrudDao<ScheduleEntity> {

    List<ScheduleEntity> findByStationsAndRoutes(List<RouteEntity> routes, String stationOfDeparture, String stationOfArrival);

    List<ScheduleEntity> findByStation(StationEntity station);

    ScheduleEntity findScheduleByStationsAndDepartureTime(String stationFrom, String stationTo, LocalTime departureTime);

}
