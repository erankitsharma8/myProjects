package com.ankit.student.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ankit.student.flightreservation.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

}
