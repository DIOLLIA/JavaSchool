package schedule.service.api;

import schedule.model.Route;
import schedule.model.Schedule;

import java.util.List;

public interface ScheduleService {
    List<Schedule> findStations(List<Route> route, String stationOfDeparture, String stationOfArrival);

    void addSchedule(Schedule schedule);
}
