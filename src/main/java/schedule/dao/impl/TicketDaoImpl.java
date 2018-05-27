package schedule.dao.impl;

import org.springframework.stereotype.Repository;
import schedule.dao.api.TicketDao;
import schedule.entity.TicketEntity;
import schedule.model.Ticket;


@Repository
public class TicketDaoImpl extends GeneralCrudDaoImpl<TicketEntity> implements TicketDao {

    @Override
    public void addTicket(TicketEntity ticketEntity) {
        getCurrentSession().save(ticketEntity);
    }
    public void addModelTicket(Ticket ticket) {
        getCurrentSession().save(ticket);
    }
}
