package com.project.Covoiturage.controller;

import com.project.Covoiturage.entity.Driver;
import com.project.Covoiturage.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;

    // Afficher la liste des conducteurs
    @GetMapping
    public String getAllDrivers(Model model) {
        model.addAttribute("drivers", driverService.getAllDrivers());
        return "drivers"; // Correspond à drivers.html
    }

    // Afficher le formulaire pour ajouter un conducteur
    @GetMapping("/add")
    public String addDriverForm(Model model) {
        model.addAttribute("driver", new Driver());
        return "add-driver"; // Correspond à add-driver.html
    }

    // Enregistrer un nouveau conducteur
    @PostMapping("/add")
    public String createDriver(@ModelAttribute Driver driver) {
        driverService.saveDriver(driver);
        return "redirect:/drivers";
    }

    // Supprimer un conducteur
    @GetMapping("/delete/{id}")
    public String deleteDriver(@PathVariable Long id) {
        driverService.deleteDriver(id);
        return "redirect:/drivers";
    }

    @GetMapping("/{id}")
    public String getDriver(@PathVariable Long id, Model model) {
        Driver driver = driverService.getDriverById(id);
        model.addAttribute("driver", driver);
        return "driver-details";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Driver driver = driverService.getDriverById(id); // Fetch the driver by ID
        model.addAttribute("driver", driver);           // Pass the driver to the model
        return "edit-driver";                           // Render the edit-driver.html template
    }

    // Handle the update
    @PostMapping("/edit")
    public String updateDriver(@ModelAttribute Driver driver) {
        driverService.updateDriver(driver);             // Save the updated driver
        return "redirect:/drivers";                    // Redirect to the list of drivers
    }
}
