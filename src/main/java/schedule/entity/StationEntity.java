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
    private String stationName;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "StationEntity{" +
                "id=" + id +
                ", stationName='" + stationName + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
