package com.ankit.student.flightreservation.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ankit.student.flightreservation.dto.ReservationRequest;
import com.ankit.student.flightreservation.entities.Flight;
import com.ankit.student.flightreservation.entities.Reservation;
import com.ankit.student.flightreservation.repos.FlightRepository;
import com.ankit.student.flightreservation.services.ReservationService;

@Controller
public class ReservationController {

	@Autowired
	FlightRepository fileRepos;
	
	@Autowired
	ReservationService resvRepo;
	
	@RequestMapping("/showCompleteReservation")
	public String showCompleteReservation(@RequestParam("flightId") Long flighId,ModelMap mp){
		Flight fl= fileRepos.findById(flighId).orElse(null);
		mp.addAttribute("flight", fl);
		return "completeReservation";
		
	}
	
	@RequestMapping(value = "/completeReservation", method = RequestMethod.POST)
	public String completeReservation(ReservationRequest request,ModelMap mp){
		Reservation reservation = resvRepo.bookFlight(request);
		mp.addAttribute("msg","Reservation created successfully and id is: " +reservation.getId());
		return "reservationConfirmation";
		
	}
}
