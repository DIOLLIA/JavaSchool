package schedule.controller;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import schedule.model.Role;
import schedule.model.User;
import schedule.service.api.UserService;

import java.util.Locale;

@Controller
public class HomeController extends BaseController {

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = {"/", "/index"})
    public ModelAndView mainPage(Locale locale) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView modelAndView = new ModelAndView();
        if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            modelAndView.setViewName("admin-home");
            modelAndView.addObject("pageTitle", getMessage("page.title.admin", locale));
        } else {
            modelAndView.setViewName("home");
            modelAndView.addObject("pageTitle", getMessage("page.title.user", locale));
        }
        return modelAndView;
    }

    @RequestMapping(value = "/about")
    public ModelAndView aboutPage(Locale locale) {
        ModelAndView modelAndView = new ModelAndView("about");
        modelAndView.addObject("pageTitle", getMessage("page.title.about", locale));
        return modelAndView;
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public ModelAndView signIn(@RequestParam(value = "error", required = false) String error,
                               @RequestParam(value = "logout", required = false) String logout,
                               Locale locale) {
        ModelAndView modelAndView = new ModelAndView("sign-in");

        if (error != null) {
            modelAndView.addObject("error",
                    getMessage("message.login.error.invalid-username-or-pswd", locale));
        }

        if (logout != null) {
            modelAndView.addObject("msg", getMessage("message.logout.success", locale));
        }

        return modelAndView;
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public ModelAndView signUp() {
        return new ModelAndView("sign-up");
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ModelAndView register(@RequestParam("email") String email,
                                 @RequestParam("name") String name,
                                 @RequestParam("surname") String surname,
                                 @RequestParam("password") String password,
                                 @RequestParam("birthDay") String birthDayString,
                                 Locale locale) {
        if (!userService.findByLoginOrSurname(email).isEmpty()) {
            return new ModelAndView("sign-up").addObject("msg",
                    getMessage("message.user.create.error.username-exist", locale, email));
        } else {
            Role role = new Role();
            role.setId(2);

            User newUser = new User();
            newUser.setEmail(email);
            newUser.setName(name);
            newUser.setSurname(surname);
            newUser.setPassword(passwordEncoder.encode(password));
            newUser.setRole(role);
            newUser.setBirthDay(LocalDate.parse(birthDayString));

            userService.addUser(newUser);
            ModelAndView modelAndView = new ModelAndView("sign-in");
            modelAndView.addObject("msg", getMessage("message.user.create.success", locale));

            return modelAndView;
        }
    }

    @RequestMapping(value = "/userInfo")
    public ModelAndView userInfo(Locale locale) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        Object authorities = auth.getAuthorities();

        if (principal.toString().contains("anonymousUser") && authorities.toString().equals("[ROLE_ANONYMOUS]")) {
            return new ModelAndView("home")
                    .addObject("msg", getMessage("message.anonymous.profile", locale));
        }
        User user = userService.findByLoginOrSurname(((UserDetails) principal).getUsername()).get(0);
        ModelAndView modelAndView;
        if (authorities.toString().equals("[ROLE_ADMIN]")) {
            modelAndView = new ModelAndView("admin-profile");
        } else {
            modelAndView = new ModelAndView("personal-data");
        }
        modelAndView.addObject("user", user);

        return modelAndView;
    }

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

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}