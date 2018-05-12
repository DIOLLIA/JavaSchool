package schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import schedule.controller.model.StationSearch;
import schedule.dao.api.RouteDao;
import schedule.model.Schedule;
import schedule.model.Station;
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
    RouteDao routeDao;

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView searchStations(@ModelAttribute("stationSearch") StationSearch stationSearch) {

        scheduleService.addSchedule(new Schedule());
        ModelAndView modelAndView = new ModelAndView("searchResult");
        List<Schedule> schedules = scheduleService.findStations(stationSearch.getArrivalStation(), stationSearch.getDepartureStation());
        modelAndView.addObject("searchResult", schedules);
        Station station = stationService.findByName(stationSearch.getArrivalStation());
        return modelAndView;

    }

    @RequestMapping(value = "/search2", method = RequestMethod.GET)
    public ModelAndView searchStations2() {

        ModelAndView modelAndView = new ModelAndView("schedule");
        modelAndView.addObject("stationSearch", new StationSearch());
        return modelAndView;
    }
}
