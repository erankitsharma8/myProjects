package com.ankit.student.flightreservation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ankit.student.flightreservation.dto.ReservationRequest;
import com.ankit.student.flightreservation.entities.Flight;
import com.ankit.student.flightreservation.entities.Passenger;
import com.ankit.student.flightreservation.entities.Reservation;
import com.ankit.student.flightreservation.repos.FlightRepository;
import com.ankit.student.flightreservation.repos.PassengerRepository;
import com.ankit.student.flightreservation.repos.ReservationRepository;
import com.ankit.student.flightreservation.util.EmailUtil;
import com.ankit.student.flightreservation.util.PDFGenerator;


@Service
public class ReservationServiceImpl implements ReservationService {

	@Value("${com.ankit.flightreservation.itinerary.dirpath}")
	private String Itiniary_DIR ;

	@Autowired
	FlightRepository flightrepo;
	
	@Autowired
	PassengerRepository passRepo;
	
	@Autowired
	ReservationRepository reservRepo;
	
	@Autowired
	EmailUtil emailUtil;
	
	@Autowired
	PDFGenerator pdfGenerator;
	
	@Override
	public Reservation bookFlight(ReservationRequest request) {
		
		//Make payment
		
		Long flightId = request.getFlightId();
		Flight flight = flightrepo.findById(flightId).orElse(null);
		
		Passenger pass=new Passenger();
		pass.setFirstName(request.getPassengerFirstName());
		pass.setLastName(request.getPassengerLastName());
		pass.setPhone(request.getPassengerPhone());
		pass.setEmail(request.getPassengerEmail());
		
		Passenger savedPassenger = passRepo.save(pass);
		
		Reservation reservation=new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);
		
		Reservation saveReservation = reservRepo.save(reservation);
		
		String filePath = Itiniary_DIR+savedPassenger.getId()+".pdf";
		pdfGenerator.generateItineary(saveReservation, filePath);
		emailUtil.sendItinerary(pass.getEmail(), filePath);
		return saveReservation;
	}

}
