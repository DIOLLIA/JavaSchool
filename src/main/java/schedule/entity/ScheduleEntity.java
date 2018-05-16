package schedule.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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

    @Column(name = "arrival_time")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalTime")
    private LocalTime arrivalTime;

    @Column(name = "departure_time")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalTime")
    private LocalTime departureTime;

    @ManyToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "station_name")
    private StationEntity stationName;

    @ManyToOne(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //  @JoinColumn(name = "train_number_id")
    private TrainEntity trainNumber;

    private Integer timeInterval;

    @ManyToOne(cascade = CascadeType.ALL)
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

    public Integer getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(Integer timeInterval) {
        this.timeInterval = timeInterval;
    }

    public int getRouteDailyId() {
        return routeDailyId;
    }

    public void setRouteDailyId(int routeDailyId) {
        this.routeDailyId = routeDailyId;
    }
}
