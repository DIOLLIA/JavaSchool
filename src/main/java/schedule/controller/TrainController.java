package schedule.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import schedule.model.Train;
import schedule.service.TrainService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/train")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @RequestMapping(value = "/list")
    public ModelAndView listOfTrains() {
        ModelAndView modelAndView = new ModelAndView("trainsList");

        List<Train> trains = trainService.getTrains();
        modelAndView.addObject("trains", trains);

        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addTrainPage() {
        ModelAndView modelAndView = new ModelAndView("addTrain");
        modelAndView.addObject("train", new Train());
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addingTrain(@ModelAttribute Train train) {

        ModelAndView modelAndView = new ModelAndView("trainsList");
        trainService.addTrain(train);

        List<Train> trains = trainService.getTrains();
        modelAndView.addObject("trains", trains);

        String message = "Train was successfully added.";
        modelAndView.addObject("message", message);

        return modelAndView;
    }
}
