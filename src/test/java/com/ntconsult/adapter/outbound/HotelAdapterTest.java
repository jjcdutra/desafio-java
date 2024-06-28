package com.ntconsult.adapter.outbound;

import com.ntconsult.adapter.inbound.entity.Hotel;
import com.ntconsult.adapter.inbound.entity.Reservation;
import com.ntconsult.adapter.inbound.entity.Review;
import com.ntconsult.adapter.outbound.repository.HotelRepositoryJpa;
import com.ntconsult.adapter.outbound.repository.ReservationRepositoryJpa;
import com.ntconsult.adapter.outbound.repository.ReviewRepositoryJpa;
import com.ntconsult.application.ports.out.HotelPort;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class HotelAdapterTest {

    @Mock
    private HotelRepositoryJpa hotelRepository;

    @Mock
    private ReviewRepositoryJpa reviewRepository;

    @Mock
    private ReservationRepositoryJpa reservationRepository;

    @InjectMocks
    private HotelAdapter hotelPort;

    public HotelAdapterTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSearchHotels() {
        Hotel hotel1 = new Hotel(1L,
                "Hotel A",
                "Paris",
                10,
                2,
                100.0,
                "Central",
                "WiFi, Pool",
                null,
                null);
        Hotel hotel2 = new Hotel(2L,
                "Hotel B",
                "Paris",
                5,
                3,
                150.0,
                "Near Airport",
                "WiFi, Gym",
                null,
                null);
        when(hotelRepository.findByDestinationAndAvailableRoomsGreaterThanEqualAndMaxGuestsPerRoomGreaterThanEqual(
                "Paris", 1, 1)).thenReturn(Arrays.asList(hotel1, hotel2));
        List<Hotel> result = hotelPort.searchHotels("Paris", 1, 1);
        assertEquals(2, result.size());
        assertEquals("Hotel A", result.get(0).getName());
        assertEquals("Hotel B", result.get(1).getName());
    }

    @Test
    public void testGetReviewsForHotel() {
        Review review1 = new Review(1L, "User1", 5, "Great!", null);
        Review review2 = new Review(2L, "User2", 4, "Good", null);
        when(reviewRepository.findByHotelId(1L)).thenReturn(Arrays.asList(review1, review2));
        List<Review> result = hotelPort.getReviewsForHotel(1L);
        assertEquals(2, result.size());
        assertEquals("Great!", result.get(0).getComment());
        assertEquals("Good", result.get(1).getComment());
    }

    @Test
    public void testCreateReservation() {
        Hotel hotel = new Hotel(1L,
                "Hotel A",
                "Paris",
                10,
                2,
                100.0,
                "Central",
                "WiFi, Pool",
                null,
                null);
        Reservation reservation = new Reservation(null,
                "John Doe",
                "john@example.com",
                "1234-5678-9012-3456",
                "2023-10-01",
                "2023-10-05",
                2,
                hotel);
        when(hotelRepository.findById(1L)).thenReturn(java.util.Optional.of(hotel));
        when(reservationRepository.save(reservation)).thenReturn(reservation);
        Reservation result = hotelPort.createReservation(reservation);
        assertEquals("John Doe", result.getGuestName());
        assertEquals("john@example.com", result.getContact());
    }
}