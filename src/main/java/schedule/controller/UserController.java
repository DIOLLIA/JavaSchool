package schedule.controller;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import schedule.model.Role;
import schedule.model.User;
import schedule.service.api.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listOfUsers() {
        ModelAndView modelAndView = new ModelAndView("users-list");

        List<User> users = userService.getUsers();
        modelAndView.addObject("user", users);

        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addUserPage() {
        ModelAndView modelAndView = new ModelAndView("add-user");
        //todo why do you need it? think and rename if it's used.
        modelAndView.addObject("user", new User());

        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView createUser(@RequestParam("email") String email,
                                   @RequestParam("name") String name,
                                   @RequestParam("surname") String surname,
                                   @RequestParam("password") String password,
                                   @RequestParam("birthDay") String birthDayString,
                                   @RequestParam("role") String role,
                                   Locale locale) {
        if (!userService.findByLoginOrSurname(email).isEmpty()) {
            return new ModelAndView("add-user")
                    .addObject("msg",
                            getMessage("message.user.create.error.username-exist", locale, email));
        } else {
            //todo rename with appropriate name? What exactly the role? You created this var to store what role?
            Role role1 = new Role();

            if (role.equals("Admin")) {
                role1.setId(1);
            } else {
                role1.setId(2);
            }
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setName(name);
            newUser.setSurname(surname);
            newUser.setPassword(passwordEncoder.encode(password));
            newUser.setRole(role1);
            newUser.setBirthDay(LocalDate.parse(birthDayString));

            userService.addUser(newUser);

            ModelAndView modelAndView = new ModelAndView("add-user");
            modelAndView.addObject("message",
                    getMessage("message.admin.create-user.success", locale, role));

            return modelAndView;
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ModelAndView deleteUserPage(@ModelAttribute User id,
                                       Locale locale) {
        userService.addUser(id);
        List<User> users = userService.getUsers();

        ModelAndView modelAndView = new ModelAndView("users-list");
        modelAndView.addObject("users", users);
        modelAndView.addObject("message", getMessage("message.admin.delete-user.success", locale));

        return modelAndView;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ModelAndView findByLoginOrSurname(@RequestParam("loginOrSurname") String loginOrSurname,
                                             Locale locale) {
        List<User> list = userService.findByLoginOrSurname(loginOrSurname);
        ArrayList<User> searchResult = new ArrayList<>(list);
        String elementForSearch;
        ModelAndView modelAndView = new ModelAndView("users-list");
        if (loginOrSurname.contains("@")) {
            elementForSearch = "login";
        } else {
            elementForSearch = "surname";
        }
        if (searchResult.size() == 0) {
            modelAndView.addObject("message",
                    getMessage("message.admin.search-user-by-login.no-result", locale,
                            elementForSearch, loginOrSurname));
        } else {
            //todo redundant message. Don't delete search request from search input
            modelAndView.addObject("message",
                    getMessage("message.admin.search-user-by-login.result", locale, elementForSearch, loginOrSurname));
        }
        modelAndView.addObject("user", searchResult);

        return modelAndView;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

}
