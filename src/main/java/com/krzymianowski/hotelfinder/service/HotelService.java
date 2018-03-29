package com.krzymianowski.hotelfinder.service;

import com.krzymianowski.hotelfinder.model.Hotel;
import com.krzymianowski.hotelfinder.model.repository.HotelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelsRepository hotelsRepository;


    public void deleteAllHotels() {
        hotelsRepository.deleteAll();
    }

    public void saveHotel(Hotel hotel) {
        hotelsRepository.save(hotel);
    }

    public List<Hotel> findAllHotels() {
        return hotelsRepository.findAll();
    }
}
