package schedule.controller;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import schedule.controller.model.ScheduleItem;
import schedule.entity.RouteEntity;
import schedule.model.Route;
import schedule.model.Schedule;
import schedule.model.Station;
import schedule.service.api.RouteService;
import schedule.service.api.ScheduleService;
import schedule.service.api.StationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Controller
@RequestMapping(value = "/schedule")
public class ScheduleController extends BaseController {

    private ScheduleService scheduleService;
    private StationService stationService;
    private RouteService routeService;


    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView findSchedule(@RequestParam(name = "stationFrom") String stationFrom, @RequestParam(name = "stationTo") String stationTo, @RequestParam(name = "searchDate") String searchDate, @RequestParam(name = "searchTime") String searchTime) {
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
        if (scheduleItems.isEmpty()) {
            message = "No trains found from " + searchTime + " on " + searchDate + ".";
        } else {
            message = "Search result for departure date " + date;
        }
        modelAndView.addObject("message", message);
        modelAndView.addObject("searchResult", scheduleItems);
        return modelAndView;
    }

    @RequestMapping(value = "/searchTrainOnStation", method = RequestMethod.GET)
    public ModelAndView searchOnStation(Locale locale) {

        ModelAndView modelAndView = new ModelAndView("train-list-by-station");
        modelAndView.addObject("pageTitle",
                getMessage("page.title.search-by-station", locale));

        return modelAndView;
    }

    @RequestMapping(value = "/searchTrainOnStation", method = RequestMethod.POST)
    public ModelAndView searchOnStationResult(@RequestParam(name = "stationFrom") String station,
                                              Locale locale) {

        ModelAndView modelAndView = new ModelAndView("train-list-by-station");
        modelAndView.addObject("pageTitle",
                getMessage("page.title.search-by-station", locale));

        List<Schedule> listOfTrainsByStation = scheduleService.findScheduleByStation(
                stationService.findByName(station));

        List<Schedule> scheduleItems = new ArrayList<>(listOfTrainsByStation);
        String msg = "Results for " + station + " :";

        modelAndView.addObject("scheduleItems", scheduleItems);
        modelAndView.addObject("msg", msg);

        return modelAndView;
    }

    @RequestMapping(value = "/scheduleList")
    public ModelAndView listOfSchedule(Locale locale) {
        List<Schedule> schedule = scheduleService.getSchedule();
        List<Schedule> formatedSchedule = scheduleService.formatShcedule(schedule);

        ModelAndView modelAndView = new ModelAndView("schedule-viewer");
        modelAndView.addObject("schedule", formatedSchedule);
        modelAndView.addObject("pageTitle", getMessage("page.title.schedule.editor", locale));
        modelAndView.addObject("pageTitle", getMessage("page.title.schedule.viewer", locale));

        return modelAndView;
    }

    @RequestMapping(value = "/scheduleList/{schedule.id}")
    public ModelAndView scheduleDetails(Locale locale, @PathVariable(value = "schedule.id") int scheduleId) {
        List<Schedule> schedule = scheduleService.getSchedule();
        List<Schedule> routeAndTrains = scheduleService.formatShcedule(schedule);
        List<Schedule> formatedSchedule = scheduleService.showRouteDetails(schedule, scheduleId);

        ModelAndView modelAndView = new ModelAndView("schedule-viewer");
        modelAndView.addObject("details", formatedSchedule);
        modelAndView.addObject("schedule", routeAndTrains);
        modelAndView.addObject("message", getMessage("message.train.delete.success", locale));

        return modelAndView;
    }

    @RequestMapping(value = "/scheduleList/add")
    public ModelAndView addNewSchedule(Locale locale) {

        ModelAndView modelAndView = new ModelAndView("schedule-constructor");
        modelAndView.addObject("pageTitle", getMessage("page.title.schedule.viewer", locale));

        return modelAndView;
    }

    @RequestMapping(value = "/scheduleList/addRoute", method = RequestMethod.GET)
    public ModelAndView addRoutePage(Locale locale) {
        ModelAndView modelAndView = new ModelAndView("add-route");
        modelAndView.addObject("route", new RouteEntity());
        modelAndView.addObject("pageTitle", getMessage("page.title.add-station", locale));

        return modelAndView;
    }

    @RequestMapping(value = "/scheduleList/addRoute", method = RequestMethod.POST)
    public ModelAndView addRoute(Locale locale, @RequestParam(name = "stationFrom") String stationFrom,
                                 @RequestParam(name = "stationTo") String stationTo) {

        String routeName = stationFrom + "-" + stationTo;
        routeService.addRoute(routeName);
        ModelAndView modelAndView = new ModelAndView("add-route");
        modelAndView.addObject("message", getMessage("message.route.create.success", locale));

        return modelAndView;
    }

    @RequestMapping(value = "/scheduleList/routeList")
    public ModelAndView listOfStationsOnRoute(Locale locale) {
        ModelAndView modelAndView = new ModelAndView("routes-list");

        List<Route> routes = routeService.routesList();
        modelAndView.addObject("routes", routes);
        modelAndView.addObject("pageTitle", getMessage("page.title.stations-list", locale));

        return modelAndView;
    }

    @RequestMapping(value = "/scheduleList/routeList/{route.id}", method = RequestMethod.GET)
    public ModelAndView viewRoute(Locale locale, @PathVariable(value = "route.id") int routeId) {
        List<Station> routeStationsList = routeService.stationsList(routeId);

        ModelAndView modelAndView = new ModelAndView("stations-of-route");
        modelAndView.addObject("stations", routeStationsList);
        return modelAndView;
    }

    @RequestMapping(value = "/scheduleList/routeList/{route.id}", method = RequestMethod.POST)
    public ModelAndView addStationOnRoute(Locale locale, @PathVariable(value = "route.id") int routeId,
                                          @RequestParam(name = "station") String station) {
        routeService.addStationToRoute(routeId, stationService.findByName(station));

        List<Station> routeStationsList = routeService.stationsList(routeId);

        ModelAndView modelAndView = new ModelAndView("stations-of-route");
        modelAndView.addObject("stations", routeStationsList);
        modelAndView.addObject("message", getMessage("message.stations.create.success", locale));

        return modelAndView;
    }

    @RequestMapping(value = "/constructor")
    public ModelAndView scheduleConstructor(Locale locale) {
        List<Schedule> schedule = scheduleService.getSchedule();
        ModelAndView modelAndView = new ModelAndView("schedule-editor");
        modelAndView.addObject("schedule", schedule);
        modelAndView.addObject("pageTitle", getMessage("page.title.schedule.viewer", locale));

        return modelAndView;
    }

    @RequestMapping(value = "/constructor", method = RequestMethod.POST)
    public ModelAndView scheduleItemSave(Locale locale,
                                         @RequestParam(name = "route_picker") String routeName,
                                         @RequestParam(name = "arrival_time") String arrivalTime,
                                         @RequestParam(name = "departure_time") String departureTime,
                                         @RequestParam(name = "stations_list") String station,
                                         @RequestParam(name = "number_in_order") int numberInOrder,
                                         @RequestParam(name = "daily_route") int dailyRoute,
                                         @RequestParam(name = "train_picker") int trainNumber) {
        scheduleService.addSchedule(routeName, arrivalTime, departureTime, station, dailyRoute, numberInOrder, trainNumber);
        List<Schedule> schedule = scheduleService.getSchedule();
        ModelAndView modelAndView = new ModelAndView("schedule-editor");
        modelAndView.addObject("schedule", schedule);
        modelAndView.addObject("pageTitle", getMessage("page.title.schedule.viewer", locale));

        return modelAndView;
    }

    @RequestMapping(value = "/sendMsg")
    public ModelAndView messageToQueue() {
        String message = "message from schedule";
        scheduleService.send(message);
        ModelAndView modelAndView = new ModelAndView("schedule-viewer");

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
