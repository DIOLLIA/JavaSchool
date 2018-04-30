package schedule.dao;

import schedule.entity.StationEntity;

import java.util.List;

public interface StationDao {
    public void addStation(StationEntity station);

    public StationEntity getStation(int id);

    public void deleteStation(int id);

    public List<StationEntity> getStations();
}

