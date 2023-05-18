package com.miso.eksamensprojektf23.controllers;


import com.miso.eksamensprojektf23.models.Appointment;
import com.miso.eksamensprojektf23.models.User;
import com.miso.eksamensprojektf23.repositories.AppointmentRepository;
import com.miso.eksamensprojektf23.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AppointmentRepository appointmentRepository;


    // Used for testing purposes
    @GetMapping("/users/1/appointments")
    public List<Appointment> employeeAppointments(@PathVariable long employeeId) {
        User employee = userRepository.findByUserId(employeeId);
        return appointmentRepository.findAppointmentsByUser(employee);
    }

    // Implement to fetch in calendar when using springboot login
    @GetMapping("/user/userappointments")
    public List<Appointment> getSomeData(Authentication authentication) {
        String loggedInUsername = ((UserDetails) authentication.getPrincipal()).getUsername();
        // Use the logged-in user's ID to fetch data specific to that user

        Optional<User> loggedInUser = userRepository.findUserByUsername(loggedInUsername);

        return appointmentRepository.findAppointmentsByUser(loggedInUser.get());
    }



}
