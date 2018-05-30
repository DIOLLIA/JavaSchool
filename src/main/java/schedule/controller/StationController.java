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

@Controller
@RequestMapping(value = "/station")
public class StationController {

    @Autowired
    private StationService stationService;

    @RequestMapping(value = "/list")
    public ModelAndView listOfStations() {
        ModelAndView modelAndView = new ModelAndView("stationsList");

        List<Station> stations = stationService.getStations();
        modelAndView.addObject("stations", stations);
        modelAndView.addObject("pageTitle", "Stations list");


        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addStationPage() {
        ModelAndView modelAndView = new ModelAndView("addStation");
        modelAndView.addObject("station", new StationEntity());
        modelAndView.addObject("pageTitle", "Add station");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addStation(@ModelAttribute Station station) {
        ModelAndView modelAndView = new ModelAndView("stationsList");
        stationService.addStation(station);

        List<Station> stations = stationService.getStations();
        modelAndView.addObject("stations", stations);

        String message = "Station was successfully added.";
        modelAndView.addObject("message", message);

        return modelAndView;
    }

    @RequestMapping(value = "/edit/{station.id}", method = RequestMethod.GET)
    public ModelAndView editStation(@PathVariable(value = "station.id") int stationId) {

        ModelAndView modelAndView = new ModelAndView("editStation");
        modelAndView.addObject("station", stationService.getStation(stationId));
        modelAndView.addObject("pageTitle", "Edit train");

        return modelAndView;
    }

    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public ModelAndView editTrainSave(@ModelAttribute("station") Station station) {
        stationService.editStation(station);
        String message = "Station was successfully modified.";

        ModelAndView modelAndView = new ModelAndView("stationsList");
        List<Station> stations = stationService.getStations();

        modelAndView.addObject("stations", stations);
        modelAndView.addObject("message", message);

        return modelAndView;
    }

    @RequestMapping(value = "/delete/{station.id}")
    public ModelAndView deleteStation(@PathVariable(value = "station.id") int stationId) {
        stationService.deleteStation(stationId);
        String message = "Station was successfully deleted.";
        ModelAndView modelAndView = new ModelAndView("stationsList");
        List<Station> stations = stationService.getStations();
        modelAndView.addObject("stations", stations);
        modelAndView.addObject("message", message);

        return modelAndView;
    }
}
