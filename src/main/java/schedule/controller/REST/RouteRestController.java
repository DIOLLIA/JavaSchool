package schedule.controller.REST;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import schedule.controller.model.ScheduleItem;
import schedule.exception.CustomServiceException;
import schedule.model.Route;
import schedule.model.Schedule;
import schedule.model.Train;
import schedule.service.api.RouteService;
import schedule.service.api.ScheduleService;
import schedule.service.api.TrainService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rudkov Andrey
 */
@RequestMapping(value = "/routes")
@RestController
public class RouteRestController {

    private RouteService routeService;
    private TrainService trainService;
    private ScheduleService scheduleService;

    /**
     * Method return list of routes from DataBase as Json
     *
     * @return list of routes from DataBase
     */
    @GetMapping(value = "/get-routes/")
    public String routesList() {
        List<Route> routes = routeService.routesList();
        List<String> routeName = new ArrayList<>();
        for (Route item : routes) {
            routeName.add(item.getRouteName());
        }
        return new Gson().toJson(routeName);
    }

    /**
     * method find stations belonging to route from DataBase as Json
     *
     * @param selectedRoute
     * @return list of stations belonging to route from DataBase
     */
    @GetMapping(value = "/get-stations/{selectedRoute}")
    public String getStationsOnRoute(@PathVariable(value = "selectedRoute") String selectedRoute) {
        int selectedRouteId = routeService.findByName(selectedRoute);
        List<String> stations = routeService.findStationsByRouteName(selectedRouteId);

        return new Gson().toJson(stations);
    }

    /**
     * method return all trains from DataBase as Json
     *
     * @return return list of trains from DataBase
     */
    @GetMapping(value = "/get-trains")
    public String getTrains() throws CustomServiceException {
        List<Train> train = trainService.getTrains();
        List<Integer> trainsList = new ArrayList<>();
        for (Train trainModel : train) {
            trainsList.add(trainModel.getNumberOfTrain());
        }
        return new Gson().toJson(trainsList);
    }

    /**
     * method return all routes for request train
     *
     * @return return routes for train request  from DataBase
     */
    @GetMapping(value = "/findRoute")
    public List<ScheduleItem> getRoutesByTrainNumber(@RequestParam("trainNumber") int trainNumber) throws CustomServiceException {
        int trainId = trainService.findByNumber(trainNumber).getId();
        List<Schedule> scheduleList = scheduleService.findTrainById(trainId);
        List<Schedule> formatedSchedule;
        if (!scheduleList.isEmpty()) {
            int scheduleId = scheduleList.get(0).getRouteDailyId();
            formatedSchedule = scheduleService.showRouteDetails(scheduleList, scheduleId);

            List<ScheduleItem> scheduleItems = new ArrayList<>();
            for (Schedule item : formatedSchedule) {
                ScheduleItem scheduleItem = new ScheduleItem();
                scheduleItem.setArrivalTime(item.getArrivalTime());
                scheduleItem.setDepartureTime(item.getDepartureTime());
                scheduleItem.setStationOfDeparture(item.getStationName().getStationName());
                scheduleItems.add(scheduleItem);
            }
            return scheduleItems;
        }
        return new ArrayList<>();
    }

    @Autowired
    public void setTrainService(TrainService trainService) {
        this.trainService = trainService;
    }

    @Autowired
    public void setRouteService(RouteService routeService) {
        this.routeService = routeService;
    }

    @Autowired
    public void setScheduleService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }
}
