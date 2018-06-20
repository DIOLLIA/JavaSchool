package schedule.controller.model;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

/**
 * @author Rudkov Andrey
 * class {@link TicketItem} helps for
 * create and search tickets without use of real object {@link schedule.model.Ticket}. Class use similar
 * fields, which can be easily obtained from
 * jsp model @RequestParam values taken
 */

public class TicketItem {
    private String stationFrom;
    private String stationTo;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private int trainNumber;
    private String name;
    private String surName;
    private LocalDate birthDay;

    public String getStationFrom() {
        return stationFrom;
    }

    public void setStationFrom(String stationFrom) {
        this.stationFrom = stationFrom;
    }

    public String getStationTo() {
        return stationTo;
    }

    public void setStationTo(String stationTo) {
        this.stationTo = stationTo;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }
}
