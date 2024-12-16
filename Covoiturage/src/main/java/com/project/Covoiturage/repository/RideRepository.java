package com.project.Covoiturage.repository;

import com.project.Covoiturage.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RideRepository extends JpaRepository<Ride, Long> {

    List<Ride> findByDepartureAndDestination(String departure, String destination);

    List<Ride> findByDriverId(Long driverId);

}
