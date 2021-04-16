package com.ankit.student.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ankit.student.flightreservation.entities.User;

public interface UserRepository extends JpaRepository<User,Long> {

	User findByEmail(String email);

}
