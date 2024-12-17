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

    // Display the list of rides
    @GetMapping
    public String getAllRides(Model model) {
        model.addAttribute("rides", rideService.getAllRides());
        return "rides"; // Corresponds to rides.html
    }



    // Display the form to add a ride
    @GetMapping("/add")
    public String addRideForm(Model model) {
        model.addAttribute("ride", new Ride());
        return "add-ride"; // Corresponds to add-ride.html
    }

    // Save a new ride
    @PostMapping("/add")
    public String createRide(@ModelAttribute Ride ride) {
        rideService.saveRide(ride);
        return "redirect:/rides";
    }

    // Delete a ride
    @GetMapping("/delete/{id}")
    public String deleteRide(@PathVariable Long id) {
        rideService.deleteRide(id);
        return "redirect:/rides";
    }

    // Display ride details
    @GetMapping("/{id}")
    public String getRide(@PathVariable Long id, Model model) {
        Ride ride = rideService.getRideById(id);
        model.addAttribute("ride", ride);
        return "ride-details"; // Corresponds to ride-details.html
    }

    // Display the form to edit a ride
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Ride ride = rideService.getRideById(id); // Fetch the ride by ID
        model.addAttribute("ride", ride);      // Pass the ride to the model
        return "edit-ride";                   // Render the edit-ride.html template
    }

    // Handle the update of a ride
    @PostMapping("/edit")
    public String updateRide(@ModelAttribute Ride ride) {
        rideService.updateRide(ride);          // Save the updated ride
        return "redirect:/rides";            // Redirect to the list of rides
    }
}