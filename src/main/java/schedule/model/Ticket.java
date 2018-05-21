package schedule.model;

import org.joda.time.LocalDateTime;

public class Ticket {

    private int id;

    private User user;

    private Train train;

    private LocalDateTime departureDateTime;

    private Schedule departureSchedule;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public Schedule getDepartureSchedule() {
        return departureSchedule;
    }

    public void setDepartureSchedule(Schedule departureSchedule) {
        this.departureSchedule = departureSchedule;
    }
}
