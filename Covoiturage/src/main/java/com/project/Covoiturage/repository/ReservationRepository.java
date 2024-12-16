package com.project.Covoiturage.repository;

import com.project.Covoiturage.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByPassengerId(Long id);
    List<Reservation> findByRideId(Long id);
}

