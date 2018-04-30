package schedule.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "routes")
public class Route {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "route_name")
    private String routeName;

    @OneToMany
    @ElementCollection()
    @Column(name = "station_name")
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
