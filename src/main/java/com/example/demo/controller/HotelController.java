package com.example.demo.controller;

import com.example.demo.model.Hotel;
import com.example.demo.model.repository.HotelRepository;
import com.example.demo.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    private HotelRepository hotelRepository;

    @GetMapping
    public List getUsers(){
        return hotelRepository.findAll();
    }
}
