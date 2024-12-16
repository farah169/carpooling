package com.project.Covoiturage.services;

import com.project.Covoiturage.entity.Reservation;
import com.project.Covoiturage.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public List<Reservation> getReservationsByPassengerId(Long passengerId) {
        return reservationRepository.findByPassengerId(passengerId);
    }

    public List<Reservation> getReservationsByRideId(Long rideId) {
        return reservationRepository.findByRideId(rideId);
    }

    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

}

