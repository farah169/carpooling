package com.project.Covoiturage.repository;

import com.project.Covoiturage.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByReviewedId(Long driverId);

    List<Review> findByReviewerId(Long passengerId);
}
