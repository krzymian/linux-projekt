package com.krzymianowski.hotelfinder.controller;

import com.krzymianowski.hotelfinder.service.CountryService;
import com.krzymianowski.hotelfinder.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SearchController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private CountryService countryService;

    @GetMapping("/search")
    public ModelAndView searchPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "all") String country,
            @RequestParam(defaultValue = "all") String city,
            @RequestParam(defaultValue = "price") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        if (page < 1) page = 1;
        if (size < 5) size = 5;

        Map<String, String> parameters = new HashMap<>();
        parameters.put("page", ""+page);
        parameters.put("size", ""+size);
        parameters.put("country", country);
        parameters.put("city", city);
        parameters.put("sortBy", sortBy);
        parameters.put("direction", direction);

        ModelAndView modelAndView = new ModelAndView("search-page");
        modelAndView.addObject("hotels",
                hotelService.searchHotel(country, city, size, page-1, sortBy, direction));
        modelAndView.addObject("countries",
                countryService.getAllCountries());
        modelAndView.addObject("parameters", parameters);
        return modelAndView;
    }
}
