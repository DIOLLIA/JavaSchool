package schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import schedule.controller.model.TicketItem;
import schedule.model.Ticket;
import schedule.model.User;
import schedule.service.api.TicketService;
import schedule.service.api.TrainService;
import schedule.service.api.UserService;

@Controller
@RequestMapping(value = "/ticket")
public class TicketController extends BaseController {

    private TicketService ticketService;
    private UserService userService;
    private TrainService trainService;

    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public ModelAndView buyTicket() {
        ModelAndView modelAndView = new ModelAndView("buy-ticket");
        modelAndView.addObject("ticket", new User());
        modelAndView.addObject("pageTitle", getMessage("page.title.buy-ticket", DEFAULT_LOCALE));

        return modelAndView;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView saveTicket(@RequestParam("stationFrom") String stationFrom, @RequestParam("stationTo") String stationTo, @RequestParam("departureDate") String departureDate, @RequestParam("departureTime") String departureTime, @RequestParam("trainNumber") int trainNumber, @RequestParam("name") String name, @RequestParam("surName") String surName, @RequestParam("birthDay") String birthDay) {

        boolean routeValid = ticketService.simpleRouteValidation(stationFrom, stationTo, departureDate, departureTime, trainNumber);
        boolean userValid = userService.simpleUserValidation(name, surName, birthDay);

        if (userValid) {
            User ticketHolder = userService.findUserByNameSurnameBirthDay(name, surName, birthDay);

            if (null == ticketHolder && routeValid) {
                TicketItem ticketItem = ticketService.createTicketItem(departureDate, departureTime, birthDay, name, surName, trainNumber, stationFrom, stationTo);
               ticketService.addGuestTicket(ticketItem);
            }
            else
                {
                Ticket ticket = ticketService.createTicket(departureDate, departureTime, trainNumber, stationFrom, stationTo, ticketHolder);
//                ticketService.addUserTicket(ticket);
            }
        }
        else {
            //throw exception
        }
     /*   DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("MM/dd/yyyy");
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

        ticketService.addGuestTicket(ticketItem);*/

        ModelAndView modelAndView = new ModelAndView("buy-ticket");
        modelAndView.addObject("pageTitle", getMessage("page.title.buy-ticket", DEFAULT_LOCALE));
        modelAndView.addObject("message", getMessage("message.ticket.buy", DEFAULT_LOCALE));

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
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
