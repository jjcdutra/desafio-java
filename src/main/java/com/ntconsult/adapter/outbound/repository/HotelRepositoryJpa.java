package com.ntconsult.adapter.outbound.repository;

import com.ntconsult.adapter.inbound.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepositoryJpa extends JpaRepository<Hotel, Long> {
    List<Hotel> findByDestinationAndAvailableRoomsGreaterThanEqualAndMaxGuestsPerRoomGreaterThanEqual(
            String destination, int availableRooms, int maxGuestsPerRoom);
}