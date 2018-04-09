package com.krzymianowski.hotelfinder.service;

import com.krzymianowski.hotelfinder.model.Hotel;
import com.krzymianowski.hotelfinder.model.repository.HotelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public Page<Hotel> pageHotels(PageRequest page) {
        Page<Hotel> nextPage = hotelsRepository.findAll(page);
        for (Hotel p : nextPage) {
            String url = p.getUrl();
//            System.out.println(url);
            String shortUrl = "unknown";
            if (url != null && url != "") {
                if (url.indexOf("www") > 0) {
                    shortUrl = url.substring(url.indexOf("www"));
                    if (shortUrl.lastIndexOf("/") > 0) shortUrl = shortUrl.substring(0, shortUrl.lastIndexOf("/"));
                } else if (url.indexOf("//") > 0) {
                    shortUrl = "www." + url.substring(url.indexOf("//") + 2);
                    if (shortUrl.lastIndexOf("/") > 0) shortUrl = shortUrl.substring(0, shortUrl.lastIndexOf("/"));
                }
            }
            p.setShortUrl(shortUrl);
        }
        return nextPage;
    }
}
