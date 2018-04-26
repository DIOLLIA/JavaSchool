package schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import schedule.model.Station;
import schedule.service.StationService;

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

        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addStationPage() {
        ModelAndView modelAndView = new ModelAndView("addStation");
        modelAndView.addObject("station", new Station());
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addingStation(@ModelAttribute Station station) {
        ModelAndView modelAndView = new ModelAndView("stationsList");
        stationService.addStation(station);

        String message = "Station was successfully added.";
        modelAndView.addObject("message", message);
        return modelAndView;
    }
}
