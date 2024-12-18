package com.project.Covoiturage.controller;

import com.project.Covoiturage.entity.Ride;
import com.project.Covoiturage.services.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rides")
public class RideController {

    @Autowired
    private RideService rideService;

    // Display the list of rides
    @GetMapping
    public String getAllRides(Model model) {
        model.addAttribute("rides", rideService.getAllRides());
        return "rides"; // Corresponds to rides.html
    }

    // Display ride details by ID
    @GetMapping("/{id}")
    public String getRide(@PathVariable Long id, Model model) {
        Ride ride = rideService.getRideById(id);

        // If the ride is not found, display an error message
        if (ride == null) {
            model.addAttribute("error", "Ride not found.");
            return "error"; // Corresponds to error.html
        }

        model.addAttribute("ride", ride);
        return "ride-details"; // Corresponds to ride-details.html
    }

    // Display the form to add a new ride
    @GetMapping("/add")
    public String addRideForm(Model model) {
        model.addAttribute("ride", new Ride());
        return "add-ride"; // Corresponds to add-ride.html
    }

    // Save a new ride
    @PostMapping("/add")
    public String createRide(@ModelAttribute Ride ride) {
        rideService.saveRide(ride);
        return "redirect:/rides"; // Redirect to the rides list
    }

    // Delete a ride by ID
    @GetMapping("/delete/{id}")
    public String deleteRide(@PathVariable Long id) {
        rideService.deleteRide(id);
        return "redirect:/rides"; // Redirect to the rides list
    }

    // Display the form to edit an existing ride
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Ride ride = rideService.getRideById(id);

        // If the ride is not found, return an error
        if (ride == null) {
            model.addAttribute("error", "Ride not found.");
            return "error"; // Corresponds to error.html
        }

        model.addAttribute("ride", ride);
        return "edit-ride"; // Corresponds to edit-ride.html
    }

    // Handle the update of a ride
    @PostMapping("/edit")
    public String updateRide(@ModelAttribute Ride ride) {
        rideService.updateRide(ride);
        return "redirect:/rides"; // Redirect to the rides list after updating
    }
}