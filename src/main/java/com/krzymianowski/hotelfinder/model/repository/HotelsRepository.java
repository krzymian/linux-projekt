package com.krzymianowski.hotelfinder.model.repository;

import com.krzymianowski.hotelfinder.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelsRepository extends JpaRepository<Hotel, Integer> {
}
