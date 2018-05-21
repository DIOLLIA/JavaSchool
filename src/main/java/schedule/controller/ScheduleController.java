package schedule.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import schedule.controller.model.ScheduleItem;
import schedule.model.Route;
import schedule.model.Schedule;
import schedule.service.api.RouteService;
import schedule.service.api.ScheduleService;
import schedule.service.api.StationService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;
    @Autowired
    StationService stationService;
    @Autowired
    RouteService routeService;

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView findSchedule(@RequestParam(name = "stationFrom") String stationFrom, @RequestParam(name = "stationTo") String stationTo) {
        List<Route> routes = routeService.findByStationNames(stationFrom, stationTo);

        ModelAndView modelAndView = new ModelAndView("searchResult");
        List<Schedule> schedules = scheduleService.findStations(routes, stationTo, stationFrom);

        List<ScheduleItem> scheduleItems = new ArrayList<>();
        for (Schedule scheduleOne : schedules) {
            String stationOneName = scheduleOne.getStationName().getStationName();
            if (stationOneName.equals(stationTo)) {
                continue;
            }
            for (Schedule scheduleTwo : schedules) {
                String stationTwoName = scheduleTwo.getStationName().getStationName();
                if (scheduleOne.getRouteDailyId() != scheduleTwo.getRouteDailyId() ||
                        scheduleOne.getRouteStationIndex() >= scheduleTwo.getRouteStationIndex()) {
                    continue;
                }
                ScheduleItem scheduleItem = new ScheduleItem();
                scheduleItem.setDepartureTime(scheduleOne.getDepartureTime());
                scheduleItem.setArrivalTime(scheduleTwo.getArrivalTime());
                scheduleItem.setStationOfDeparture(stationOneName);
                scheduleItem.setStationOfArrival(stationTwoName);
                scheduleItem.setTrainNumber(scheduleOne.getTrainNumber().getNumberOfTrain());
                scheduleItems.add(scheduleItem);
            }
        }
        modelAndView.addObject("searchResult", scheduleItems);
        return modelAndView;
    }

    @GetMapping(value = "/get-stations/")
    @ResponseBody
    public String getStationsNames() {
        List<String> stationsNames = stationService.getStationsNames();

        return new Gson().toJson(stationsNames);
    }

    @GetMapping(value = "/get-stations/{selectedFromStation}")
    @ResponseBody
    public String getStationsNamesTest(@PathVariable(value = "selectedFromStation") String selectedFromStation) {
        List<String> stationsNames = stationService.getStationsNames();
        stationsNames.remove(selectedFromStation);
        return new Gson().toJson(stationsNames);
    }

    @RequestMapping(value = "/searchTrainOnStation", method = RequestMethod.GET)
    public ModelAndView searchOnStation() {

        ModelAndView modelAndView = new ModelAndView("trainListByStation");
        modelAndView.addObject("pageTitle", "On station");

        return modelAndView;
    }

    @RequestMapping(value = "/searchTrainOnStation", method = RequestMethod.POST)
    public ModelAndView searchOnStationResult(@RequestParam(name = "stationFrom") String station) {

        ModelAndView modelAndView = new ModelAndView("trainListByStation");

        List<Schedule> listOfTrainsByStation = scheduleService.findByStation(stationService.findByName(station));

        List<Schedule> scheduleItems = new ArrayList<>(listOfTrainsByStation);
        String msg = "Results for " + station + " :";

        modelAndView.addObject("scheduleItems", scheduleItems);
        modelAndView.addObject("msg", msg);
        return modelAndView;
    }
}
