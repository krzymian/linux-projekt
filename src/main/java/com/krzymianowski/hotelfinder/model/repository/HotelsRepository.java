package com.krzymianowski.hotelfinder.model.repository;

import com.krzymianowski.hotelfinder.model.Hotel;
import org.hibernate.boot.model.source.spi.Sortable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelsRepository extends JpaRepository<Hotel, Integer> {

    List<Hotel> findAllByOrderByCountryNameAsc();

    List<Hotel> findAllByCountryName(String countryName);


    List<Hotel> findAllByCountryNameOrderByCityNameAsc(String countryName);

    Page<Hotel> findAllByCountryName(String countryName, Pageable pageable);

    Page<Hotel> findAllByCountryNameAndCityName(String countryName, String cityName, Pageable pageable);

}
