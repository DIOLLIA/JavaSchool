package schedule.model;


import org.joda.time.DateTime;

public class Ticket {

    private int id;

    private User user;

    private Train train;

    private DateTime ticketDateTime;

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

    public DateTime getTicketDateTime() {
        return ticketDateTime;
    }

    public void setTicketDateTime(DateTime ticketDateTime) {
        this.ticketDateTime = ticketDateTime;
    }
}
