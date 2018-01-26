package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Arrays;

@Controller
@RequestMapping("/rejestracja")
public class rejestracjaController {

    @Autowired
    UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping
    public String rejestracja(Model model) {
        model.addAttribute("user", new User());
        return "rejestracja";
    }

    @PostMapping()
    public String register(@ModelAttribute @Valid User rawUser, BindingResult errors){
        if (errors.hasErrors()){
            System.out.println("errors");
            return "redirect:/rejestracja";
        }else{
            System.out.println(rawUser.toString());

            User user = new User(
                    rawUser.getFirstName(),
                    rawUser.getLastName(),
                    rawUser.getEmail(),
                    passwordEncoder.encode(rawUser.getPassword()),
                    Arrays.asList(
                            new Role("ROLE_USER")));

            userService.saveNewUser(user);

            return "redirect:/";
        }
    }
}
