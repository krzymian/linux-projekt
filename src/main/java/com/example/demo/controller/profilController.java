package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/profil")
public class profilController {

    @Autowired
    UserService userService;


    @GetMapping
    public String profil(Model model, Principal principal){

        User user = userService.getUserByEmail(principal.getName());
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("lastName", user.getLastName());
        model.addAttribute("tel", user.getTel());

        model.addAttribute("user", user);

        return "profil";
    }

    @PostMapping("/zapisz")
    public String updateUser(@ModelAttribute User user, Principal principal){
        User pUser = userService.getUserByEmail(principal.getName());
        pUser.setFirstName(user.getFirstName());
        pUser.setLastName(user.getLastName());
        pUser.setTel(user.getTel());

        System.out.println("test: "+user.toString());

        userService.updateUser(pUser);
        return "redirect:/";
    }
}
