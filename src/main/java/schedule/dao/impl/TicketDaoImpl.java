package schedule.dao.impl;

import org.springframework.stereotype.Repository;
import schedule.dao.api.TicketDao;
import schedule.entity.TicketEntity;


@Repository
public class TicketDaoImpl extends GeneralCrudDaoImpl<TicketEntity> implements TicketDao {

    @Override
    public void addTicket(TicketEntity ticketEntity) {
        getCurrentSession().save(ticketEntity);
    }
}
