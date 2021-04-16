package com.ankit.student.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ankit.student.flightreservation.entities.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger,Long> {

}
