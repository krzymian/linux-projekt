package com.krzymianowski.hotelfinder.controller;


import com.krzymianowski.hotelfinder.model.User;
import com.krzymianowski.hotelfinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> allUsers() {
        return userService.findAllUsers();
    }
}
