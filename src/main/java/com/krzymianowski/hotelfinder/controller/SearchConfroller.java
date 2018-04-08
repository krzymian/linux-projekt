package com.krzymianowski.hotelfinder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchConfroller {

    @GetMapping("/search")
    public ModelAndView searchPage() {
        ModelAndView modelAndView = new ModelAndView("search-page");
        return modelAndView;
    }
}
