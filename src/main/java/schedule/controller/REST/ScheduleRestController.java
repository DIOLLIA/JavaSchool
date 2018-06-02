package schedule.controller.REST;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import schedule.service.api.StationService;

import java.util.List;

@RequestMapping(value = "/mainSearch")
@RestController
public class ScheduleRestController {
    @Autowired
    StationService stationService;
    @GetMapping(value = "/get-stations/")
    public String getStationsNames() {
        List<String> stationsNames = stationService.getStationsNames();

        return new Gson().toJson(stationsNames);
    }

    @GetMapping(value = "/get-stations/{selectedFromStation}")
    public String getStationsNamesTest(@PathVariable(value = "selectedFromStation") String selectedFromStation) {
        List<String> stationsNames = stationService.getStationsNames();
        stationsNames.remove(selectedFromStation);
        return new Gson().toJson(stationsNames);
    }
}
