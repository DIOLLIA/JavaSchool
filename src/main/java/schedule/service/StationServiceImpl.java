package schedule.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schedule.model.Station;

import java.util.List;


@Service
@Transactional
public class StationServiceImpl implements StationService {
    @Override
    public void addStation(Station station) {

    }

    @Override
    public void getStation(int id) {

    }

    @Override
    public void deleteStation(int id) {

    }

    @Override
    public List<Station> getStations() {
        return null;
    }
}
