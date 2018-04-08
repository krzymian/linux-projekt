package com.krzymianowski.hotelfinder.controller;


import com.krzymianowski.hotelfinder.model.User;
import com.krzymianowski.hotelfinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public ModelAndView showRegistrationPage() {
        ModelAndView modelAndView = new ModelAndView("register-page");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView registerUser(@Valid User user, BindingResult result, HttpServletRequest request, Principal loggedUser) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.setViewName("register-page");
            modelAndView.addObject("user", user);
            return modelAndView;
        } else {

            if (userService.createUser(user)) {
                if (loggedUser == null)
                    try {
                        request.login(user.getEmail(), user.getPassword());
                    } catch (ServletException e) {
                        e.printStackTrace();
                    }
                modelAndView.setViewName("redirect:/");
            } else {
                result.rejectValue("email", "error.user", "An account already exists for this email.");
                modelAndView.setViewName("register-page");
                modelAndView.addObject("user", user);
            }
            return modelAndView;
        }
    }
}