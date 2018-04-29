package schedule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schedule.dao.StationDao;
import schedule.model.Station;

import java.util.List;


@Service
@Transactional
public class StationServiceImpl implements StationService {
    @Autowired
    private StationDao stationDao;

    @Override
    public void addStation(Station station) {
        stationDao.addStation(station);
    }

    @Override
    public void getStation(int id) {
        stationDao.getStation(id);
    }

    @Override
    public void deleteStation(int id) {
        stationDao.deleteStation(id);
    }

    @Override
    public List<Station> getStations() {
        return stationDao.getStations();
    }
}
