package com.project.Covoiturage.controller;

import com.project.Covoiturage.entity.Driver;
import com.project.Covoiturage.entity.Passenger;
import com.project.Covoiturage.repository.DriverRepository;
import com.project.Covoiturage.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthenticationController {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    // Driver Login Page
    @GetMapping("/auth/driver")
    public String showDriverLoginPage() {
        return "driver-login"; // Thymeleaf template for driver login
    }

    // Passenger Login Page
    @GetMapping("/auth/passenger")
    public String showPassengerLoginPage() {
        return "passenger-login"; // Thymeleaf template for passenger login
    }

    // Driver Authentication Logic
    @PostMapping("/auth/driver")
    public String authenticateDriver(@RequestParam("email") String email,
                                     @RequestParam("name") String name,
                                     Model model) {
        Driver driver = driverRepository.findByEmailAndName(email, name);
        if (driver != null && driver.getName().equals(name) ) {
            return "rides"; // Redirect to driver's dashboard
        }
        model.addAttribute("error", "Invalid credentials for Driver");
        return "driver-login";
    }

    // Passenger Authentication Logic
    @PostMapping("/auth/passenger")
    public String authenticatePassenger(@RequestParam("email") String email,
                                        @RequestParam("name") String name,
                                        Model model) {
        Passenger passenger = passengerRepository.findByEmailAndName(email, name);
        if (passenger != null) {
            return "reservations"; // Redirect to passenger's dashboard
        }
        model.addAttribute("error", "Invalid credentials for Passenger");
        return "passenger-login";
    }
}
