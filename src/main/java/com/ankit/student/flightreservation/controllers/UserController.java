package com.ankit.student.flightreservation.controllers;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ankit.student.flightreservation.entities.User;
import com.ankit.student.flightreservation.repos.UserRepository;
import com.ankit.student.flightreservation.services.SecurityService;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private SecurityService securityService;
	
	private static final Logger LOGGER=org.slf4j.LoggerFactory.getLogger(UserController.class); 
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@RequestMapping("/showReg")
	public String showRegistrationPage() {
		return "login/registerUser";
	}

	@RequestMapping(value="/registerUser", method = RequestMethod.POST)
	public String register(@ModelAttribute("user") User user){
		user.setPassword(encoder.encode(user.getPassword()));
		userRepo.save(user);
		return "login/login";
	}
	
	@RequestMapping("/showLogin")
	public String showLoginPage() {
		return "login/login";
	}
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public String login(@RequestParam("email") String email,@RequestParam("password")  String password, ModelMap mp) {
		LOGGER.error("error");
		LOGGER.warn("warn");
		LOGGER.info("info");
		LOGGER.debug("debug");
		LOGGER.trace("trace");
		boolean loginResponse = securityService.login(email, password);
		if(loginResponse){
			return "findFlights";
		}else {
			mp.addAttribute("msg","Invalid userName or Password. Please try again!");
		}
		return "login/login";
	}
}
