package com.ankit.student.flightreservation.services;

import com.ankit.student.flightreservation.dto.ReservationRequest;
import com.ankit.student.flightreservation.entities.Reservation;

public interface ReservationService {

	public Reservation bookFlight(ReservationRequest request);
}
