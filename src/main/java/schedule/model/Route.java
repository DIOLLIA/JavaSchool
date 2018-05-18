package schedule.model;

import java.util.ArrayList;
import java.util.List;

public class Route {
    private int id;
    private String routeName;
    private List<Station> stationsList;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public List<Station> getStationsList() {
        return stationsList;
    }

    public void setStationsList(ArrayList<Station> stationsList) {
        this.stationsList = stationsList;
    }
}
