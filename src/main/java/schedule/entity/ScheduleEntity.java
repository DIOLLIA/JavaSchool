package schedule.entity;

import org.hibernate.annotations.Type;
import org.joda.time.LocalTime;

import javax.persistence.*;

@Entity
@Table(name = "schedule")
public class ScheduleEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    private int routeDailyId;

    private int routeStationIndex;

    @Column(name = "arrival_time")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalTime")
    private LocalTime arrivalTime;

    @Column(name = "departure_time")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalTime")
    private LocalTime departureTime;

    @ManyToOne(cascade = CascadeType.MERGE)
    // @OnDelete(action = OnDeleteAction.CASCADE) //todo не работает так как данная сущность есть в нескольких табах
    private StationEntity stationName;

    @ManyToOne(cascade = CascadeType.MERGE)
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private TrainEntity trainNumber;

    private Boolean isEnable = true;

    @ManyToOne(cascade = CascadeType.MERGE)
    // @JoinColumn(name = "route_id")
    private RouteEntity routeName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public StationEntity getStationName() {
        return stationName;
    }

    public void setStationName(StationEntity stationName) {
        this.stationName = stationName;
    }

    public TrainEntity getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(TrainEntity trainNumber) {
        this.trainNumber = trainNumber;
    }

    public RouteEntity getRouteName() {
        return routeName;
    }

    public void setRouteName(RouteEntity routeName) {
        this.routeName = routeName;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public int getRouteDailyId() {
        return routeDailyId;
    }

    public void setRouteDailyId(int routeDailyId) {
        this.routeDailyId = routeDailyId;
    }

    public int getRouteStationIndex() {
        return routeStationIndex;
    }

    public void setRouteStationIndex(int routeStationIndex) {
        this.routeStationIndex = routeStationIndex;
    }

    @Override
    public String toString() {
        return "ScheduleEntity{" +
                "id=" + id +
                ", routeDailyId=" + routeDailyId +
                ", routeStationIndex=" + routeStationIndex +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                ", stationName=" + stationName +
                ", trainNumber=" + trainNumber +
                ", isEnable=" + isEnable +
                ", routeName=" + routeName +
                '}';
    }
}
