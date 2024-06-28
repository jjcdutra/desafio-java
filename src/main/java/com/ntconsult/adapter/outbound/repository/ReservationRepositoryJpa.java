package com.ntconsult.adapter.outbound.repository;

import com.ntconsult.adapter.inbound.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepositoryJpa extends JpaRepository<Reservation, Long> {
}
