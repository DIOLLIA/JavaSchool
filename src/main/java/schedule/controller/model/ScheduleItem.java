package schedule.controller.model;

import org.joda.time.LocalTime;

/**
 * @author Rudkov Andrey
 * class {@link ScheduleItem} helps for
 * search,create Schedule object using real {@link schedule.model.Schedule} fields. Class use similar
 * fields, which can be easily obtained from
 * jsp Models for further parsing it and create real Schedule object
 */
public class ScheduleItem {
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private int trainNumber;
    private String stationOfDeparture;
    private String stationOfArrival;
    private int scheduleDailyRouteId;

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getStationOfDeparture() {
        return stationOfDeparture;
    }

    public void setStationOfDeparture(String stationOfDeparture) {
        this.stationOfDeparture = stationOfDeparture;
    }

    public String getStationOfArrival() {
        return stationOfArrival;
    }

    public void setStationOfArrival(String stationOfArrival) {
        this.stationOfArrival = stationOfArrival;
    }

    public int getScheduleDailyRouteId() {
        return scheduleDailyRouteId;
    }

    public void setScheduleDailyRouteId(int scheduleDailyRouteId) {
        this.scheduleDailyRouteId = scheduleDailyRouteId;
    }
}
