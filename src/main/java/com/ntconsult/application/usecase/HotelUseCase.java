package com.ntconsult.application.usecase;

import com.ntconsult.adapter.inbound.entity.Hotel;
import com.ntconsult.adapter.inbound.entity.Reservation;
import com.ntconsult.adapter.inbound.entity.Review;
import com.ntconsult.application.domain.Notification;
import com.ntconsult.application.ports.out.HotelPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HotelUseCase {

    private final HotelPort hotelPort;
    private final NotificationUseCase notificationUseCase;

    public HotelUseCase(HotelPort hotelPort, NotificationUseCase notificationUseCase) {
        this.hotelPort = hotelPort;
        this.notificationUseCase = notificationUseCase;
    }

    public List<Hotel> searchHotels(String destination, int rooms, int guests) {
        return hotelPort.searchHotels(destination, rooms, guests);
    }

    public List<Review> getReviewsForHotel(Long hotelId) {
        return hotelPort.getReviewsForHotel(hotelId);
    }

    @Transactional
    public Reservation createReservation(Long hotelId, Reservation reservation) {
        Hotel hotel = hotelPort.findById(hotelId).orElseThrow(() -> new RuntimeException("Hotel not found"));
        if (hotel.getAvailableRooms() < reservation.getNumberOfRooms()) {
            throw new RuntimeException("Not enough available rooms");
        }
        hotel.setAvailableRooms(hotel.getAvailableRooms() - reservation.getNumberOfRooms());
        reservation.setHotel(hotel);
        Reservation savedReservation = hotelPort.createReservation(reservation);

        processCheckin(savedReservation.getId());
        processCheckout(savedReservation.getId());

        return savedReservation;
    }

    private void processCheckin(Long reservationId) {
        Notification notification = new Notification();
        notification.setReservationId(reservationId);
        notification.setStatus("CHECKIN_CONFIRMED");
        notification.setMessage("Check-in confirmed for booking ID: " + reservationId);
        notificationUseCase.sendNotification(notification);
    }

    private void processCheckout(Long reservationId) {
        Notification notification = new Notification();
        notification.setReservationId(reservationId);
        notification.setStatus("CHECKOUT_CONFIRMED");
        notification.setMessage("Check-out confirmed for booking ID: " + reservationId);
        notificationUseCase.sendNotification(notification);
    }
}