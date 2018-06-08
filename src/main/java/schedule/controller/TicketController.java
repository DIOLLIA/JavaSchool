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
import schedule.service.api.UserService;

@Controller
@RequestMapping(value = "/ticket")
public class TicketController extends BaseController {

    private TicketService ticketService;
    private UserService userService;

    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public ModelAndView buyTicket() {
        ModelAndView modelAndView = new ModelAndView("buy-ticket");
//        modelAndView.addObject("ticket", new User());
        modelAndView.addObject("pageTitle", getMessage("page.title.buy-ticket", DEFAULT_LOCALE));

        return modelAndView;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView saveTicket(@RequestParam("stationFrom") String stationFrom,
                                   @RequestParam("stationTo") String stationTo,
                                   @RequestParam("departureDate") String departureDate,
                                   @RequestParam("departureTime") String departureTime,
                                   @RequestParam("trainNumber") int trainNumber,
                                   @RequestParam("name") String name,
                                   @RequestParam("surName") String surName,
                                   @RequestParam("birthDay") String birthDay) {

        Ticket ticket = null;
        ModelAndView modelAndView;

        boolean routeNotEmpty = ticketService.simpleRouteValidation(stationFrom, stationTo, departureDate, departureTime, trainNumber);
        boolean userNotEmpty = userService.simpleUserValidation(name, surName, birthDay);
        boolean isTimeEnough = ticketService.weHaveTenMinutes(departureDate, departureTime);

        if (!isTimeEnough) {
            modelAndView = new ModelAndView("buy-ticket");
            modelAndView.addObject("message", getMessage("message.ticket.past.date.or.not.enough.time", DEFAULT_LOCALE));
            return modelAndView;
        } else {
            if (userNotEmpty && routeNotEmpty) {
                User existingTicketHolder = userService.findUserByNameSurnameBirthDay(name, surName, birthDay);

                if (existingTicketHolder == null) {
                    TicketItem ticketItem = ticketService.createTicketItem(departureDate, departureTime, birthDay, name, surName, trainNumber, stationFrom, stationTo);
                    ticket = ticketService.createGuestTicket(ticketItem);
                } else {
                    ticket = ticketService.createTicketForUser(departureDate, departureTime, trainNumber, stationFrom, stationTo, existingTicketHolder);
                }
            } else {
                modelAndView = new ModelAndView("buy-ticket");
                modelAndView.addObject("message", getMessage("message.ticket.empty.fields", DEFAULT_LOCALE));
            }
        }
        modelAndView = new ModelAndView("buy-ticket");
        modelAndView.addObject("pageTitle", getMessage("page.title.buy-ticket", DEFAULT_LOCALE));
        if (ticket == null) {
            modelAndView.addObject("message", getMessage("message.ticket.already.have", DEFAULT_LOCALE));
        } else {
            modelAndView.addObject("message", getMessage("message.ticket.buy", DEFAULT_LOCALE));
        }
        return modelAndView;
    }

    @Autowired
    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
