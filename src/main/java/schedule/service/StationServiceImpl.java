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
    private ModelMapper modelMapper;

    @Override
    public void addStation(Station stationDto) {
        StationEntity stationEntity = modelMapper.map(stationDto, StationEntity.class);
        stationDao.addStation(stationEntity);
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
        List<StationEntity> stationEntities = stationDao.getStations();
        List<Station> stationsDto = new ArrayList<>();
        for (StationEntity station : stationEntities) {
            stationsDto.add(modelMapper.map(stationEntities, Station.class));
        }
        return stationsDto;
    }
}
