package schedule.service.api;

import schedule.model.Route;
import schedule.model.Schedule;
import schedule.model.Station;

import java.util.List;

public interface ScheduleService {
    List<Schedule> findScheduleByStations(List<Route> route, String stationOfDeparture, String stationOfArrival);

    List<Schedule> findScheduleByStation(Station station);

//    void addSchedule(Schedule schedule);

    //    Schedule findScheduleByStationsAndDepartureTime(String stationFrom, String stationTo, LocalTime departureTime);
    List<Schedule> getSchedule();

    List<Schedule> formatShcedule(List<Schedule> schedule);

    List<Schedule> showRouteDetails(List<Schedule> schedule, int scheduleId);

}
