package com.project.Covoiturage.repository;

import com.project.Covoiturage.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
