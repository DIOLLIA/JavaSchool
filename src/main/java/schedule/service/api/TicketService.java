package schedule.service.api;

import schedule.controller.model.TicketItem;
import schedule.model.Ticket;

public interface TicketService {

    Ticket addUserTicket(Ticket ticket);

    void addGuestTicket(TicketItem ticket);
}
