package com.project.Covoiturage.controller;

import com.project.Covoiturage.entity.Ride;
import com.project.Covoiturage.services.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rides")
public class RideController {

    @Autowired
    private RideService rideService;

    // Display all rides
    @GetMapping
    public String listRides(Model model) {
        List<Ride> rides = rideService.getAllRides();
        model.addAttribute("rides", rides);
        return "rides/list"; // Thymeleaf template: src/main/resources/templates/rides/list.html
    }

    // Display a single ride by ID
    @GetMapping("/{id}")
    public String getRideById(@PathVariable Long id, Model model) {
        Ride ride = rideService.getRideById(id);
        if (ride != null) {
            model.addAttribute("ride", ride);
            return "rides/detail"; // Thymeleaf template: src/main/resources/templates/rides/detail.html
        } else {
            model.addAttribute("errorMessage", "Ride not found.");
            return "error/404"; // Error page template
        }
    }

    // Search rides by departure and destination
    @GetMapping("/search")
    public String searchRide(@RequestParam String departure, @RequestParam String destination, Model model) {
        List<Ride> rides = rideService.searchRide(departure, destination);
        model.addAttribute("rides", rides);
        model.addAttribute("departure", departure);
        model.addAttribute("destination", destination);
        return "rides/search"; // Thymeleaf template: src/main/resources/templates/rides/search.html
    }

    // Show form for creating a new ride
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("ride", new Ride());
        return "rides/create"; // Thymeleaf template: src/main/resources/templates/rides/create.html
    }

    // Handle form submission for creating a new ride
    @PostMapping
    public String createRide(@ModelAttribute Ride ride, Model model) {
        rideService.saveRide(ride);
        return "redirect:/rides"; // Redirect to the list page
    }

    // Handle ride deletion
    @GetMapping("/delete/{id}")
    public String deleteRide(@PathVariable Long id, Model model) {
        boolean deleted = rideService.deleteRide(id);
        if (!deleted) {
            model.addAttribute("errorMessage", "Unable to delete ride. Ride not found.");
            return "error/404"; // Error page template
        }
        return "redirect:/rides"; // Redirect to the list page
    }
}