package schedule.controller;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import schedule.controller.model.ScheduleItem;
import schedule.model.Schedule;
import schedule.model.Train;
import schedule.model.User;
import schedule.service.api.TrainService;
import schedule.service.api.UserService;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping(value = "/train")
public class TrainController extends BaseController {

    private TrainService trainService;
    private UserService userService;

    @RequestMapping(value = "/list")
    public ModelAndView listOfTrains(Locale locale) {
        List<Train> trains = trainService.getTrains();

        ModelAndView modelAndView = new ModelAndView("trains-list");
        modelAndView.addObject("trains", trains);
        modelAndView.addObject("pageTitle", getMessage("page.title.trains-list", locale));

        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addTrainPage(Locale locale) {
        ModelAndView modelAndView = new ModelAndView("add-train");
        modelAndView.addObject("train", new Train());
        modelAndView.addObject("pageTitle", getMessage("page.title.add-train", locale));

        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addTrain(@ModelAttribute Train train, Locale locale) {
        ModelAndView modelAndView = new ModelAndView("trains-list");

        if (trainService.findByNumber(train.getNumberOfTrain()) == null) {

            trainService.addTrain(train);


            modelAndView.addObject("message", getMessage("message.train.create.success", locale));
        } else {
            modelAndView.addObject("message", getMessage("message.train.create.abort", locale));
        }
        List<Train> trains = trainService.getTrains();
        modelAndView.addObject("trains", trains);
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{train.id}")
    public ModelAndView deleteTrain(@PathVariable(value = "train.id") int trainId, Locale locale) {
        trainService.deleteTrain(trainId);
        List<Train> trains = trainService.getTrains();

        ModelAndView modelAndView = new ModelAndView("trains-list");
        modelAndView.addObject("trains", trains);
        modelAndView.addObject("message", getMessage("message.train.delete.success", locale));

        return modelAndView;
    }

    @RequestMapping(value = "/edit/{train.id}", method = RequestMethod.GET)
    public ModelAndView editTrain(@PathVariable(value = "train.id") int trainId, Locale locale) {

        ModelAndView modelAndView = new ModelAndView("edit-train");
        modelAndView.addObject("train", trainService.get(trainId));
        modelAndView.addObject("pageTitle", getMessage("page.title.edit-train", locale));

        return modelAndView;
    }

    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public ModelAndView editTrainSave(@ModelAttribute("train") Train train, Locale locale) {
        trainService.editTrain(train);
        List<Train> trains = trainService.getTrains();

        ModelAndView modelAndView = new ModelAndView("trains-list");
        modelAndView.addObject("trains", trains);
        modelAndView.addObject("message", getMessage("message.train.modify.success", locale));

        return modelAndView;
    }

    @RequestMapping(value = "/schedule/{train.id}", method = RequestMethod.GET)
    public ModelAndView trainSchedule(@PathVariable(value = "train.id") int trainId, Locale locale) {
        List<Schedule> schedules = trainService.getScheduleByTrainId(trainId);

        List<ScheduleItem> trainRoutes = trainService.createTrainRoutesList(schedules);
        Train train = trainService.get(trainId);
        int trainNumber = train.getNumberOfTrain();

        ModelAndView modelAndView = new ModelAndView("train-routes-list");
        modelAndView.addObject("pageTitle", getMessage("page.title.train-routes", locale));
        modelAndView.addObject("train", train);
        modelAndView.addObject("scheduleByTrainId", trainRoutes);
        modelAndView.addObject("msg", getMessage("message.train.routes-for-train",
                locale, String.valueOf(trainNumber)));

        return modelAndView;
    }

    @RequestMapping(value = "/schedule/{train.id}/passengers/{route.id}", method = RequestMethod.GET)
    public ModelAndView showPassengersOnTrain(@PathVariable(name = "train.id") String trainId,
                                              @PathVariable(name = "route.id") String routeIdAsString,
                                              @RequestParam(name = "date") String dateAsString,
                                              @RequestParam(name = "startTime") String startTimeAsString,
                                              Locale locale) {

        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("MM/dd/yyyy");
        LocalDate date = LocalDate.parse(dateAsString, dateFormatter);
        LocalTime time = LocalTime.parse(startTimeAsString);
        LocalDateTime startDateTime = date.toLocalDateTime(time);

        int routeId = Integer.parseInt(routeIdAsString);
        int trainNumber = trainService.get(Integer.parseInt(trainId))
                .getNumberOfTrain();
        List<User> passengersOnRoute = userService.findPassengersOfTrain(routeId, startDateTime);

        ModelAndView modelAndView = new ModelAndView("passengers-on-daily-route")
                .addObject("passengersOnRoute", passengersOnRoute);
        modelAndView.addObject("msg", getMessage("message.train.passengers-list", locale,
                String.valueOf(trainNumber), dateAsString));

        return modelAndView;

    }

    @Autowired
    public void setTrainService(TrainService trainService) {
        this.trainService = trainService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
