package schedule.dao.api;

import schedule.entity.StationEntity;

import java.util.List;

public interface StationDao extends GeneralCrudDao<StationEntity> {
    void addStation(StationEntity station);

    void addStation(String stationName, double latitude, double longetude);

    StationEntity getStation(int id);

    void deleteStation(int id);

    List<StationEntity> getStations();

    StationEntity findByName(String name);

    void editStation(StationEntity stationEntity);

}

