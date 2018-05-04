package schedule.dao;

import schedule.model.Route;
import schedule.model.Schedule;
import schedule.model.Station;

import java.util.List;

public interface ScheduleDao {
    List<Schedule> findByStationsAndRoutes (List<Route> routes, Station stationOfDeparture, Station stationOfArrival);
}
