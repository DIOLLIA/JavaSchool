package schedule.service.api;

import schedule.controller.model.TicketItem;
import schedule.model.Ticket;
import schedule.model.User;

public interface TicketService {

    void addUserTicket(Ticket ticket);

    void addGuestTicket(TicketItem ticket);

    boolean simpleRouteValidation(String stationFrom, String stationTo, String departureDate, String departureTime, int trainNumber);

    TicketItem createTicketItem(String departureDate, String departureTime, String birthDay, String name, String surName, int trainNumber, String stationFrom, String stationTo);

    Ticket createTicket (String departureDate, String departureTime, int trainNumber, String stationFrom, String stationTo, User user);
}
