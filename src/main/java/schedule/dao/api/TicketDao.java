package schedule.dao.api;

import schedule.entity.TicketEntity;

public interface TicketDao extends GeneralCrudDao<TicketEntity> {

    void addTicket(TicketEntity ticketEntity);

/*    void addModelTicket(Ticket ticket);*/
}
