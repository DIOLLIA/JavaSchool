package shedule.controller;

import shedule.model.Train;
import shedule.service.TrainService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value="/train")
public class TrainController {
    @Autowired
    private TrainService trainService;

    @RequestMapping(value="/list")
    public ModelAndView listOfTrains() {
        ModelAndView modelAndView = new ModelAndView("trainsList");

        List<Train> trains = trainService.getTrains();
        modelAndView.addObject("trains", trains);

        return modelAndView;
    }
}
