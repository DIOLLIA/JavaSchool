package schedule.service.api;

import schedule.controller.model.TicketItem;
import schedule.exception.CustomServiceException;
import schedule.model.Ticket;
import schedule.model.User;

import java.util.List;

/**
 * @author Rudkov Andrey
 * <p>
 * TicketService class basically interacts with {@link Ticket} objects.
 * Class methods can: create tickets by incoming parameters,
 * check incoming parameters and makes simple validation for time and vacant seats
 */
public interface TicketService {

    Ticket createGuestTicket(TicketItem ticket) throws CustomServiceException;

    TicketItem createTicketItem(String departureDate, String departureTime, String birthDay, String name, String surName, int trainNumber, String stationFrom, String stationTo);

    Ticket createTicketForUser(String departureDate, String departureTime, int trainNumber, String stationFrom, String stationTo, User user) throws CustomServiceException;

    boolean weHaveTenMinutes(String departureDate, String departureTime);

    boolean isVacantSeatsOnTrain(String departureDate, String departureTime, String stationFrom, String stationTo, int trainNumber) throws CustomServiceException;

    String ticketSimpleValidation(String stationTo, String stationFrom, String depTime, String depDate, String name, String surname, String birthDate, String trainNumber) throws CustomServiceException;

    List<Ticket> findByUserName(String username);
}
