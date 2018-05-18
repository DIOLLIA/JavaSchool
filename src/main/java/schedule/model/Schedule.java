package schedule.model;


import org.joda.time.LocalTime;

public class Schedule {
    private int id;

    private int routeDailyId;
    private int routeStationIndex;
    private LocalTime arrivalTime;

    private LocalTime departureTime;

    private Station stationName;

    private Train trainNumber;

    private Integer timeInterval;

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

    public int getRouteStationIndex() {
        return routeStationIndex;
    }

    public void setRouteStationIndex(int routeStationIndex) {
        this.routeStationIndex = routeStationIndex;
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
