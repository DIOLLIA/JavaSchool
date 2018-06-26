package schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import schedule.model.Route;
import schedule.model.Schedule;
import schedule.model.Station;
import schedule.service.api.RouteService;
import schedule.service.api.ScheduleService;
import schedule.service.api.StationService;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping(value = "/station")
public class StationController extends BaseController {

    private StationService stationService;
    private ScheduleService scheduleService;
    private RouteService routeService;

    @RequestMapping(value = "/list")
    public ModelAndView listOfStations(Locale locale) {
        ModelAndView modelAndView = new ModelAndView("stations-list");

        List<Station> stations = stationService.getStations();
        modelAndView.addObject("stations", stations);
        modelAndView.addObject("pageTitle", getMessage("page.title.stations-list", locale));

        return modelAndView;
    }

    @RequestMapping(value = "/edit/{station.id}", method = RequestMethod.GET)
    public ModelAndView editStation(@PathVariable(value = "station.id") int stationId,
                                    Locale locale) {
        ModelAndView modelAndView = new ModelAndView("edit-station");
        modelAndView.addObject("station", stationService.getStation(stationId));
        modelAndView.addObject("pageTitle", getMessage("page.title.edit-station", locale));

        return modelAndView;
    }

    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public ModelAndView editTrainSave(@ModelAttribute("station") Station station,
                                      Locale locale) {
        stationService.editStation(station);

        List<Station> stations = stationService.getStations();

        ModelAndView modelAndView = new ModelAndView("stations-list");
        modelAndView.addObject("stations", stations);
        modelAndView.addObject("message", getMessage("message.stations.modify.success", locale));

        return modelAndView;
    }

    @RequestMapping(value = "/delete/{station.id}")
    public ModelAndView deleteStation(@PathVariable(value = "station.id") int stationId,
                                      Locale locale) {
        ModelAndView modelAndView = new ModelAndView("stations-list");
        List<Station> stations;
        List<Schedule> resultScheduleList;
        List<Route> resultRouteList;

        resultScheduleList = scheduleService.findScheduleByStation(stationService.getStation(stationId));
        resultRouteList= routeService.findByStationNames(stationService.getStation(stationId).getStationName());
        if (resultScheduleList.isEmpty()&&resultRouteList.isEmpty()) {
            stationService.deleteStation(stationId);
            modelAndView.addObject("message", getMessage("message.stations.delete.success", locale));
            stations = stationService.getStations();
            modelAndView.addObject("stations", stations);

            return modelAndView;
        }

        stations = stationService.getStations();
        modelAndView.addObject("message", getMessage("message.stations.delete.fail", locale));
        modelAndView.addObject("stations", stations);

        return modelAndView;
    }

    @Autowired
    public void setStationService(StationService stationService) {
        this.stationService = stationService;
    }

    @Autowired
    public void setScheduleService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }
    @Autowired

    public void setRouteService(RouteService routeService) {
        this.routeService = routeService;
    }
}
