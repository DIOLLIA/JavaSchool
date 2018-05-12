package schedule.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schedule.dao.StationDao;
import schedule.entity.StationEntity;
import schedule.model.Station;

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
    public void getStation(int id) {
        stationDao.getStation(id);
    }

    @Override
    public void deleteStation(int id) {
        stationDao.deleteStation(id);
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

}
