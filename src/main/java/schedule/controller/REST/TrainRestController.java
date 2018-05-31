package schedule.controller.REST;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import schedule.controller.model.StationsFromTo;
import schedule.model.Route;
import schedule.model.Schedule;
import schedule.service.api.RouteService;
import schedule.service.api.ScheduleService;

import java.util.*;

//@RestController
@Controller

@RequestMapping(value = "/searchForUser")
public class TrainRestController {
    @Autowired
    RouteService routeService;
    @Autowired
    ScheduleService scheduleService;

    @RequestMapping(value = "/get-train-and-time/", method = RequestMethod.POST)
    public @ResponseBody
    String getTrainsListForDepartureStation(@RequestBody StationsFromTo stations) {
        String selectedFromStation = stations.getStationFrom();
        String selectedToStation = stations.getStationTo();

        List<Integer> trainsNumberList = new ArrayList<>();
        List<Route> routes = routeService.findByStationNames(selectedFromStation, selectedToStation);

        List<Schedule> schedules = scheduleService.findScheduleByStations(routes, selectedToStation, selectedFromStation);
        Set<Schedule> treeSchedules = new TreeSet(new RouteDailyIdComparator());
        treeSchedules.addAll(schedules);
        for (Schedule element : treeSchedules) {
            trainsNumberList.add(element.getTrainNumber().getNumberOfTrain());
        }

        return new Gson().toJson(trainsNumberList);
    }

  /*  @RequestMapping(value = "/get-train-and-time/", method = RequestMethod.POST)
    String getTrainsListForDepartureStation2(@RequestBody StationsFromTo stations) {
        String selectedFromStation = stations.getStationFrom();
        String selectedToStation = stations.getStationTo();

        List<TrainAndDepTime> trainsNumberAndDepTimeList = new ArrayList<>();
        List<Route> routes = routeService.findByStationNames(selectedFromStation, selectedToStation);

        List<Schedule> schedules = scheduleService.findScheduleByStations(routes, selectedToStation, selectedFromStation);
        Set<Schedule> treeSchedules = new TreeSet(new RouteDailyIdComparator());
        treeSchedules.addAll(schedules);
        for (Schedule element : treeSchedules) {
            TrainAndDepTime trainAndDepTime = new TrainAndDepTime();
            trainAndDepTime.setDepartureTime(element.getDepartureTime().toString());
            trainAndDepTime.setTrainNumber(element.getTrainNumber().getNumberOfTrain());
            trainsNumberAndDepTimeList.add(trainAndDepTime);
        }

        return new Gson().toJson(trainsNumberAndDepTimeList);
    }*/

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
}


