package com.krzymianowski.hotelfinder.model.repository;

import com.krzymianowski.hotelfinder.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

}
