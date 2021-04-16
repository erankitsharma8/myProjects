package com.ankit.student.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ankit.student.flightreservation.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
