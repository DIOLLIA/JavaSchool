package schedule.entity;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table
public class TicketEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "ticketDateTime")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime departureDateTime;

    @ManyToOne
    private TrainEntity trainEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    private UserEntity userEntity;

    @OneToOne
    private ScheduleEntity departureSchedule;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public TrainEntity getTrainEntity() {
        return trainEntity;
    }

    public void setTrainEntity(TrainEntity trainEntity) {
        this.trainEntity = trainEntity;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public ScheduleEntity getDepartureSchedule() {
        return departureSchedule;
    }

    public void setDepartureSchedule(ScheduleEntity departureSchedule) {
        this.departureSchedule = departureSchedule;
    }
}
