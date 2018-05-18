package schedule.controller;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import schedule.model.Role;
import schedule.model.User;
import schedule.service.api.UserService;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list")
    public ModelAndView listOfUsers() {
        ModelAndView modelAndView = new ModelAndView("usersList");

        List<User> users = userService.getUsers();
        modelAndView.addObject("users", users);

        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addUserPage() {
        ModelAndView modelAndView = new ModelAndView("addUser");
        User user = new User();
        user.setName("hello");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/add2", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute(name = "user") User user) {

//        User user = null;
        ModelAndView modelAndView = new ModelAndView("usersList");
        userService.addUser(user);

        List<User> users = userService.getUsers();
        modelAndView.addObject("users", users);

        String message = "User was successfully added.";
        modelAndView.addObject("message", message);

        return modelAndView;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ModelAndView deleteUserPage(@ModelAttribute User id) {
        ModelAndView modelAndView = new ModelAndView("usersList");
        userService.addUser(id);

        List<User> users = userService.getUsers();
        modelAndView.addObject("users", users);

        String message = "User was successfully added.";
        modelAndView.addObject("message", message);

        return modelAndView;
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("username") String username, @RequestParam("password") String password) {
        boolean isValid;
        String msg;
        isValid = userService.userExist(username, password);
        if (isValid) {
            msg = "Welcome " + username + "!";
        } else {
            msg = "Incorrect username or password!";
        }
        ModelAndView modelAndView = new ModelAndView("signIn");
        modelAndView.addObject("msg", msg);
        return modelAndView;

    }

    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public ModelAndView signUp() {
        ModelAndView modelAndView = new ModelAndView("signIn");
        return modelAndView;
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public ModelAndView signIn() {
        ModelAndView modelAndView = new ModelAndView("signUp");
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
}
