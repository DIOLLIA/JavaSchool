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

import java.util.*;

@Controller
@RequestMapping(value = "/train")
public class TrainController {

    @Autowired
    TrainService trainService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/list")
    public ModelAndView listOfTrains() {
        ModelAndView modelAndView = new ModelAndView("trains-list");

        List<Train> trains = trainService.getTrains();
        modelAndView.addObject("trains", trains);
        modelAndView.addObject("pageTitle", "Trains list");

        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addTrainPage() {
        ModelAndView modelAndView = new ModelAndView("addTicket");
        modelAndView.addObject("train", new Train());
        modelAndView.addObject("pageTitle", "Add train");

        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addTrain(@ModelAttribute Train train) {

        ModelAndView modelAndView = new ModelAndView("trains-list");
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
        ModelAndView modelAndView = new ModelAndView("trains-list");
        List<Train> trains = trainService.getTrains();
        modelAndView.addObject("trains", trains);
        modelAndView.addObject("message", message);

        return modelAndView;
    }

    @RequestMapping(value = "/edit/{train.id}", method = RequestMethod.GET)
    public ModelAndView editTrain(@PathVariable(value = "train.id") int trainId) {

        ModelAndView modelAndView = new ModelAndView("edit-train");
        modelAndView.addObject("train", trainService.get(trainId));
        modelAndView.addObject("pageTitle", "Edit train");

        return modelAndView;
    }

    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public ModelAndView editTrainSave(@ModelAttribute("train") Train train /*,
            @PathVariable ("id") int trainId*/
            /* @RequestParam ("id") int trainId*/) {
        trainService.editTrain(train);
        String message = "Train was successfully modified.";

        ModelAndView modelAndView = new ModelAndView("trains-list");
        List<Train> trains = trainService.getTrains();

        modelAndView.addObject("trains", trains);
        modelAndView.addObject("message", message);

        return modelAndView;
    }

    @RequestMapping(value = "/schedule/{train.id}", method = RequestMethod.GET)
    public ModelAndView trainSchedule(@PathVariable(value = "train.id") int trainId) {

        String msg = "Routes for train №" + trainService.get(trainId).getNumberOfTrain();

        ModelAndView modelAndView = new ModelAndView("train-routes-list");
        modelAndView.addObject("pageTitle", "Train routes");

        modelAndView.addObject("train", trainService.get(trainId));
        List<Schedule> schedules = trainService.getScheduleByTrainId(trainId);

        Map<Integer, List<Schedule>> scheduleMap = new HashMap<>();
        for (Schedule element : schedules) {
            int dayliRouteId = element.getRouteDailyId();
            if (!scheduleMap.containsKey(dayliRouteId)) {
                scheduleMap.put(dayliRouteId, new ArrayList<>());
            }
            scheduleMap.get(dayliRouteId).add(element);
        }
        List<ScheduleItem> resultScheduleList = new ArrayList<>();

        for (List<Schedule> scheduleList : scheduleMap.values()) {

            scheduleList.sort(new RouteStationIdComparator());

            Schedule firstStationInSchedule = scheduleList.get(0);
            Schedule lastStationInSchedule = scheduleList.get(scheduleList.size() - 1);

            ScheduleItem scheduleItem = new ScheduleItem();
            scheduleItem.setTrainNumber(firstStationInSchedule.getTrainNumber().getNumberOfTrain());
            scheduleItem.setStationOfDeparture(firstStationInSchedule.getStationName().getStationName());
            scheduleItem.setStationOfArrival(lastStationInSchedule.getStationName().getStationName());
            scheduleItem.setDepartureTime(firstStationInSchedule.getDepartureTime());
            scheduleItem.setArrivalTime(lastStationInSchedule.getArrivalTime());
            scheduleItem.setScheduleDailyRouteId(firstStationInSchedule.getRouteDailyId());
            resultScheduleList.add(scheduleItem);
        }
        modelAndView.addObject("scheduleByTrainId", resultScheduleList);
        modelAndView.addObject("msg", msg);

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
        String msg = "Passengers list on train №" + trainService.get(Integer.parseInt(trainId)).getNumberOfTrain() + " " + dateAsString + ":";
        List<User> passengersOnRoute = userService.findPassengersOfTrain(routeId, startDateTime);
        ModelAndView modelAndView = new ModelAndView("passengers-on-daily-route").addObject("passengersOnRoute", passengersOnRoute);
        modelAndView.addObject("msg", msg);
        return modelAndView;

    }

    private class RouteStationIdComparator implements Comparator<Schedule> {
        @Override
        public int compare(Schedule o1, Schedule o2) {
            if (o1.getRouteStationIndex() == o2.getRouteStationIndex()) {
                return 0;
            } else if (o1.getRouteStationIndex() > o2.getRouteStationIndex()) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}