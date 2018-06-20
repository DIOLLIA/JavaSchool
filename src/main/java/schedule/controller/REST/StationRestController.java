package schedule.controller.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import schedule.controller.BaseController;
import schedule.service.api.StationService;

/**
 * @author Rudkov Andrey
 * <p>
 * controller work with Google map
 */
@RestController
public class StationRestController extends BaseController {

    private StationService stationService;

    /**
     * method takes params
     * @param stationName
     * @param lat
     * @param lng
     * from ajax, and create station with it in database
     * @return boolean
     */
    @GetMapping(value = "/add-station**")
    public boolean addStation(@RequestParam("placeName") String stationName,
                              @RequestParam("lat") Double lat,
                              @RequestParam("lng") Double lng) {
        if (stationService.findByName(stationName) == null) {
            stationService.addStation(stationName, lat, lng);
            return true;
        }
        return false;
    }

    @Autowired
    public void setStationService(StationService stationService) {
        this.stationService = stationService;
    }
}

