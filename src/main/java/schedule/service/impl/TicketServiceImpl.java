package schedule.service.impl;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schedule.controller.model.TicketItem;
import schedule.dao.impl.TicketDaoImpl;
import schedule.entity.TicketEntity;
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
    public Ticket addUserTicket(Ticket ticket) {
        TicketEntity ticketEntity = modelMapper.map(ticket, TicketEntity.class);
        ticketDao.addTicket(ticketEntity);
        return modelMapper.map(ticketEntity, Ticket.class);
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
        //Train departureTrain = trainService.findByNumber(ticketItem.getTrainNumber());
        Train departureTrain = new Train();
        ticket.setTrain(departureTrain);
        ticket.setDepartureSchedule(departureSchedule);

        TicketEntity ticketEntity = modelMapper.map(ticket, TicketEntity.class);
        ticketDao.addTicket(ticketEntity);
        // ticketDao.addModelTicket(ticket);
    }
}
