package schedule.service.impl;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schedule.controller.model.TicketItem;
import schedule.dao.impl.TicketDaoImpl;
import schedule.entity.TicketEntity;
import schedule.entity.TrainEntity;
import schedule.entity.UserEntity;
import schedule.model.*;
import schedule.service.api.RouteService;
import schedule.service.api.ScheduleService;
import schedule.service.api.TicketService;
import schedule.service.api.TrainService;

import java.util.List;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    TicketDaoImpl ticketDao;
    @Autowired
    TrainService trainService;
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    RouteService routeService;

    @Override
    public void addUserTicket(Ticket ticket) {
//        TicketEntity ticketEntity = modelMapper.map(ticket, TicketEntity.class);
        ticketDao.addTicket(modelMapper.map(ticket, TicketEntity.class));
//        ticketDao.addTicket(ticketEntity);
//        return modelMapper.map(ticketEntity, Ticket.class);
    }

    @Override
    public void addGuestTicket(TicketItem ticketItem) {

        User user = new User();
        user.setName(ticketItem.getName());
        user.setSurname(ticketItem.getSurName());
        user.setBirthDay(ticketItem.getBirthDay());

        LocalTime departureTime = ticketItem.getDepartureTime();
        LocalDate departureDate = ticketItem.getDepartureDate();
        LocalDateTime departureDateTime = departureDate.toLocalDateTime(departureTime);

        List<Route> routes = routeService.findByStationNames(ticketItem.getStationFrom(), ticketItem.getStationTo());
        List<Schedule> schedules = scheduleService.findScheduleByStations(routes, ticketItem.getStationTo(), ticketItem.getStationFrom());
        int departureScheduleId = 0;
        Schedule departureSchedule = null;
        for (Schedule item : schedules) {
            if (item.getDepartureTime().equals(departureTime)) {
                departureScheduleId = item.getId();
                departureSchedule = item;
                break;///todo bad
            }
        }
        if (departureScheduleId == 0) {
            //throw exc
        }

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setDepartureDateTime(departureDateTime);
        Train departureTrain = trainService.findByNumber(ticketItem.getTrainNumber());
        ticket.setTrain(departureTrain);
        ticket.setDepartureSchedule(departureSchedule);

        TicketEntity ticketEntity = modelMapper.map(ticket, TicketEntity.class);
        ticketEntity.setTrainEntity(modelMapper.map(ticket.getTrain(), TrainEntity.class));
        ticketEntity.setUserEntity(modelMapper.map(ticket.getUser(), UserEntity.class));
        ticketDao.addTicket(ticketEntity);
    }

    /**
     * Method make simple validation of ticket for not empty fields and for elements existing in database
     *
     * @param stationFrom
     * @param stationTo
     * @param departureDate
     * @param departureTime
     * @param trainNumber
     * @return true, if ticket fields is not empty and stations and train was found in dataBase
     */
    @Override
    public boolean simpleRouteValidation(String stationFrom, String stationTo, String departureDate, String departureTime, int trainNumber) {
        if (null == stationFrom || stationFrom.equals("") || null == stationTo || stationTo.equals("") || null == departureDate || departureDate.equals("") || null == trainService.findByNumber(trainNumber) || trainService.findByNumber(trainNumber).equals("")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Method create TicketItem from values, received from Front as ticket data
     *
     * @param departureDate date of train departure as String
     * @param departureTime time of train departure as String
     * @param birthDay      of User - ticketholder
     * @param name          of User - ticketholder
     * @param surName       of User - ticketholder
     * @param trainNumber   for train
     * @param stationFrom
     * @param stationTo     and
     * @return it.
     */
    @Override
    public TicketItem createTicketItem(String departureDate, String departureTime, String birthDay, String name, String surName, int trainNumber, String stationFrom, String stationTo) {

        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("MM/dd/yyyy");
        LocalDate departureDateFormatted = LocalDate.parse(departureDate, dateFormatter);
        LocalTime departureTimeFormatted = LocalTime.parse(departureTime);
        DateTimeFormatter birthDayDateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        LocalDate birthDayFormatted = LocalDate.parse(birthDay, birthDayDateFormatter);

        TicketItem ticketItem = new TicketItem();
        ticketItem.setBirthDay(birthDayFormatted);
        ticketItem.setDepartureDate(departureDateFormatted);
        ticketItem.setDepartureTime(departureTimeFormatted);
        ticketItem.setName(name);
        ticketItem.setSurName(surName);
        ticketItem.setTrainNumber(trainNumber);
        ticketItem.setStationFrom(stationFrom);
        ticketItem.setStationTo(stationTo);

        return ticketItem;
    }

    @Override
    public Ticket createTicket(String departureDate, String departureTime, int trainNumber, String stationFrom, String stationTo, User user) {

        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("MM/dd/yyyy");
        LocalDate departureDateFormatted = LocalDate.parse(departureDate, dateFormatter);
        LocalTime departureTimeFormatted = LocalTime.parse(departureTime);
        LocalDateTime departureDateTime = departureDateFormatted.toLocalDateTime(departureTimeFormatted);

        Train departureTrain = trainService.findByNumber(trainNumber);

        List<Route> routes = routeService.findByStationNames(stationFrom, stationTo);
        List<Schedule> schedules = scheduleService.findScheduleByStations(routes, stationTo, stationFrom);
        int departureScheduleId = 0;
        Schedule departureSchedule = null;
        for (Schedule item : schedules) {
            if (item.getDepartureTime().equals(departureTimeFormatted)) {
                departureScheduleId = item.getId();
                departureSchedule = item;
                break;///todo bad
            }
        }
        if (departureScheduleId == 0) {
            //throw exc
        }

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setDepartureDateTime(departureDateTime);
        ticket.setTrain(departureTrain);
        ticket.setDepartureSchedule(departureSchedule);

        TicketEntity ticketEntity = modelMapper.map(ticket, TicketEntity.class);
        ticketEntity.setTrainEntity(modelMapper.map(ticket.getTrain(), TrainEntity.class));
        ticketEntity.setUserEntity(modelMapper.map(ticket.getUser(), UserEntity.class));
        ticketDao.addTicket(ticketEntity);
        return ticket;
    }

    /**
     * Method take two parameters
     *
     * @param departureDate departure date of train in ticket
     * @param departureTime departure time of train in ticket
     *           and compares it with current date and time, to check that:
     *           date is today or Farther;
     *           user have 10 minutes before train depart.
     * @return {@code boolean true} if departure date and time more than 10 minutes of today and Farther
     */
    @Override
    public boolean weHaveTenMinutes(String departureDate, String departureTime) {

        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("MM/dd/yyyy");
        LocalDate departureDateFormatted = LocalDate.parse(departureDate, dateFormatter);
        LocalTime departureTimeFormatted = LocalTime.parse(departureTime);
        if (departureDateFormatted.isAfter(LocalDate.now()) || departureDateFormatted.isEqual(LocalDate.now())) {

            return departureTimeFormatted.isAfter(LocalTime.now().plusMinutes(10));
        }
        return false;
    }
}
