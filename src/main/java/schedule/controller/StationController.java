package schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import schedule.entity.StationEntity;
import schedule.model.Station;
import schedule.service.api.StationService;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping(value = "/station")
public class StationController extends BaseController {

    private StationService stationService;

    @RequestMapping(value = "/list")
    public ModelAndView listOfStations(Locale locale) {
        ModelAndView modelAndView = new ModelAndView("stations-list");

        List<Station> stations = stationService.getStations();
        modelAndView.addObject("stations", stations);
        modelAndView.addObject("pageTitle", getMessage("page.title.stations-list", locale));

        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addStationPage(Locale locale) {
        ModelAndView modelAndView = new ModelAndView("add-station");
        modelAndView.addObject("station", new StationEntity());
        modelAndView.addObject("pageTitle", getMessage("page.title.add-station", locale));

        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addStation(@ModelAttribute Station station,
                                   Locale locale) {
        stationService.addStation(station);

        List<Station> stations = stationService.getStations();

        ModelAndView modelAndView = new ModelAndView("stations-list");
        modelAndView.addObject("stations", stations);
        modelAndView.addObject("message", getMessage("message.stations.create.success", locale));

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
        stationService.deleteStation(stationId);

        List<Station> stations = stationService.getStations();

        ModelAndView modelAndView = new ModelAndView("stations-list");
        modelAndView.addObject("stations", stations);
        modelAndView.addObject("message", getMessage("message.stations.delete.success", locale));

        return modelAndView;
    }

    @Autowired
    public void setStationService(StationService stationService) {
        this.stationService = stationService;
    }
}
