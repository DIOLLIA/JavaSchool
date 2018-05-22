package schedule.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schedule.dao.impl.TicketDaoImpl;
import schedule.entity.TicketEntity;
import schedule.model.Ticket;
import schedule.service.api.TicketService;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    TicketDaoImpl ticketDao;

    @Override
    public Ticket addTicket(Ticket ticket) {
        TicketEntity ticketEntity = modelMapper.map(ticket, TicketEntity.class);
        ticketDao.addTicket(ticketEntity);
        return modelMapper.map(ticketEntity, Ticket.class);
    }
}
