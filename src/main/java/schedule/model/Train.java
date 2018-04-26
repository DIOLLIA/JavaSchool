package schedule.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;


@Entity
@Table(name = "train")
public class Train {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "number_of_train")
    private int numberOfTrain;


    @Column(name = "station")
    private String station; //todo возможно стоит использовать коллекции для списка станций, а так же аннотации @CollectionTable и @CollectionTable. Или  использовать связи между сущностями...

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

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
}
