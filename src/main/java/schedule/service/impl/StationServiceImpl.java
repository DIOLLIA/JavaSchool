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

    @Autowired
    private StationDao stationDao;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public Station addStation(Station stationDto) {
        StationEntity stationEntity = modelMapper.map(stationDto, StationEntity.class);
        stationDao.addStation(stationEntity);
        return modelMapper.map(stationEntity, Station.class);
    }

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
}
