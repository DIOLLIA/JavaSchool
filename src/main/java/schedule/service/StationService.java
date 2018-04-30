package schedule.service;

import schedule.entity.StationEntity;
import schedule.model.Station;

import java.util.List;

public interface StationService {

    public void addStation(Station station);

    public void getStation(int id);

    public void deleteStation(int id);

    public List<Station> getStations();

}


