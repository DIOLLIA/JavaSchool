package schedule.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import schedule.controller.model.ScheduleToSend;
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

    private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);

    private ScheduleService scheduleService;
    private StationService stationService;
    private RouteService routeService;


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

        return modelAndView;
    }

    //TODO проверить юзабилити метода
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
        Integer routeId = routeService.findByName(routeName);
        if (routeId!=null) {
            logger.debug("Route with id {} and route name {} found ", routeId, routeName);
            ModelAndView modelAndView = new ModelAndView("add-route");
            modelAndView.addObject("message", getMessage("message.route.create.fail", locale));
            return modelAndView;
        } else {
            logger.debug("Route with id {} and route name {} NOT found ", routeId, routeName);
            ModelAndView modelAndView = new ModelAndView("route-list");
            routeService.addRoute(routeName);
            modelAndView.addObject("message", getMessage("message.route.create.success", locale));

            return modelAndView;
        }
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
        ModelAndView modelAndView = new ModelAndView("schedule-constructor");
        modelAndView.addObject("schedule", schedule);
        modelAndView.addObject("pageTitle", getMessage("page.title.schedule.viewer", locale));

        return modelAndView;
    }

    /**
     * method get from user (jsp page) values
     *
     * @param locale
     * @param routeName
     * @param arrivalTime
     * @param departureTime
     * @param station
     * @param numberInOrder
     * @param dailyRoute
     * @param trainNumber   and sendt it to {@link ScheduleService}, also create new instance of
     *                      {@link ScheduleToSend}, add it to list and transfer it to JMS.
     * @return list of schedule with new item
     */
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
        List<ScheduleToSend> list = new ArrayList<>();
        list.add(new ScheduleToSend(station, departureTime, arrivalTime, trainNumber, dailyRoute, numberInOrder));
        scheduleService.sendAll(list);
        List<Schedule> schedule = scheduleService.getSchedule();
        ModelAndView modelAndView = new ModelAndView("schedule-constructor");
        modelAndView.addObject("schedule", schedule);
        modelAndView.addObject("pageTitle", getMessage("page.title.schedule.viewer", locale));

        return modelAndView;
    }

    //todo переделать чтоб не перезагружить страницу
    @RequestMapping(value = "/sendMsg")
    public ModelAndView messageToQueue() {
        List<ScheduleToSend> scheduleToSends = scheduleService.transform(scheduleService.getSchedule());
        scheduleService.sendAll(scheduleToSends);
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
