package com.ntconsult.adapter.inbound.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String guestName;
    private String contact;
    private String paymentDetails;
    private String checkInDate;
    private String checkOutDate;
    private int numberOfRooms;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}