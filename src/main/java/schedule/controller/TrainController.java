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
import schedule.service.api.ScheduleService;
import schedule.service.api.TrainService;
import schedule.service.api.UserService;

import java.util.List;
import java.util.Locale;

/**
 * controller class
 */
@Controller
@RequestMapping(value = "/train")
public class TrainController extends BaseController {

    private TrainService trainService;
    private UserService userService;
    private ScheduleService scheduleService;

    /**
     * @param locale
     * @return full trains list of {@link Train}
     */
    @RequestMapping(value = "/list")
    public ModelAndView listOfTrains(Locale locale) {
        List<Train> trains = trainService.getTrains();

        ModelAndView modelAndView = new ModelAndView("trains-list");
        modelAndView.addObject("trains", trains);
        modelAndView.addObject("pageTitle", getMessage("page.title.trains-list", locale));

        return modelAndView;
    }

    /**
     * view page for adding Train
     *
     * @param locale
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addTrainPage(Locale locale) {
        ModelAndView modelAndView = new ModelAndView("add-train");
        modelAndView.addObject("train", new Train());
        modelAndView.addObject("pageTitle", getMessage("page.title.add-train", locale));

        return modelAndView;
    }

    /**
     * method create new {@link Train} if train with that number not exist in DataBase
     *
     * @param train
     * @param locale
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addTrain(@ModelAttribute Train train, Locale locale) {
        ModelAndView modelAndView;

        if (trainService.findByNumber(train.getNumberOfTrain()) == null) {
            modelAndView = new ModelAndView("trains-list");
            trainService.addTrain(train);

            modelAndView.addObject("message", getMessage("message.train.create.success", locale));
        } else {
            modelAndView = new ModelAndView("add-train");
            modelAndView.addObject("message", getMessage("message.train.create.abort", locale));
        }
        List<Train> trains = trainService.getTrains();
        modelAndView.addObject("trains", trains);
        return modelAndView;
    }

    /**
     * method delete train by id from DataBase if it not include in existing schedule
     *
     * @param trainId
     * @param locale
     * @return trains-list.jsp with deleted train if check success
     */
    @RequestMapping(value = "/delete/{train.id}")
    public ModelAndView deleteTrain(@PathVariable(value = "train.id") int trainId, Locale locale) {
        ModelAndView modelAndView = new ModelAndView("trains-list");

        if (!scheduleService.findTrainById(trainId).isEmpty()) {
            modelAndView.addObject("message", getMessage("message.train.delete.fail", locale));
        } else {
            trainService.deleteTrain(trainId);
            modelAndView.addObject("message", getMessage("message.train.delete.success", locale));
        }
        List<Train> trains = trainService.getTrains();
        modelAndView.addObject("trains", trains);

        return modelAndView;
    }

    /**
     * method show view.jsp for edit train with existed values on it
     *
     * @param trainId
     * @param locale
     * @return
     */
    @RequestMapping(value = "/edit/{train.id}", method = RequestMethod.GET)
    public ModelAndView editTrain(@PathVariable(value = "train.id") int trainId, Locale locale) {

        ModelAndView modelAndView = new ModelAndView("edit-train");
        modelAndView.addObject("train", trainService.get(trainId));
        modelAndView.addObject("pageTitle", getMessage("page.title.edit-train", locale));

        return modelAndView;
    }

    /**
     * method edit and update train by id from DataBase if it not include in existing schedule
     *
     * @param train
     * @param locale
     * @return
     */
    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public ModelAndView editTrainSave(@ModelAttribute("train") Train train, Locale locale) {
        ModelAndView modelAndView = new ModelAndView("trains-list");
        if (!scheduleService.findTrainById(train.getId()).isEmpty()) {
            modelAndView.addObject("message", getMessage("message.train.modify.fail", locale));
        } else {
            trainService.editTrain(train);
            modelAndView.addObject("message", getMessage("message.train.modify.success", locale));
        }
        List<Train> trains = trainService.getTrains();
        modelAndView.addObject("trains", trains);

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

    @Autowired
    public void setScheduleService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }
}
