package com.ntconsult.adapter.outbound.repository;

import com.ntconsult.adapter.inbound.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepositoryJpa extends JpaRepository<Review, Long> {

    List<Review> findByHotelId(Long hotelId);
}
