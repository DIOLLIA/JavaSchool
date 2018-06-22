package schedule.controller;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
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
import schedule.controller.model.ScheduleItem;
import schedule.model.Role;
import schedule.model.Route;
import schedule.model.Schedule;
import schedule.model.User;
import schedule.service.api.RouteService;
import schedule.service.api.ScheduleService;
import schedule.service.api.StationService;
import schedule.service.api.UserService;
import schedule.util.MyValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Controller
public class HomeController extends BaseController {

    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private ScheduleService scheduleService;
    private RouteService routeService;
    private StationService stationService;
    private Validator validator;

    /**
     * @param locale
     * @return homepage of application for appropriate ROLE
     */
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

    /**
     * @param locale
     * @return about.jsp page with application info
     */
    @RequestMapping(value = "/about")
    public ModelAndView aboutPage(Locale locale) {
        ModelAndView modelAndView = new ModelAndView("about");
        modelAndView.addObject("pageTitle", getMessage("page.title.about", locale));
        return modelAndView;
    }

    /**
     * @param error
     * @param logout
     * @param locale
     * @return login view for application visitors with login\password form
     */
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

    /**
     * @return registration form for visitors
     */
    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public ModelAndView signUp() {
        return new ModelAndView("sign-up");
    }

    /**
     * method takes parameters for new user registration
     *
     * @param email
     * @param name
     * @param surname
     * @param password
     * @param birthDayString
     * @param locale         with USER_ROLE if successes
     * @return login form if new user successfully created
     */
    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ModelAndView register(@RequestParam("email") String email,
                                 @RequestParam("name") String name,
                                 @RequestParam("surname") String surname,
                                 @RequestParam("password") String password,
                                 @RequestParam("birthDay") String birthDayString,
                                 Locale locale) {
        ModelAndView modelAndView;
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

            MyValidator myValidator = new MyValidator();
            Set<ConstraintViolation<Object>> validationSet = myValidator.validate(newUser, validator);
            if (validationSet.isEmpty()) {
                userService.addUser(newUser);
                modelAndView = new ModelAndView("sign-in");
                modelAndView.addObject("msg", getMessage("message.user.create.success", locale));
            } else {
                StringBuilder msg = new StringBuilder();
                for (ConstraintViolation<Object> cv : validationSet) {
                    msg.append(cv.getMessage()).append("<br>");
                }
                modelAndView = new ModelAndView("sign-up");
                modelAndView.addObject("msg", msg.toString());
            }
            return modelAndView;


        }
    }

    /**
     * @param locale
     * @return personal data for registered user
     */
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

    /**
     * @return access Denied view
     */
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accessDenied() {
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

    /**
     * method search schedule by present params
     *
     * @param stationFrom
     * @param stationTo
     * @param searchDate
     * @param searchTime
     * @return view with search results satisfying request
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView findScheduleResult(@RequestParam(name = "stationFrom") String stationFrom, @RequestParam(name = "stationTo") String stationTo, @RequestParam(name = "searchDate") String searchDate, @RequestParam(name = "searchTime") String searchTime) {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("MM/dd/yyyy");
        LocalDate date = LocalDate.parse(searchDate, dateFormatter);

        LocalTime requestTime = LocalTime.parse(searchTime);
        String message;
        List<Route> routes = routeService.findByStationNames(stationFrom, stationTo);

        ModelAndView modelAndView = new ModelAndView("home");
        List<Schedule> schedules = scheduleService.findScheduleByStations(routes, stationTo, stationFrom);

        List<ScheduleItem> scheduleItems = new ArrayList<>();
        for (Schedule scheduleOne : schedules) {
            String stationOneName = scheduleOne.getStationName().getStationName();
            if (stationOneName.equals(stationTo)) {
                continue;
            }
            for (Schedule scheduleTwo : schedules) {
                String stationTwoName = scheduleTwo.getStationName().getStationName();
                if (scheduleOne.getRouteDailyId() != scheduleTwo.getRouteDailyId() ||
                        scheduleOne.getRouteStationIndex() >= scheduleTwo.getRouteStationIndex()) {
                    continue;
                }
                if (!requestTime.isBefore(scheduleOne.getDepartureTime())) {
                    break;
                } else {
                    ScheduleItem scheduleItem = new ScheduleItem();
                    scheduleItem.setDepartureTime(scheduleOne.getDepartureTime());
                    scheduleItem.setArrivalTime(scheduleTwo.getArrivalTime());

                    scheduleItem.setStationOfDeparture(stationOneName);
                    scheduleItem.setStationOfArrival(stationTwoName);
                    scheduleItem.setTrainNumber(scheduleOne.getTrainNumber().getNumberOfTrain());
                    scheduleItems.add(scheduleItem);
                }
            }
        }
        if (scheduleItems.isEmpty()) {
            message = "No trains found from " + searchTime + " on " + searchDate + ".";
        } else {
            message = "Search result for departure date " + date;
        }
        modelAndView.addObject("message", message);
        modelAndView.addObject("searchResult", scheduleItems);
        return modelAndView;
    }

    /**
     * @param locale
     * @return view with trains list of request station
     */
    @RequestMapping(value = "/searchTrainOnStation", method = RequestMethod.GET)
    public ModelAndView searchOnStation(Locale locale) {

        ModelAndView modelAndView = new ModelAndView("train-list-by-station");
        modelAndView.addObject("pageTitle",
                getMessage("page.title.search-by-station", locale));

        return modelAndView;
    }

    /**
     * @param station
     * @param locale
     * @return view with trains list of request @param station
     */
    @RequestMapping(value = "/searchTrainOnStation", method = RequestMethod.POST)
    public ModelAndView searchOnStationResult(@RequestParam(name = "stationFrom") String station,
                                              Locale locale) {

        ModelAndView modelAndView = new ModelAndView("train-list-by-station");
        modelAndView.addObject("pageTitle",
                getMessage("page.title.search-by-station", locale));

        List<Schedule> listOfTrainsByStation = scheduleService.findScheduleByStation(
                stationService.findByName(station));

        List<Schedule> scheduleItems = new ArrayList<>(listOfTrainsByStation);
        String msg = "Results for " + station + " :";

        modelAndView.addObject("scheduleItems", scheduleItems);
        modelAndView.addObject("msg", msg);

        return modelAndView;
    }

    @Autowired
    public void setScheduleService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @Autowired
    public void setStationService(StationService stationService) {
        this.stationService = stationService;
    }

    @Autowired
    public void setRouteService(RouteService routeService) {
        this.routeService = routeService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setValidator(Validator validator) {
        this.validator = validator;
    }
}