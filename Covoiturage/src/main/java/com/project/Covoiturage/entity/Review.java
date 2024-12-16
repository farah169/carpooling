package com.project.Covoiturage.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Passenger reviewer;

    @ManyToOne
    private Driver reviewed;

    private int rating;
    private String comment;
    private LocalDateTime reviewDate;

}
