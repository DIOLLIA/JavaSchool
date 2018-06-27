package schedule.service.api;

import schedule.model.Station;

import java.util.List;

/**
 * @author Rudkov Andrey
 * <p>
 * StationService class basically interacts with {@link Station} objects.
 * Class methods can: add station with coordinates,
 * getting station by name or id,
 * delete of edit station by id,
 * receive  list of all station objects or it names only.
 */

public interface StationService {

    void addStation(String station, double latitude, double longitude);

    Station getStation(int id);

    void deleteStation(int id);

    void editStation(Station station);

    List<Station> getStations();

    Station findByName(String name);

    List<String> getStationsNames();
}


