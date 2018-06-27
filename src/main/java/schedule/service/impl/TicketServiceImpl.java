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
import schedule.dao.api.TicketDao;
import schedule.entity.TicketEntity;
import schedule.entity.TrainEntity;
import schedule.entity.UserEntity;
import schedule.exception.CustomServiceException;
import schedule.model.*;
import schedule.service.api.RouteService;
import schedule.service.api.ScheduleService;
import schedule.service.api.TicketService;
import schedule.service.api.TrainService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {


    private ModelMapper modelMapper;
    private TicketDao ticketDao;
    private TrainService trainService;
    private ScheduleService scheduleService;
    private RouteService routeService;

    @Override
    public Ticket createGuestTicket(TicketItem ticketItem) throws CustomServiceException {

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
                break;
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
        return ticket;
    }

    public String ticketSimpleValidation(String stationTo, String stationFrom, String depTime, String depDate, String name, String surname, String birthDate, String trainNumber) throws CustomServiceException {
        StringBuilder validationInfo = new StringBuilder();
        if (stationTo.equals("") || stationTo == null || stationFrom.equals("") || null == stationFrom) {
            validationInfo.append("Wrong station.").append("<br>");
        }
        if (null == trainService.findByNumber(Integer.parseInt(trainNumber)) || trainNumber.equals("")) {
            validationInfo.append("Wrong train.").append("<br>");
        }
        if (depDate.equals("") || depDate == null || depTime.equals("") || depTime == null) {
            validationInfo.append("Wrong date/time.").append("<br>");
        }
        if (name.equals("") || surname.equals("") || birthDate.equals("")) {
            validationInfo.append("Wrong user data.").append("<br>");
        }
        return validationInfo.toString();
    }

    @Override
    public List<Ticket> findByUserName(String username) {

        List<TicketEntity> ticketEntity = ticketDao.findByUserName(username);
        List<Ticket> ticketList = new ArrayList<>();
        for (TicketEntity item : ticketEntity) {
            ticketList.add(modelMapper.map(item, Ticket.class));
        }
        return ticketList;
    }

    /**
     * Method create {@link TicketItem} from values, received from Front as ticket data
     * formating date and time first from String to joda.time format
     *
     * @param departureDate
     * @param departureTime
     * @param birthDay
     * @param name
     * @param surName
     * @param trainNumber
     * @param stationFrom
     * @param stationTo
     * @return new instance of TicketItem
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

    /**
     * Method check number of vacant seats on train. Format data/time first, search {@link Route}'s
     * and {@link Schedule}'s, create {@link Ticket} from @params
     *
     * @param departureDate
     * @param departureTime
     * @param stationFrom
     * @param stationTo
     * @param trainNumber   and then search train with ticket on it.
     * @return boolean (true if empty seats) by compare purchased tickets and seats on this train
     */
    @Override
    public boolean isVacantSeatsOnTrain(String departureDate, String departureTime, String stationFrom, String stationTo, int trainNumber) throws CustomServiceException {
        LocalDateTime departureDateTime = dateTimeBuilder(departureDate, departureTime);
        Train departureTrain = trainService.findByNumber(trainNumber);
        LocalTime departureTimeFormatted = LocalTime.parse(departureTime);

        List<Route> routes = routeService.findByStationNames(stationFrom, stationTo);
        List<Schedule> schedules = scheduleService.findScheduleByStations(routes, stationTo, stationFrom);
        int departureScheduleId = 0;
        Schedule departureSchedule = null;
        for (Schedule item : schedules) {
            if (item.getDepartureTime().equals(departureTimeFormatted)) {
                departureScheduleId = item.getId();
                departureSchedule = item;
                break;
            }
        }
        if (departureScheduleId == 0) {
            //throw exc
        }

        Ticket ticket = new Ticket();
        ticket.setDepartureDateTime(departureDateTime);
        ticket.setTrain(departureTrain);
        ticket.setDepartureSchedule(departureSchedule);

        TicketEntity ticketEntity = modelMapper.map(ticket, TicketEntity.class);
        ticketEntity.setTrainEntity(modelMapper.map(ticket.getTrain(), TrainEntity.class));

        return ticketDao.remainedElseVacantSeats(departureTrain.getSeats(), ticketEntity);
    }

    /**
     * Method create ticket for existing {@link User}, take data from Front-end.
     *
     * @param departureDate
     * @param departureTime
     * @param trainNumber
     * @param stationFrom
     * @param stationTo
     * @param user          Format data/time first, search {@link Route}'s
     *                      and {@link Schedule}'s, create {@link Ticket} if it already not existed in database
     * @return null if ticket with preset @params already exist, return {@link Ticket} if not
     */
    @Override
    public Ticket createTicketForUser(String departureDate, String departureTime, int trainNumber, String stationFrom, String stationTo, User user) throws CustomServiceException {
        LocalDateTime departureDateTime = dateTimeBuilder(departureDate, departureTime);
        LocalTime departureTimeFormatted = LocalTime.parse(departureTime);

        Train departureTrain = trainService.findByNumber(trainNumber);

        List<Route> routes = routeService.findByStationNames(stationFrom, stationTo);
        List<Schedule> schedules = scheduleService.findScheduleByStations(routes, stationTo, stationFrom);
        int departureScheduleId = 0;
        Schedule departureSchedule = null;
        for (Schedule item : schedules) {
            if (item.getDepartureTime().equals(departureTimeFormatted)) {
                departureScheduleId = item.getId();
                departureSchedule = item;
                break;
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

        boolean sameTicketExist = ticketDao.findTicket(ticketEntity);

        if (sameTicketExist) {
            return null;
        } else {
            ticketDao.addTicket(ticketEntity);
            return ticket;
        }
    }

    /**
     * Method take two parameters
     *
     * @param departureDate departure date of train in ticket
     * @param departureTime departure time of train in ticket
     *                      and compares it with current date and time, to check that:
     *                      date is today or Farther;
     *                      user have 10 minutes before train depart.
     * @return {@code boolean true} if departure date and time more than 10 minutes of today and Farther
     */
    @Override
    public boolean weHaveTenMinutes(String departureDate, String departureTime) {

        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("MM/dd/yyyy");
        LocalDate departureDateFormatted = LocalDate.parse(departureDate, dateFormatter);
        LocalTime departureTimeFormatted = LocalTime.parse(departureTime);
        if (departureDateFormatted.isAfter(LocalDate.now())) {
            return true;
        } else return departureDateFormatted.isEqual(LocalDate.now()) &&
                departureTimeFormatted.isAfter(LocalTime.now().plusMinutes(10));
    }

    LocalDateTime dateTimeBuilder(String departureDate, String departureTime) {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("MM/dd/yyyy");
        LocalDate departureDateFormatted = LocalDate.parse(departureDate, dateFormatter);
        LocalTime departureTimeFormatted = LocalTime.parse(departureTime);
        return departureDateFormatted.toLocalDateTime(departureTimeFormatted);
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setTicketDao(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @Autowired
    public void setTrainService(TrainService trainService) {
        this.trainService = trainService;
    }

    @Autowired
    public void setScheduleService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @Autowired
    public void setRouteService(RouteService routeService) {
        this.routeService = routeService;
    }
}
