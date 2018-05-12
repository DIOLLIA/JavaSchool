package schedule.dao.api;

import schedule.entity.StationEntity;

import java.util.List;

public interface StationDao {
    void addStation(StationEntity station);

    StationEntity getStation(int id);

    void deleteStation(int id);

    List<StationEntity> getStations();

    StationEntity findByName(String name);
}

