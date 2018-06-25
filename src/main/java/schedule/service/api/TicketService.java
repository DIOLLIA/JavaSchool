package schedule.service.api;

import schedule.controller.model.TicketItem;
import schedule.model.Ticket;
import schedule.model.User;

import java.util.List;

public interface TicketService {

//    void addUserTicket(Ticket ticket);

    Ticket createGuestTicket(TicketItem ticket);

    /*  boolean simpleRouteValidation(String stationFrom, String stationTo, String departureDate, String departureTime, int trainNumber);*/

    TicketItem createTicketItem(String departureDate, String departureTime, String birthDay, String name, String surName, int trainNumber, String stationFrom, String stationTo);

    Ticket createTicketForUser(String departureDate, String departureTime, int trainNumber, String stationFrom, String stationTo, User user);

    boolean weHaveTenMinutes(String departureDate, String departureTime);

    boolean isVacantSeatsOnTrain(String departureDate, String departureTime, String stationFrom, String stationTo, int trainNumber);

    String ticketSimpleValidation(String stationTo, String stationFrom, String depTime, String depDate, String name, String surname, String birthDate, String trainNumber);

    List<Ticket> findByUserName(String username);
}
