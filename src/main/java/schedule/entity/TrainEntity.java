package schedule.entity;

import javax.persistence.*;


@Entity
@Table(name = "trains")
public class TrainEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "train_number")
    private int numberOfTrain;

    @Column(name = "seats")
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
        return "TrainEntity{" +
                "id=" + id +
                ", numberOfTrain=" + numberOfTrain +
                ", seats=" + seats +
                '}';
    }
}
