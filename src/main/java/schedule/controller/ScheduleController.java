package schedule.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import schedule.controller.model.ScheduleItem;
import schedule.controller.model.StationSearch;
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
    public ModelAndView findSchedule(@ModelAttribute("stationSearch") StationSearch stationSearch) {
        String arrivalStation = stationSearch.getArrivalStation();
        String departureStation = stationSearch.getDepartureStation();
        List<Route> routes = routeService.findByStationNames(arrivalStation, departureStation);

        // scheduleService.addSchedule(new Schedule());
        ModelAndView modelAndView = new ModelAndView("searchResult");
        List<Schedule> schedules = scheduleService.findStations(routes, arrivalStation, departureStation);

        List<ScheduleItem> scheduleItems = new ArrayList<>();
        for (Schedule scheduleOne : schedules) {
            String stationOneName = scheduleOne.getStationName().getStationName();
            if (stationOneName.equals(departureStation)) {
                continue;
            }
            for (Schedule scheduleTwo : schedules) {
                String stationTwoName = scheduleTwo.getStationName().getStationName();
                if (scheduleOne.getRouteDailyId() != scheduleTwo.getRouteDailyId() ||
                        !stationTwoName.equals(departureStation)) {
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

    @RequestMapping(value = "/search2", method = RequestMethod.GET)
    public ModelAndView searchStations2() {

        ModelAndView modelAndView = new ModelAndView("schedule");
        modelAndView.addObject("stationSearch", new StationSearch());
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

        ModelAndView modelAndView = new ModelAndView("searchTrainOnStation");
       // modelAndView.addObject("stationSearch", new StationSearch());
        return modelAndView;
    }
}
