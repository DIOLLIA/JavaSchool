package schedule.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "shedule")
public class Shedule {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "arrival_time")
    private Date arrivalTime;

    @Column(name = "departure_time")
    private Date departureTime;

    @Column(name = "station_name_id")
    private String stationName;

    @Column(name = "train_number_id")
    private int trainNumber;

    @Column(name = "interspace")
    private Date interspace;

    @Column(name = "route_id")
    private String routeName;

}
