package schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import schedule.model.User;
import schedule.service.api.TrainService;

@Controller
@RequestMapping(value = "/ticket")
public class TicketController {
    /*@Autowired
    private TicketService trainService;*/
    @Autowired
    private TrainService trainService;

    //todo вешать номер поезда в соотв поле
    @RequestMapping(value = "/by", method = RequestMethod.GET)
    public ModelAndView byTicket(/*@PathVariable(value = "train.id") int trainId*/) {
        ModelAndView modelAndView = new ModelAndView("byTicket");
        modelAndView.addObject("ticket", new User());
        //modelAndView.addObject("byTicket", trainService.get(trainId));
        modelAndView.addObject("pageTitle", "By ticket");

        return modelAndView;
    }
}
