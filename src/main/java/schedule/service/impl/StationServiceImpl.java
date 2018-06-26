package schedule.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schedule.dao.api.StationDao;
import schedule.entity.StationEntity;
import schedule.model.Station;
import schedule.service.api.StationService;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class StationServiceImpl implements StationService {

    private StationDao stationDao;
    private ModelMapper modelMapper;

    /**
     * method add station to database that takes from Google map:
     * @param stationName
     * @param latitude
     * @param longitude
     * @return new {@link Station} if success
     */
    @Override
    public void addStation(String stationName, double latitude, double longitude) {

        stationDao.addStation(stationName, latitude, longitude);
    }

    @Override
    public Station getStation(int id) {
        return modelMapper.map(stationDao.getStation(id), Station.class);
    }

    @Override
    public void deleteStation(int id) {
        stationDao.deleteStation(id);
    }

    @Override
    public void editStation(Station station) {
        stationDao.editStation(modelMapper.map(station, StationEntity.class));

    }

    @Override
    public List<Station> getStations() {
        List<StationEntity> allStations = stationDao.getStations();
        List<Station> stations = new ArrayList<>();

        for (StationEntity stationEntity : allStations) {
            stations.add(modelMapper.map(stationEntity, Station.class));
        }
        return stations;
    }

    @Override
    public Station findByName(String name) {
        StationEntity station = stationDao.findByName(name);
        if (station == null) {
            return null;
        }
        return modelMapper.map(station, Station.class);
    }

    @Override
    public List<String> getStationsNames() {
        List<Station> stations = getStations();
        List<String> stationNames = new ArrayList<>();

        for (Station station : stations) {
            stationNames.add(station.getStationName());
        }
        return stationNames;
    }

    @Autowired
    public void setStationDao(StationDao stationDao) {
        this.stationDao = stationDao;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
