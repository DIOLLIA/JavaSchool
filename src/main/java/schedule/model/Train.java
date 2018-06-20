package schedule.model;


import javax.validation.constraints.NotNull;

public class Train {

    private int id;
    @NotNull
    private int numberOfTrain;
    @NotNull
    private int seats;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfTrain() {
        return numberOfTrain;
    }

    public void setNumberOfTrain(int numberOfTrain) {
        this.numberOfTrain = numberOfTrain;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Train{" +
                "id=" + id +
                ", numberOfTrain=" + numberOfTrain +
                ", seats=" + seats +
                '}';
    }
}
