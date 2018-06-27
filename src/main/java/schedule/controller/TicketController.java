package schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import schedule.controller.model.TicketItem;
import schedule.exception.CustomServiceException;
import schedule.model.Ticket;
import schedule.model.User;
import schedule.service.api.TicketService;
import schedule.service.api.UserService;

import java.util.Locale;

@Controller
@RequestMapping(value = "/ticket")
public class TicketController extends BaseController {

    private TicketService ticketService;
    private UserService userService;

    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public ModelAndView buyTicket(Locale locale) {
        ModelAndView modelAndView = new ModelAndView("buy-ticket");
//        modelAndView.addObject("ticket", new User());
        modelAndView.addObject("pageTitle", getMessage("page.title.buy-ticket", locale));

        return modelAndView;
    }

    /**
     * first controller simply validate received data for null and empty values with <m>validation</m> and return message on page if it's true.
     *
     * @param stationFrom
     * @param stationTo
     * @param departureDate
     * @param departureTime
     * @param train
     * @param name
     * @param surName
     * @param birthDay
     * @param locale        Than check for enough time (more than 10 min) and vacant seats have. Search future ticketholder in database. if false - create guest profile and ticket for him. Check that user already have ticket with request parameters or not. If it pass through all the checks, {@link schedule.model.Ticket} will be created
     * @return view with message
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView saveTicket(@RequestParam("stationFrom") String stationFrom,
                                   @RequestParam("stationTo") String stationTo,
                                   @RequestParam("departureDate") String departureDate,
                                   @RequestParam("departureTime") String departureTime,
                                   @RequestParam("trainNumber") String train,
                                   @RequestParam("name") String name,
                                   @RequestParam("surName") String surName,
                                   @RequestParam("birthDay") String birthDay,
                                   Locale locale) throws CustomServiceException {
        ModelAndView modelAndView;
        String validation = ticketService.ticketSimpleValidation(stationTo, stationFrom, departureTime, departureDate, name, surName, birthDay, train);
        if (validation.length() != 0) {
            modelAndView = new ModelAndView("buy-ticket");
            return modelAndView.addObject("message", validation);
        }

        int trainNumber = Integer.parseInt(train);
        Ticket ticket;

        boolean isTimeEnough = ticketService.weHaveTenMinutes(departureDate, departureTime);
        boolean isVacantSeatsOnTrain = ticketService.isVacantSeatsOnTrain(departureDate, departureTime, stationFrom, stationTo, trainNumber);
        //check for time left before departure
        if (!isTimeEnough) {
            modelAndView = new ModelAndView("buy-ticket");
            modelAndView.addObject("message", getMessage("message.ticket.past.date.or.not.enough.time", locale));
            return modelAndView;
            //check for vacant seats on train
        } else if (!isVacantSeatsOnTrain) {
            modelAndView = new ModelAndView("buy-ticket");
            modelAndView.addObject("message", getMessage("message.ticket.no.vacant.seats", locale));
            return modelAndView;
        } else {
            //search future ticketholder in database. if false - we create guest profile and ticket for him
            User existingTicketHolder = userService.findUserByNameSurnameBirthDay(name, surName, birthDay);

            if (existingTicketHolder == null) {
                // always create new guest profile and ticket for him
                TicketItem ticketItem = ticketService.createTicketItem(departureDate, departureTime, birthDay, name, surName, trainNumber, stationFrom, stationTo);
                ticket = ticketService.createGuestTicket(ticketItem);
            } else {
                //if user already have ticket with request parameters, method returns null
                ticket = ticketService.createTicketForUser(departureDate, departureTime, trainNumber, stationFrom, stationTo, existingTicketHolder);
            }
        }

        modelAndView = new ModelAndView("buy-ticket");
        modelAndView.addObject("pageTitle", getMessage("page.title.buy-ticket", locale));
        if (ticket == null) {
            modelAndView.addObject("message", getMessage("message.ticket.already.have", locale));
        } else {
            modelAndView.addObject("message", getMessage("message.ticket.buy", locale));
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
