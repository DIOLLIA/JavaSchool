package schedule.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "schedule")
public class ScheduleEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

   // @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalTime")
    // @Type(type = "org.joda.time.contrib.hibernate.PersistentLocalTimeAsTime")
   //@Type(type = " java.time.LocalTime")  // org.hibernate.MappingException: Could not determine type for:  java.time.LocalTime
    @Column(name = "arrival_time")
    private Date arrivalTime;

   // @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalTime")
    //  @Type(type = "org.joda.time.contrib.hibernate.PersistentLocalTimeAsTime")
   // @Type(type = " java.time.LocalTime")  // org.hibernate.MappingException: Could not determine type for:  java.time.LocalTime
    @Column(name = "departure_time")
    private Date departureTime;

    @ManyToOne
    // @JoinColumn(name = "station_name")
    private StationEntity stationName;

    @ManyToOne
    //  @JoinColumn(name = "train_number_id")
    private TrainEntity trainNumber;


    private Integer timeInterval;

    @ManyToOne
    // @JoinColumn(name = "route_id")
    private RouteEntity routeName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
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
}
