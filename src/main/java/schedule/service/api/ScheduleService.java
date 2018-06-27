package schedule.service.api;

import schedule.controller.model.ScheduleToSend;
import schedule.model.Route;
import schedule.model.Schedule;
import schedule.model.Station;

import java.util.List;

/**
 * @author Rudkov Andrey
 * <p>
 * ScheduleService class basically interacts with {@link Schedule} objects.
 * Class methods can: search schedules by route name and two request stations,
 * search schedules that contains request station,
 * get schedules - it will return all objects as List,
 * search schedules with same routeDailyId,
 * add schedule with all received params from method params,
 * send all schedules from database to JMS,
 * transform Schedule to more simple object (String and int fields only),
 * return list of schedules with requested trainId
 */
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

    /**
     * search schedules that contains request
     *
     * @param station
     * @return list of schedules with requested station
     */
    List<Schedule> findScheduleByStation(Station station);

    /**
     * it will return all schedules from database
     *
     * @return all {@link Schedule} objects as List
     */
    List<Schedule> getSchedule();

    /**
     * takes request schedule {@see routeDailyId} {@link schedule.entity.ScheduleEntity}
     * routeDailyId param and chooses another schedules by it. It create list with
     * same routeDailyId field of ScheduleEntity
     *
     * @param schedule
     * @return list of Schedule with same routeDailyId
     */
    List<Schedule> formatSchedule(List<Schedule> schedule);

    /**
     * Filter schedules with request params. Returnable will contains
     * all schedule with same getRouteDailyId as contains in
     *
     * @param scheduleId
     * @param schedule
     * @return list of Schedule with same routeDailyId
     */
    List<Schedule> showRouteDetails(List<Schedule> schedule, int scheduleId);

    /**
     * Method add schedule with all received params from method params
     */
    void addSchedule(String routeName, String arrivalTime, String departureTime, String station, int dailyRoute, int numberInOrder, int trainNumber);

    /**
     * method send all {@link Schedule} from database to JMS
     *
     * @param scheduleToSends
     */
    void sendAll(List<ScheduleToSend> scheduleToSends);

    /**
     * Method transform Schedule to more simple object (String and int fields only)
     * it used for sending schedules to JMS
     *
     * @param schedules
     * @return list of {@link ScheduleToSend} object
     */
    List<ScheduleToSend> transform(List<Schedule> schedules);

    /**
     * Search schedule with
     *
     * @param trainId
     * @return list of schedules with requested trainId
     */
    List<Schedule> findTrainById(int trainId);
}
