package schedule.model;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "routes")
public class Route {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "route_name")
    private String routeName;

    @Column(name = "stations")
    private ArrayList<Station> stationsList;

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

    public ArrayList<Station> getStationsList() {
        return stationsList;
    }

    public void setStationsList(ArrayList<Station> stationsList) {
        this.stationsList = stationsList;
    }
}
