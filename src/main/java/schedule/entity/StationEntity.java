package schedule.entity;

import javax.persistence.*;

@Entity
@Table(name = "stations")
public class StationEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "station_name")
    private String StationName;

    @Column(name = "coordinate")
    private double coordinate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStationName() {
        return StationName;
    }

    public void setStationName(String stationName) {
        StationName = stationName;
    }

    public double getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(double coordinate) {
        this.coordinate = coordinate;
    }
}
