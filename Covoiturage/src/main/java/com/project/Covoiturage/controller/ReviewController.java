package com.project.Covoiturage.controller;

import com.project.Covoiturage.entity.Driver;
import com.project.Covoiturage.entity.Passenger;
import com.project.Covoiturage.entity.Review;
import com.project.Covoiturage.services.DriverService;
import com.project.Covoiturage.services.PassengerService;
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
    @Autowired
    private PassengerService passengerService;
    @Autowired
    private DriverService driverService;

    // Display all reviews
    @GetMapping
    public String listReviews(Model model) {
        List<Review> reviews = reviewService.getAllReviews();
        model.addAttribute("reviews", reviews);
        return "reviews"; // Thymeleaf template: src/main/resources/templates/reviews/list.html
    }

    // Show form for creating a new review
    @GetMapping("/add")
    public String showCreateForm(Model model) {
        model.addAttribute("review", new Review());

        // Fetch lists of passengers and drivers to populate dropdowns
        List<Passenger> passengers = passengerService.getAllPassengers();
        List<Driver> drivers = driverService.getAllDrivers();

        model.addAttribute("passengers", passengers);
        model.addAttribute("drivers", drivers);

        return "add-review"; // Thymeleaf template
    }

    // Handle form submission for creating a new review
    @PostMapping("/add")
    public String createReview(@ModelAttribute Review review) {
        reviewService.saveReview(review);
        return "redirect:/reviews"; // Redirect to the reviews list page
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





    /* Display a single review by ID
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
    }*/

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