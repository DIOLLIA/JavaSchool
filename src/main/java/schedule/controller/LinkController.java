package schedule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LinkController {

    @RequestMapping(value = "/")
    public ModelAndView mainPage() {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("pageTitle", "KudKuda Home Page");
        return modelAndView;
    }

    @RequestMapping(value = "/index")
    public ModelAndView indexPage() {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("pageTitle", "KudKuda Home");
        return modelAndView;
    }

    @RequestMapping(value = "/about")
    public ModelAndView aboutPage() {
        ModelAndView modelAndView = new ModelAndView("about");
        modelAndView.addObject("pageTitle", "About");
        return modelAndView;
    }

}
