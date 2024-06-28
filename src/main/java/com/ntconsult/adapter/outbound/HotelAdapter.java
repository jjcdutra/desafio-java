package com.ntconsult.adapter.outbound;

import com.ntconsult.adapter.inbound.entity.Hotel;
import com.ntconsult.adapter.inbound.entity.Reservation;
import com.ntconsult.adapter.inbound.entity.Review;
import com.ntconsult.adapter.outbound.repository.HotelRepositoryJpa;
import com.ntconsult.adapter.outbound.repository.ReservationRepositoryJpa;
import com.ntconsult.adapter.outbound.repository.ReviewRepositoryJpa;
import com.ntconsult.application.ports.out.HotelPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class HotelAdapter implements HotelPort {

    private final HotelRepositoryJpa hotelRepository;
    private final ReviewRepositoryJpa reviewRepository;
    private final ReservationRepositoryJpa reservationRepository;

    public HotelAdapter(HotelRepositoryJpa hotelRepository,
                        ReviewRepositoryJpa reviewRepository,
                        ReservationRepositoryJpa reservationRepository) {
        this.hotelRepository = hotelRepository;
        this.reviewRepository = reviewRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<Hotel> searchHotels(String destination, int rooms, int guests) {
        return hotelRepository.findByDestinationAndAvailableRoomsGreaterThanEqualAndMaxGuestsPerRoomGreaterThanEqual(
                destination, rooms, guests);
    }

    public List<Review> getReviewsForHotel(Long hotelId) {
        return reviewRepository.findByHotelId(hotelId);
    }

    @Transactional
    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Optional<Hotel> findById(Long id) {
        return hotelRepository.findById(id);
    }
}