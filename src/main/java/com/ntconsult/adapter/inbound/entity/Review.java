package com.ntconsult.adapter.inbound.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private int rating;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}
