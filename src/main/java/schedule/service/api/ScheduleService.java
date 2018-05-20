package schedule.service.api;

import schedule.model.Route;
import schedule.model.Schedule;
import schedule.model.Station;

import java.util.List;

public interface ScheduleService {
    List<Schedule> findStations(List<Route> route, String stationOfDeparture, String stationOfArrival);

    List<Schedule> findByStation(Station station);

    void addSchedule(Schedule schedule);
}
