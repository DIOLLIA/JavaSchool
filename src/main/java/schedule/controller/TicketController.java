package schedule.controller;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import schedule.controller.model.TicketItem;
import schedule.model.User;
import schedule.service.api.TicketService;
import schedule.service.api.TrainService;

@Controller
@RequestMapping(value = "/ticket")
public class TicketController {

    private TicketService ticketService;

    private TrainService trainService;

    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public ModelAndView buyTicket() {
        ModelAndView modelAndView = new ModelAndView("buy-ticket");
        modelAndView.addObject("ticket", new User());
        modelAndView.addObject("pageTitle", "Buy ticket");

        return modelAndView;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView saveTicket(@RequestParam("stationFrom") String stationFrom, @RequestParam("stationTo") String stationTo, @RequestParam("departureDate") String departureDate, @RequestParam("departureTime") String departureTime, @RequestParam("trainNumber") int trainNumber, @RequestParam("name") String name, @RequestParam("surName") String surName, @RequestParam("birthDay") String birthDay) {

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

        ticketService.addGuestTicket(ticketItem);
        String message = "You get the ticket";
        ModelAndView modelAndView = new ModelAndView("buy-ticket");
        modelAndView.addObject("pageTitle", "Buy ticket");
        modelAndView.addObject("message", message);

        return modelAndView;
    }

    @Autowired
    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Autowired
    public void setTrainService(TrainService trainService) {
        this.trainService = trainService;
    }
}
