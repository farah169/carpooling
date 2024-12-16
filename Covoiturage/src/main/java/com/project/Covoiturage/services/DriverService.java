package com.project.Covoiturage.services;

import com.project.Covoiturage.entity.Driver;
import com.project.Covoiturage.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {
    @Autowired
    private DriverRepository driverRepository;

    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public Driver getDriverById(Long id) {
        return driverRepository.findById(id).orElse(null);
    }

    public Driver saveDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }

    public Driver updateDriver(Driver driver) {
        if (!driverRepository.existsById(driver.getId())) {
            throw new RuntimeException("Driver not found with id: " + driver.getId());
        }

        return driverRepository.save(driver);
    }


}
