package com.project.Covoiturage.controller;


import com.project.Covoiturage.entity.Passenger;
import com.project.Covoiturage.repository.PassengerRepository;
import com.project.Covoiturage.services.PassengerService;
import com.project.Covoiturage.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/passengers")
public class PassengerController {
    @Autowired
    private PassengerService passengerService;

    @GetMapping
    public String getAllPassengers(Model model) {
        List<Passenger> passengers = passengerService.getAllPassengers();
        if (passengers == null) {
            passengers = new ArrayList<>(); // Avoid passing null
        }
        model.addAttribute("passengers", passengers);
        return "passengers";
    }



    // Afficher le formulaire pour ajouter un passager
    @GetMapping("/add")
    public String addPassengerForm(Model model) {
        model.addAttribute("passenger", new Passenger());
        return "add-passenger"; // Correspond à add-passenger.html
    }

    // Enregistrer un nouveau passager
    @PostMapping("/add")
    public String createPassenger(@ModelAttribute Passenger passenger) {
        passengerService.savePassenger(passenger);
        return "redirect:/passengers";
    }

    // Supprimer un passager
    @GetMapping("/delete/{id}")
    public String deletePassenger(@PathVariable Long id) {
        passengerService.deletePassenger(id);
        return "redirect:/passengers";
    }

    @GetMapping("/{id}")
    public String getPassenger(@PathVariable Long id, Model model) {
        Passenger passenger = passengerService.getPassengerById(id);
        model.addAttribute("passenger", passenger);
        return "passenger-details";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Passenger passenger = passengerService.getPassengerById(id); // Fetch the passenger by ID
        model.addAttribute("passenger", passenger);           // Pass the passenger to the model
        return "edit-passenger";                           // Render the edit-passenger.html template
    }

    // Handle the update
    @PostMapping("/edit")
    public String updatePassenger(@ModelAttribute Passenger passenger) {
        passengerService.updatePassenger(passenger);             // Save the updated driver
        return "redirect:/passengers";                    // Redirect to the list of drivers
    }
}
