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
    private long latitude;

    @Column(name = "longitude")
    private long longitude;

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

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }
}
