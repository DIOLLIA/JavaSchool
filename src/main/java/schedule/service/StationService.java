package schedule.service;

import schedule.entity.StationEntity;
import schedule.model.Station;

import java.util.List;

public interface StationService {

    Station addStation(Station station);

    void getStation(int id);

    void deleteStation(int id);

    List<Station> getStations();

    Station findByName(String name);
}


