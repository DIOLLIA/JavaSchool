package schedule.dao.api;

import schedule.entity.TicketEntity;

public interface TicketDao extends GeneralCrudDao<TicketEntity> {

    void addTicket(TicketEntity ticketEntity);

    boolean findTicket(TicketEntity ticketEntity);

    boolean remainedElseVacantSeats(int seats, TicketEntity ticketEntity);
}
