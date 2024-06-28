package com.ntconsult.application.ports.out;

import com.ntconsult.adapter.inbound.entity.Hotel;
import com.ntconsult.adapter.inbound.entity.Reservation;
import com.ntconsult.adapter.inbound.entity.Review;

import java.util.List;
import java.util.Optional;

public interface HotelPort {

    List<Hotel> searchHotels(String destination, int rooms, int guests);
    List<Review> getReviewsForHotel(Long hotelId);
    Reservation createReservation(Reservation reservation);
    Optional<Hotel> findById(Long id);
}