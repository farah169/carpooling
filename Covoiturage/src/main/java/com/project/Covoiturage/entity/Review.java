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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Passenger getReviewer() {
        return reviewer;
    }

    public void setReviewer(Passenger reviewer) {
        this.reviewer = reviewer;
    }

    public Driver getReviewed() {
        return reviewed;
    }

    public void setReviewed(Driver reviewed) {
        this.reviewed = reviewed;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDateTime reviewDate) {
        this.reviewDate = reviewDate;
    }
}
