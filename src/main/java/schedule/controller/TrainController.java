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

@Controller
@RequestMapping(value = "/train")
public class TrainController extends BaseController {

    private TrainService trainService;
    private UserService userService;

    @RequestMapping(value = "/list")
    public ModelAndView listOfTrains() {
        List<Train> trains = trainService.getTrains();

        ModelAndView modelAndView = new ModelAndView("trains-list");
        modelAndView.addObject("trains", trains);
        modelAndView.addObject("pageTitle", getMessage("page.title.trains-list", DEFAULT_LOCALE));

        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addTrainPage() {
        ModelAndView modelAndView = new ModelAndView("add-train");
        modelAndView.addObject("train", new Train());
        modelAndView.addObject("pageTitle", getMessage("page.title.add-train", DEFAULT_LOCALE));

        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addTrain(@ModelAttribute Train train) {
        trainService.addTrain(train);
        List<Train> trains = trainService.getTrains();

        ModelAndView modelAndView = new ModelAndView("trains-list");
        modelAndView.addObject("trains", trains);
        modelAndView.addObject("message", getMessage("message.train.create.success", DEFAULT_LOCALE));

        return modelAndView;
    }

    @RequestMapping(value = "/delete/{train.id}")
    public ModelAndView deleteTrain(@PathVariable(value = "train.id") int trainId) {
        trainService.deleteTrain(trainId);
        List<Train> trains = trainService.getTrains();

        ModelAndView modelAndView = new ModelAndView("trains-list");
        modelAndView.addObject("trains", trains);
        modelAndView.addObject("message", getMessage("message.train.delete.success", DEFAULT_LOCALE));

        return modelAndView;
    }

    @RequestMapping(value = "/edit/{train.id}", method = RequestMethod.GET)
    public ModelAndView editTrain(@PathVariable(value = "train.id") int trainId) {

        ModelAndView modelAndView = new ModelAndView("edit-train");
        modelAndView.addObject("train", trainService.get(trainId));
        modelAndView.addObject("pageTitle", getMessage("page.title.edit-train", DEFAULT_LOCALE));

        return modelAndView;
    }

    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public ModelAndView editTrainSave(@ModelAttribute("train") Train train) {
        trainService.editTrain(train);
        List<Train> trains = trainService.getTrains();

        ModelAndView modelAndView = new ModelAndView("trains-list");
        modelAndView.addObject("trains", trains);
        modelAndView.addObject("message", getMessage("message.train.modify.success", DEFAULT_LOCALE));

        return modelAndView;
    }

    @RequestMapping(value = "/schedule/{train.id}", method = RequestMethod.GET)
    public ModelAndView trainSchedule(@PathVariable(value = "train.id") int trainId) {
        List<Schedule> schedules = trainService.getScheduleByTrainId(trainId);

        List<ScheduleItem> trainRoutes = trainService.createTrainRoutesList(schedules);
        Train train = trainService.get(trainId);
        int trainNumber = train.getNumberOfTrain();

        ModelAndView modelAndView = new ModelAndView("train-routes-list");
        modelAndView.addObject("pageTitle", getMessage("page.title.train-routes", DEFAULT_LOCALE));
        modelAndView.addObject("train", train);
        modelAndView.addObject("scheduleByTrainId", trainRoutes);
        modelAndView.addObject("msg", getMessage("message.train.routes-for-train",
                DEFAULT_LOCALE, String.valueOf(trainNumber)));

        return modelAndView;
    }

    @RequestMapping(value = "/schedule/{train.id}/passengers/{route.id}", method = RequestMethod.GET)
    public ModelAndView showPassengersOnTrain(@PathVariable(name = "train.id") String trainId,
                                              @PathVariable(name = "route.id") String routeIdAsString,
                                              @RequestParam(name = "date") String dateAsString,
                                              @RequestParam(name = "startTime") String startTimeAsString) {

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
        modelAndView.addObject("msg", getMessage("message.train.passengers-list", DEFAULT_LOCALE,
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
