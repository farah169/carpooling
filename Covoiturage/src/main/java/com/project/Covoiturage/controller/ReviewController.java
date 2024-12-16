package com.project.Covoiturage.controller;

import com.project.Covoiturage.entity.Review;
import com.project.Covoiturage.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // Display all reviews
    @GetMapping
    public String listReviews(Model model) {
        List<Review> reviews = reviewService.getAllReviews();
        model.addAttribute("reviews", reviews);
        return "reviews/list"; // Thymeleaf template: src/main/resources/templates/reviews/list.html
    }

    // Display a single review by ID
    @GetMapping("/{id}")
    public String getReviewById(@PathVariable Long id, Model model) {
        Review review = reviewService.getReviewById(id);
        if (review != null) {
            model.addAttribute("review", review);
            return "reviews/detail"; // Thymeleaf template: src/main/resources/templates/reviews/detail.html
        } else {
            model.addAttribute("errorMessage", "Review not found.");
            return "error/404"; // Error page template
        }
    }

    // Display reviews by driver
    @GetMapping("/driver/{driverId}")
    public String getReviewsByDriver(@PathVariable Long driverId, Model model) {
        List<Review> reviews = reviewService.getReviewsByDriverId(driverId);
        model.addAttribute("reviews", reviews);
        model.addAttribute("driverId", driverId);
        return "reviews/driver"; // Thymeleaf template: src/main/resources/templates/reviews/driver.html
    }

    // Display reviews by passenger
    @GetMapping("/passenger/{passengerId}")
    public String getReviewsByPassenger(@PathVariable Long passengerId, Model model) {
        List<Review> reviews = reviewService.getReviewsByPassengerId(passengerId);
        model.addAttribute("reviews", reviews);
        model.addAttribute("passengerId", passengerId);
        return "reviews/passenger"; // Thymeleaf template: src/main/resources/templates/reviews/passenger.html
    }

    // Show form for creating a new review
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("review", new Review());
        return "reviews/create"; // Thymeleaf template: src/main/resources/templates/reviews/create.html
    }

    // Handle form submission for creating a new review
    @PostMapping
    public String createReview(@ModelAttribute Review review, Model model) {
        reviewService.saveReview(review);
        return "redirect:/reviews"; // Redirect to the reviews list page
    }

    // Handle review deletion
    @GetMapping("/delete/{id}")
    public String deleteReview(@PathVariable Long id, Model model) {
        boolean deleted = reviewService.deleteReview(id);
        if (!deleted) {
            model.addAttribute("errorMessage", "Unable to delete review.");
            return "error/404"; // Error page template
        }
        return "redirect:/reviews"; // Redirect to the reviews list page
    }
}