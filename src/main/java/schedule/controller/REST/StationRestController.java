package schedule.controller.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import schedule.service.api.StationService;

@RestController
public class StationRestController {

    @Autowired
    private StationService stationService;

    @GetMapping(value = "/add-station**")
    public ResponseEntity<Boolean> addStation(@RequestParam("placeName") String stationName,
                                              @RequestParam("lat") Double lat,
                                              @RequestParam("lng") Double lng) {
        stationService.addStation(stationName, lat, lng);

        return ResponseEntity.ok(true);
    }
}

