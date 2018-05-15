package schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
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
}
