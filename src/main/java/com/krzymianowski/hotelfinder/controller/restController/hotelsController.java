package com.krzymianowski.hotelfinder.controller.restController;

import com.krzymianowski.hotelfinder.model.Hotel;
import com.krzymianowski.hotelfinder.model.repository.HotelsRepository;
import com.krzymianowski.hotelfinder.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class hotelsController{

    @Autowired
    private HotelService hotelService;

    @GetMapping
    public Page<Hotel> hotelsTest(@RequestParam(defaultValue = "0") int page){

        Page<Hotel> nextPage = hotelService.pageHotels(PageRequest.of(page,666));
        return nextPage;
    }
}
