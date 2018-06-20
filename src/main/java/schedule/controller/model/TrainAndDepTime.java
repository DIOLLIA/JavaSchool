package schedule.controller.model;

/**
 * @author Rudkov Andrey
 * class {@link TrainAndDepTime} helps for search
 * <b>trainNumber</b> <b>departureTime</b> values for request ticket
 * without use of real object.
 */

public class TrainAndDepTime {
    private int trainNumber;
    private String departureTime;

    public TrainAndDepTime() {
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }
}
