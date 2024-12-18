package com.project.Covoiturage.services;

import com.project.Covoiturage.entity.Driver;
import com.project.Covoiturage.entity.Ride;
import com.project.Covoiturage.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class RideService {
    @Autowired
    private RideRepository rideRepository;

    public List<Ride> getAllRides() {
        return rideRepository.findAll();
    }

    public Ride getRideById(Long id) {
        return rideRepository.findById(id).orElse(null);
    }


    public List<Ride> searchRide(String departure, String destination) {
        return rideRepository.findByDepartureAndDestination(departure, destination);
    }

    public Ride saveRide(Ride ride) {
        return rideRepository.save(ride);
    }

    public boolean deleteRide(Long id) {
        if (rideRepository.existsById(id)) {
            rideRepository.deleteById(id);
            return true; // Deletion successful
        } else {
            return false; // Ride not found
        }
    }
    public Ride updateRide(Ride ride) {
        if (!rideRepository.existsById(ride.getId())) {
            throw new RuntimeException("Ride not found with id: " + ride.getId());
        }

        return rideRepository.save(ride);
    }
}
