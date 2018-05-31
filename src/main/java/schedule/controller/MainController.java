package schedule.controller;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import schedule.model.Role;
import schedule.model.User;
import schedule.service.api.UserService;

@Controller
public class MainController {

    @Autowired
    UserService userService;


    @RequestMapping(value = {"/", "/index"})
    public ModelAndView mainPage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView modelAndView = new ModelAndView();
        if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            modelAndView.setViewName("admin-home");
            modelAndView.addObject("pageTitle", "KudKuda-Master");
        } else {
            modelAndView.setViewName("home");
            modelAndView.addObject("pageTitle", "KudKuda Home");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/admin")
    public ModelAndView indexPage() {
        ModelAndView modelAndView = new ModelAndView("admin-home");
        modelAndView.addObject("pageTitle", "KudKuda Home");
        return modelAndView;
    }

    @RequestMapping(value = "/about")
    public ModelAndView aboutPage() {
        ModelAndView modelAndView = new ModelAndView("about");
        modelAndView.addObject("pageTitle", "About");
        return modelAndView;
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public ModelAndView signIn(@RequestParam(value = "error", required = false) String error,
                               @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView modelAndView = new ModelAndView("sign-in");

        if (error != null) {
            modelAndView.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            modelAndView.addObject("msg", "You've been logout successfully.");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public ModelAndView signUp() {
        ModelAndView modelAndView = new ModelAndView("sign-up");
        return modelAndView;
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ModelAndView register(@RequestParam("email") String email, @RequestParam("name") String name, @RequestParam("surname") String surname, @RequestParam("password") String password, @RequestParam("birthDay") String birthDayString) {

        Role role = new Role();
        role.setId(2);

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setName(name);
        newUser.setSurname(surname);
        newUser.setPassword(password);
        newUser.setRole(role);
        newUser.setBirthDay(LocalDate.parse(birthDayString));

        userService.addUser(newUser);
        ModelAndView modelAndView = new ModelAndView("home");
        return modelAndView;
    }

    //for 403 access denied page
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {

        ModelAndView model = new ModelAndView();

        //check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("username", userDetail.getUsername());
        }

        model.setViewName("403");
        return model;

    }
}