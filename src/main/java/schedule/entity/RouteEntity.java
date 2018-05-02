package schedule.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "routes")
public class RouteEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "route_name")
    private String routeName;

    @OneToMany
    @ElementCollection()
    @Column(name = "station_name")
    private List<StationEntity> stationsList;

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

    public List<StationEntity> getStationsList() {
        return stationsList;
    }

    public void setStationsList(ArrayList<StationEntity> stationsList) {
        this.stationsList = stationsList;
    }
}
