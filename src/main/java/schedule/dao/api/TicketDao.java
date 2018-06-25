package schedule.dao.api;

import schedule.entity.TicketEntity;

import java.util.List;

public interface TicketDao extends GeneralCrudDao<TicketEntity> {

    void addTicket(TicketEntity ticketEntity);

    boolean findTicket(TicketEntity ticketEntity);

    boolean remainedElseVacantSeats(int seats, TicketEntity ticketEntity);

    List<TicketEntity> findByUserName(String username);
}
