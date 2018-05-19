package schedule.entity;

import javax.persistence.*;
import java.util.List;


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

    @ManyToMany  // todo !проверить
    @Column(name = "ticket")
    private List<TicketEntity> ticketEntity; //todo map?

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

    public List<TicketEntity> getTicketEntity() {
        return ticketEntity;
    }

    public void setTicketEntity(List<TicketEntity> ticketEntity) {
        this.ticketEntity = ticketEntity;
    }
}
