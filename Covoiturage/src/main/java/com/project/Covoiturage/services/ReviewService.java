package com.project.Covoiturage.services;

import com.project.Covoiturage.entity.Review;
import com.project.Covoiturage.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review getReviewById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    public List<Review> getReviewsByDriverId(Long driverId) {
        return reviewRepository.findByReviewedId(driverId);
    }

    public List<Review> getReviewsByPassengerId(Long passengerId) {
        return reviewRepository.findByReviewerId(passengerId);
    }

    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    public boolean deleteReview(Long id) {
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
            return true; // Return true if deletion is successful
        } else {
            return false; // Return false if the review doesn't exist
        }
    }

}
