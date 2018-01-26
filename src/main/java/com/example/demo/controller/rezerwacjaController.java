package com.example.demo.controller;

import com.example.demo.model.Hotel;
import com.example.demo.model.Search;
import com.example.demo.model.User;
import com.example.demo.model.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/rezerwacja")
public class rezerwacjaController {

    @Autowired
    HotelRepository hotelRepository;

    @GetMapping
    public String rezerwacja(Model model) {
        model.addAttribute("search", new Search());
        return "rezerwacja";
    }

    @PostMapping
    public String szukaj(@ModelAttribute Search search, Model model){

        List<Hotel> hotels = hotelRepository.findAllByCountryAndCity(search.getCountry(), search.getCity());
        if (!hotels.isEmpty()){
            model.addAttribute("search", search);
            model.addAttribute("hotels", hotels);
            return "hotele";
        }else{
            return "redirect:/rezerwacja";
        }
    }
}

