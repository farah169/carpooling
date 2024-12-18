package com.project.Covoiturage.controller;

import com.project.Covoiturage.entity.Passenger;
import com.project.Covoiturage.entity.Reservation;
import com.project.Covoiturage.entity.Ride;
import com.project.Covoiturage.services.PassengerService;
import com.project.Covoiturage.services.ReservationService;
import com.project.Covoiturage.services.RideService;
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
    @Autowired
    private PassengerService passengerService;

    @Autowired
    private RideService rideService;


    // Display all reservations
    @GetMapping
    public String getAllReservations(Model model) {
        List<Reservation> reservations = reservationService.getAllReservations();
        model.addAttribute("reservations", reservations);
        return "reservations"; // Refers to the Thymeleaf template: src/main/resources/templates/reservation/list.html
    }

    // Display a single reservation by ID
    @GetMapping("/{id}")
    public String getReservationById(@PathVariable Long id, Model model) {
        Reservation reservation = reservationService.getReservationById(id);
        if (reservation != null) {
            model.addAttribute("reservation", reservation);
            return "reservation-details"; // Refers to: src/main/resources/templates/reservation/detail.html
        } else {
            return "error/404"; // Refers to an error page
        }
    }
    @GetMapping("/add_Reservation")
    public String addReservation(Model model) {
        List<Passenger> passengers = passengerService.getAllPassengers();
        model.addAttribute("passengers", passengers);
        List<Ride> rides = rideService.getAllRides();
        model.addAttribute("rides", rides);
        model.addAttribute("reservation", new Reservation());
        return "add_Reservation";
    }

    // Display reservations by passenger
    @GetMapping("/passenger/{passengerId}")
    public String getReservationsByPassenger(@PathVariable Long passengerId, Model model) {
        List<Reservation> reservations = reservationService.getReservationsByPassengerId(passengerId);
        model.addAttribute("reservations", reservations);
        return "reservation/passengers";
    }

    // Display reservations by ride
    @GetMapping("/ride/{rideId}")
    public String getReservationsByRide(@PathVariable Long rideId, Model model) {
        List<Reservation> reservations = reservationService.getReservationsByRideId(rideId);
        model.addAttribute("reservations", reservations);
        return "reservation/rides";
    }

    // Show form for creating a new reservation
    @GetMapping("/add")
    public String showCreateForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("passengers", passengerService.getAllPassengers());  // Pass passengers to the model
        model.addAttribute("rides", rideService.getAllRides());  // Pass rides to the model
        return "add_Reservation";
    }

    // Handle form submission for creating a new reservation
    @PostMapping("/add")
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
            return "add_Reservation"; // Return to the form with error
        }

        // Save the reservation
        reservationService.saveReservation(reservation);
        return "redirect:/reservations"; // Redirect to the list page
    }
}
