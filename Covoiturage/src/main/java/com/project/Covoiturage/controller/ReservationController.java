package com.project.Covoiturage.controller;

import com.project.Covoiturage.entity.Reservation;
import com.project.Covoiturage.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reservations")
@Validated
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    // Display all reservations
    @GetMapping
    public String getAllReservations(Model model) {
        List<Reservation> reservations = reservationService.getAllReservations();
        model.addAttribute("reservations", reservations);
        return "reservation/list"; // Refers to the Thymeleaf template: src/main/resources/templates/reservation/list.html
    }

    // Display a single reservation by ID
    @GetMapping("/{id}")
    public String getReservationById(@PathVariable Long id, Model model) {
        Reservation reservation = reservationService.getReservationById(id);
        if (reservation != null) {
            model.addAttribute("reservation", reservation);
            return "reservation/detail"; // Refers to: src/main/resources/templates/reservation/detail.html
        } else {
            return "error/404"; // Refers to an error page
        }
    }

    // Display reservations by passenger
    @GetMapping("/passenger/{passengerId}")
    public String getReservationsByPassenger(@PathVariable Long passengerId, Model model) {
        List<Reservation> reservations = reservationService.getReservationsByPassengerId(passengerId);
        model.addAttribute("reservations", reservations);
        return "reservation/passenger-list";
    }

    // Display reservations by ride
    @GetMapping("/ride/{rideId}")
    public String getReservationsByRide(@PathVariable Long rideId, Model model) {
        List<Reservation> reservations = reservationService.getReservationsByRideId(rideId);
        model.addAttribute("reservations", reservations);
        return "reservation/ride-list";
    }

    // Show form for creating a new reservation
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        return "reservation/create"; // Refers to: src/main/resources/templates/reservation/create.html
    }

    // Handle form submission for creating a new reservation
    @PostMapping
    public String createReservation(@ModelAttribute Reservation reservation, Model model) {
        // Manual validation
        String errorMessage = null;
        if (reservation.getPassenger() == null) {
            errorMessage = "Passenger is required.";
        } else if (reservation.getRide() == null) {
            errorMessage = "Ride is required.";
        }

        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
            return "reservation/create"; // Return to the form with error
        }

        // Save the reservation
        reservationService.saveReservation(reservation);
        return "redirect:/reservations"; // Redirect to the list page
    }
}
