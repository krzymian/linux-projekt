package com.krzymianowski.hotelfinder.service;

import com.krzymianowski.hotelfinder.model.Hotel;
import com.krzymianowski.hotelfinder.model.repository.HotelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    public List<Hotel> findAllOrderByCountry() {
        return hotelsRepository.findAllByOrderByCountryNameAsc();
    }

    public List<Hotel> findAllByCountryOrderByCity(String countryName) {
        return hotelsRepository.findAllByCountryNameOrderByCityNameAsc(countryName);
    }

    public Page<Hotel> searchHotel(String country, String city, int size, int page, String sortBy, String direction) {
        direction = direction.toLowerCase();
        sortBy = sortBy.toLowerCase();

        PageRequest pageRequest = PageRequest.of(page, size, sortOrders(sortBy, direction));

        Page<Hotel> nextPage;
        if (!country.equals("") && !country.equals("all")) {
            if (!city.equals("") && !city.equals("all"))
                nextPage = hotelsRepository.findAllByCountryNameAndCityName(country, city, pageRequest);
            else nextPage = hotelsRepository.findAllByCountryName(country, pageRequest);
        } else nextPage = hotelsRepository.findAll(pageRequest);

        nextPage = formatLinks(nextPage);
        return nextPage;

    }

    private Sort sortOrders(String sortBy, String direction) {
        Sort sort;
        switch (sortBy) {
            case "stars":
                if (direction.equals("asc"))
                    sort = Sort.by(
                            new Sort.Order(Sort.Direction.ASC, "stars"),
                            new Sort.Order(Sort.Direction.ASC, "price"),
                            new Sort.Order(Sort.Direction.ASC, "hotelName"));
                else sort = Sort.by(
                        new Sort.Order(Sort.Direction.DESC, "stars"),
                        new Sort.Order(Sort.Direction.ASC, "price"),
                        new Sort.Order(Sort.Direction.ASC, "hotelName"));
                break;
            case "price":
                if (direction.equals("asc"))
                    sort = Sort.by(
                            new Sort.Order(Sort.Direction.ASC, "price"),
                            new Sort.Order(Sort.Direction.DESC, "stars"),
                            new Sort.Order(Sort.Direction.ASC, "hotelName"));
                else sort = Sort.by(
                        new Sort.Order(Sort.Direction.DESC, "price"),
                        new Sort.Order(Sort.Direction.DESC, "stars"),
                        new Sort.Order(Sort.Direction.ASC, "hotelName"));
                break;
            default:
                sort = Sort.by(
                        new Sort.Order(Sort.Direction.DESC, "stars"),
                        new Sort.Order(Sort.Direction.ASC, "price"),
                        new Sort.Order(Sort.Direction.ASC, "hotelName"));
                break;
        }
        return sort;
    }


    private Page<Hotel> formatLinks(Page<Hotel> hotels){
        for (Hotel p : hotels) {
            String url = p.getUrl();
            String shortUrl = "unknown";
            if (url != null && !url.equals("")) {
                if (url.indexOf("www") > 0) {
                    shortUrl = url.substring(url.indexOf("www"));
                    if (shortUrl.indexOf("/") > 0) shortUrl = shortUrl.substring(0, shortUrl.indexOf("/"));
                } else if (url.indexOf("//") > 0) {
                    shortUrl = "www." + url.substring(url.indexOf("//") + 2);
                    if (shortUrl.indexOf("/") > 0) shortUrl = shortUrl.substring(0, shortUrl.indexOf("/"));
                }
            }
            p.setShortUrl(shortUrl);
        }
        return hotels;
    }

}
