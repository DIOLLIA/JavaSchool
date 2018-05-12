package schedule.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "schedule")
public class ScheduleEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalTime")
   // @Type(type = "org.joda.time.contrib.hibernate.PersistentLocalTimeAsTime")
    @Column(name = "arrival_time")
    private Date arrivalTime;
  //  private LocalTime arrivalTime;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalTime")
  //  @Type(type = "org.joda.time.contrib.hibernate.PersistentLocalTimeAsTime")
    @Column(name = "departure_time")
    private LocalTime departureTime;


    @ManyToOne
   // @JoinColumn(name = "station_name")
    private StationEntity stationName;

    @ManyToOne
  //  @JoinColumn(name = "train_number_id")
    private TrainEntity trainNumber;

  //  @Type(type = "org.joda.time.contrib.hibernate.PersistentLocalTimeAsTime")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalTime")
    @Column(name = "interspace")
    private LocalTime interspace;

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

    public LocalTime getInterspace() {
        return interspace;
    }

    public void setInterspace(LocalTime interspace) {
        this.interspace = interspace;
    }
}
