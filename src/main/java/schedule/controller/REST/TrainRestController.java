package schedule.controller.REST;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import schedule.controller.BaseController;
import schedule.controller.model.StationsFromTo;
import schedule.controller.model.TrainAndDepTime;
import schedule.model.Route;
import schedule.model.Schedule;
import schedule.service.api.RouteService;
import schedule.service.api.ScheduleService;

import java.util.*;

@RestController
@RequestMapping(value = "/searchForUser")
public class TrainRestController extends BaseController {

    RouteService routeService;
    ScheduleService scheduleService;

    @RequestMapping(value = "/get-train-and-time/", method = RequestMethod.POST)
    String getTrainsListForDepartureStation(@RequestBody StationsFromTo stations) {
        String selectedFromStation = stations.getStationFrom();
        String selectedToStation = stations.getStationTo();

        List<TrainAndDepTime> trainsNumberAndDepTimeList = new ArrayList<>();
        List<Schedule> sortedSchedule = new ArrayList<>();

        List<Route> routes = routeService.findByStationNames(selectedFromStation, selectedToStation);
        //todo: look at scheduleMainSearch and добавь исключение обратных маршрутов
        List<Schedule> schedules = scheduleService.findScheduleByStations(routes, selectedToStation, selectedFromStation);

        for (Schedule scheduleOne : schedules) {
            String stationOneName = scheduleOne.getStationName().getStationName();
            if (stationOneName.equals(selectedToStation)) {
                continue;
            }
            for (Schedule scheduleTwo : schedules) {
                if (scheduleOne.getRouteDailyId() != scheduleTwo.getRouteDailyId() ||
                        scheduleOne.getRouteStationIndex() >= scheduleTwo.getRouteStationIndex()) {
                } else {
                    sortedSchedule.add(scheduleOne);
                }
            }
        }

        Set<Schedule> treeSchedules = new TreeSet(new RouteDailyIdComparator());
        treeSchedules.addAll(sortedSchedule);
        for (Schedule element : treeSchedules) {
            String depTimeString = element.getDepartureTime().toString();
            TrainAndDepTime trainAndDepTime = new TrainAndDepTime();
            trainAndDepTime.setDepartureTime(depTimeString.substring(0, depTimeString.lastIndexOf(':')));
            trainAndDepTime.setTrainNumber(element.getTrainNumber().getNumberOfTrain());
            trainsNumberAndDepTimeList.add(trainAndDepTime);
        }

        return new Gson().toJson(trainsNumberAndDepTimeList);
    }

    private class RouteDailyIdComparator implements Comparator<Schedule> {
        @Override
        public int compare(Schedule o1, Schedule o2) {
            if (o1.getRouteDailyId() == o2.getRouteDailyId()) {
                return 0;
            } else if (o1.getRouteDailyId() > o2.getRouteDailyId()) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    @Autowired
    public void setRouteService(RouteService routeService) {
        this.routeService = routeService;
    }

    @Autowired
    public void setScheduleService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }
}


