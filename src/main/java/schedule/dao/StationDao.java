package schedule.dao;

import schedule.model.Station;

import java.util.List;

public interface StationDao {
    public void addTrain(Station station);

    public Station getStation(int id);

    public void deleteStation(int id);

    public List<Station> getStations();
}

