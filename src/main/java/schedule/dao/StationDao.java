package schedule.dao;

import schedule.entity.StationEntity;
import schedule.model.Station;

import java.util.List;

public interface StationDao {
    void addStation(StationEntity station);

    StationEntity getStation(int id);

    void deleteStation(int id);

    List<StationEntity> getStations();

    StationEntity findByName(String name);
}

