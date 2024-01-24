package web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import web.model.User;
import web.service.UserService;

@Controller
public class UserController {
    private UserService userService;

    public UserController() {

    }

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
    public ModelAndView hello(HttpServletResponse response) throws IOException {
        ModelAndView mav = new ModelAndView();
        List userList = userService.getAllUsers();
        mav.addObject("userList", userList);
        mav.setViewName("home");
        return mav;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public ModelAndView displayNewUserForm() {
        ModelAndView mav = new ModelAndView("addUser");
        mav.addObject("headerMessage", "Add new User");
        mav.addObject("user", new User());
        return mav;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ModelAndView saveNewUser(@ModelAttribute User user, BindingResult result) {
        ModelAndView mav = new ModelAndView("redirect:/home");

        boolean isAdded = userService.saveUser(user);
        if (isAdded) {
            mav.addObject("message", "New user added");
        } else {
            System.out.println("Don't save User");
        }

        return mav;
    }

    @RequestMapping(value = "/editUser/{id}", method = RequestMethod.GET)
    public ModelAndView displayEditUserForm(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("/editUser");
        User user = userService.getUserById(id);
        mav.addObject("headerMessage", "Edit old User");
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping(value = "/editUser/{id}", method = RequestMethod.POST)
    public ModelAndView saveEditedUser(@ModelAttribute User user, BindingResult result) {
        ModelAndView mav = new ModelAndView("redirect:/home");

        boolean isSaved = userService.saveUser(user);
        if (!isSaved) {
            System.out.println("Don't edit User");
        }

        return mav;
    }

    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.GET)
    public ModelAndView deleteUserById(@PathVariable Long id) {
        boolean isDeleted = userService.deleteUserById(id);
        ModelAndView mav = new ModelAndView("redirect:/home");
        return mav;

    }

}