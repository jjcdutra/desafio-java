package com.ntconsult.adapter.inbound.web;

import com.ntconsult.adapter.inbound.entity.Hotel;
import com.ntconsult.adapter.inbound.entity.Reservation;
import com.ntconsult.adapter.inbound.entity.Review;
import com.ntconsult.application.usecase.HotelUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HotelController {
    private final HotelUseCase hotelUseCase;

    public HotelController(HotelUseCase hotelUseCase) {
        this.hotelUseCase = hotelUseCase;
    }

    @GetMapping("/search")
    public List<Hotel> searchHotels(
            @RequestParam String destination,
            @RequestParam int rooms,
            @RequestParam int guests) {
        return hotelUseCase.searchHotels(destination, rooms, guests);
    }

    @GetMapping("/reviews")
    public List<Review> getReviewsForHotel(@RequestParam Long hotelId) {
        return hotelUseCase.getReviewsForHotel(hotelId);
    }

    @PostMapping("/reserve")
    public Reservation createReservation(
            @RequestParam Long hotelId,
            @RequestBody Reservation reservation) {
        return hotelUseCase.createReservation(hotelId, reservation);
    }
}
