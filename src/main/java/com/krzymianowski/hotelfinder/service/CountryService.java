package com.krzymianowski.hotelfinder.service;

import com.krzymianowski.hotelfinder.model.Country;
import com.krzymianowski.hotelfinder.model.Hotel;
import com.krzymianowski.hotelfinder.model.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private HotelService hotelService;

    private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public void addCountry(Country country) {
        countryRepository.save(country);
    }

    public List<String> getDistinctCountries() {
        List<Hotel> hotels = hotelService.findAllOrderByCountry().stream().filter(distinctByKey(Hotel::getCountryName)).collect(Collectors.toList());
        return hotels.stream().map(Hotel::getCountryName).collect(Collectors.toList());
    }

    public List<String> getDistinctCities(String countryName) {
        List<Hotel> hotels = hotelService.findAllByCountryOrderByCity(countryName).stream().filter(distinctByKey(Hotel::getCityName)).collect(Collectors.toList());
        return hotels.stream().map(Hotel::getCityName).collect(Collectors.toList());
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }
}
