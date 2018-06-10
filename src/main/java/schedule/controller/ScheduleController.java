package schedule.controller;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import schedule.controller.model.ScheduleItem;
import schedule.entity.RouteEntity;
import schedule.model.Route;
import schedule.model.Schedule;
import schedule.service.api.RouteService;
import schedule.service.api.ScheduleService;
import schedule.service.api.StationService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/schedule")
public class ScheduleController extends BaseController {

    private ScheduleService scheduleService;
    private StationService stationService;
    private RouteService routeService;


    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView findSchedule(@RequestParam(name = "stationFrom") String stationFrom, @RequestParam(name = "stationTo") String stationTo, @RequestParam(name = "searchDate") String searchDate, @RequestParam(name = "searchTime") String searchTime)
    {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("MM/dd/yyyy");
        LocalDate date = LocalDate.parse(searchDate, dateFormatter);

        LocalTime requestTime = LocalTime.parse(searchTime);
        String message;
        List<Route> routes = routeService.findByStationNames(stationFrom, stationTo);

        ModelAndView modelAndView = new ModelAndView("home");
        List<Schedule> schedules = scheduleService.findScheduleByStations(routes, stationTo, stationFrom);

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
                if (!requestTime.isBefore(scheduleOne.getDepartureTime())) {
                    break;
                } else {
                    ScheduleItem scheduleItem = new ScheduleItem();
                    scheduleItem.setDepartureTime(scheduleOne.getDepartureTime());
                    scheduleItem.setArrivalTime(scheduleTwo.getArrivalTime());

                    scheduleItem.setStationOfDeparture(stationOneName);
                    scheduleItem.setStationOfArrival(stationTwoName);
                    scheduleItem.setTrainNumber(scheduleOne.getTrainNumber().getNumberOfTrain());
                    scheduleItems.add(scheduleItem);
                }
            }
        }
        if (scheduleItems.isEmpty()){
         message = "No trains found from " + searchTime + " on " + searchDate+".";
        }
        else {
            message = "Search result for departure date " + date;
        }
        modelAndView.addObject("message", message);
        modelAndView.addObject("searchResult", scheduleItems);
        return modelAndView;
    }

    @RequestMapping(value = "/searchTrainOnStation", method = RequestMethod.GET)
    public ModelAndView searchOnStation() {

        ModelAndView modelAndView = new ModelAndView("train-list-by-station");
        modelAndView.addObject("pageTitle",
                getMessage("page.title.search-by-station", DEFAULT_LOCALE));

        return modelAndView;
    }

    @RequestMapping(value = "/searchTrainOnStation", method = RequestMethod.POST)
    public ModelAndView searchOnStationResult(@RequestParam(name = "stationFrom") String station) {

        ModelAndView modelAndView = new ModelAndView("train-list-by-station");
        modelAndView.addObject("pageTitle",
                getMessage("page.title.search-by-station", DEFAULT_LOCALE));

        List<Schedule> listOfTrainsByStation = scheduleService.findScheduleByStation(
                stationService.findByName(station));

        List<Schedule> scheduleItems = new ArrayList<>(listOfTrainsByStation);
        String msg = "Results for " + station + " :";

        modelAndView.addObject("scheduleItems", scheduleItems);
        modelAndView.addObject("msg", msg);

        return modelAndView;
    }

    @RequestMapping(value = "/scheduleList")
    public ModelAndView listOfSchedule() {
        List<Schedule> schedule = scheduleService.getSchedule();

        ModelAndView modelAndView = new ModelAndView("schedule-editor");
        modelAndView.addObject("schedule", schedule);
        modelAndView.addObject("pageTitle", getMessage("page.title.schedule.editor", DEFAULT_LOCALE));

        return modelAndView;
    }

    @RequestMapping(value = "/scheduleList/addRoute", method = RequestMethod.GET)
    public ModelAndView addRoutePage() {
        ModelAndView modelAndView = new ModelAndView("add-route");
        modelAndView.addObject("route", new RouteEntity());
        modelAndView.addObject("pageTitle", getMessage("page.title.add-station", DEFAULT_LOCALE));

        return modelAndView;
    }

    @RequestMapping(value = "/scheduleList/addRoute", method = RequestMethod.POST)
    public ModelAndView addRoute(@RequestParam (name = "stationFrom") String stationFrom,
                                 @RequestParam (name = "stationTo") String stationTo) {

        String routeName = stationFrom + "-" + stationTo;
        routeService.addRoute(routeName);
        ModelAndView modelAndView = new ModelAndView("add-route");
        modelAndView.addObject("message", getMessage("message.route.create.success", DEFAULT_LOCALE));

        return modelAndView;
    }

    @Autowired
    public void setScheduleService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @Autowired
    public void setStationService(StationService stationService) {
        this.stationService = stationService;
    }

    @Autowired
    public void setRouteService(RouteService routeService) {
        this.routeService = routeService;
    }
}
