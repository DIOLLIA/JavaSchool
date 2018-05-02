package schedule.model;

import schedule.entity.TrainEntity;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "arrival_time")
    private LocalTime arrivalTime;

    @Column(name = "departure_time")
    private LocalTime departureTime;

    @OneToOne
    @JoinColumn(name = "station_name")
    private Station stationName;

    @OneToOne
    @JoinColumn(name = "train_number_id")
    private TrainEntity trainNumber;

    @Column(name = "interspace")
    private LocalTime interspace;

    @OneToOne
    @JoinColumn(name = "route_id")
    private Route routeName;

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

    public Station getStationName() {
        return stationName;
    }

    public void setStationName(Station stationName) {
        this.stationName = stationName;
    }

    public TrainEntity getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(TrainEntity trainNumber) {
        this.trainNumber = trainNumber;
    }

    public Route getRouteName() {
        return routeName;
    }

    public void setRouteName(Route routeName) {
        this.routeName = routeName;
    }

    public LocalTime getInterspace() {
        return interspace;
    }

    public void setInterspace(LocalTime interspace) {
        this.interspace = interspace;
    }
}
