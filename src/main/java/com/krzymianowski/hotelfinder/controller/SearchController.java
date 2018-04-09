package com.krzymianowski.hotelfinder.controller;

import com.krzymianowski.hotelfinder.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchController {

    @Autowired
    private HotelService hotelService;

    @GetMapping("/search")
    public ModelAndView searchPage(@RequestParam(defaultValue = "1") int page) {
        if (page < 1) page = 1;
        ModelAndView modelAndView = new ModelAndView("search-page");
        modelAndView.addObject(
                "hotels",
                hotelService.pageHotels(PageRequest.of(page-1, 10)));
        return modelAndView;
    }
}
