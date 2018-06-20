package schedule.service.api;

import schedule.controller.model.ScheduleToSend;
import schedule.model.Route;
import schedule.model.Schedule;
import schedule.model.Station;

import java.util.List;

public interface ScheduleService {
    /**
     * Return Schedule list that contains both of stations @param which in turn contains in route @param
     *
     * @param route              from {@link schedule.service.impl.RouteServiceImpl#findByStationNames(String...)} )
     * @param stationOfDeparture
     * @param stationOfArrival
     * @return
     */
    List<Schedule> findScheduleByStations(List<Route> route, String stationOfDeparture, String stationOfArrival);

    List<Schedule> findScheduleByStation(Station station);

    List<Schedule> getSchedule();

    List<Schedule> formatShcedule(List<Schedule> schedule);

    List<Schedule> showRouteDetails(List<Schedule> schedule, int scheduleId);

    void addSchedule(String routeName, String arrivalTime, String departureTime, String station, int dailyRoute, int numberInOrder, int trainNumber);

    void sendAll(List<ScheduleToSend> scheduleToSends);

    List<ScheduleToSend> transform(List<Schedule> schedules);

    List<Schedule> findTrainById(int trainId);
};
