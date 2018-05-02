package schedule.model;

import java.time.LocalTime;

public class Schedule {
    private int id;

    private LocalTime arrivalTime;

    private LocalTime departureTime;

    private Station stationName;

    private Train trainNumber;

    private LocalTime interspace;

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

    public Train getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(Train trainNumber) {
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
