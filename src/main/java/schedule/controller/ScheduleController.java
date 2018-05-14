package schedule.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import schedule.controller.model.StationSearch;
import schedule.model.Route;
import schedule.model.Schedule;
import schedule.model.Station;
import schedule.service.api.RouteService;
import schedule.service.api.ScheduleService;
import schedule.service.api.StationService;

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
        modelAndView.addObject("searchResult", schedules);
        Station station = stationService.findByName(arrivalStation);
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
    public String getStationsNamesTest(@PathVariable(value="selectedFromStation") String selectedFromStation) {
        List<String> stationsNames = stationService.getStationsNames();
        stationsNames.remove(selectedFromStation);
        return new Gson().toJson(stationsNames);
    }
}
