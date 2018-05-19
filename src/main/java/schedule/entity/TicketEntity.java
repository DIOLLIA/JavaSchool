package schedule.entity;

import javax.persistence.*;

@Entity
@Table
public class TicketEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @ManyToOne
  //  @Column(name = "passenger")
    private PassengerEntity passengerEntity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PassengerEntity getPassengerEntity() {
        return passengerEntity;
    }

    public void setPassengerEntity(PassengerEntity passengerEntity) {
        this.passengerEntity = passengerEntity;
    }

}
