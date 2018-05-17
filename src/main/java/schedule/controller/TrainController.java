package schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import schedule.model.Train;
import schedule.service.api.TrainService;

import java.util.List;


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
    public ModelAndView addTrain(@ModelAttribute Train train) {

        ModelAndView modelAndView = new ModelAndView("trainsList");
        trainService.addTrain(train);

        List<Train> trains = trainService.getTrains();
        modelAndView.addObject("trains", trains);

        String message = "Train was successfully added.";
        modelAndView.addObject("message", message);

        return modelAndView;
    }

    @RequestMapping(value = "/delete/{train.id}")
    public ModelAndView deleteTrain(@PathVariable(value = "train.id") int trainId) {
        trainService.deleteTrain(trainId);
        String message = "Train was successfully deleted.";
        ModelAndView modelAndView = new ModelAndView("trainsList");
        List<Train> trains = trainService.getTrains();
        modelAndView.addObject("trains", trains);
        modelAndView.addObject("message", message);

        return modelAndView;
    }

    @RequestMapping(value = "/edit/{train.id}", method = RequestMethod.GET)
    public ModelAndView editTrain(@PathVariable(value = "train.id") int trainId) {

        ModelAndView modelAndView = new ModelAndView("editTrain");
        modelAndView.addObject("train", trainService.get(trainId));

//        modelAndView.addObject("trains", trains);

        return modelAndView;
    }

    //todo не работает
    //todo rename method and url to "save"
    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public ModelAndView editTrainSave(@ModelAttribute("train") Train train /*,
            @PathVariable ("id") int trainId*/
            /* @RequestParam ("id") int trainId*/) {
        trainService.editTrain(train);
        String message = "Train was successfully modified.";

        ModelAndView modelAndView = new ModelAndView("trainsList");
        List<Train> trains = trainService.getTrains();

        modelAndView.addObject("trains", trains);
        modelAndView.addObject("message", message);

        return modelAndView;
    }
}
