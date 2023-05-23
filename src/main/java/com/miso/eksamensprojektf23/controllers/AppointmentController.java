package com.miso.eksamensprojektf23.controllers;


import com.miso.eksamensprojektf23.models.Appointment;
import com.miso.eksamensprojektf23.models.User;
import com.miso.eksamensprojektf23.repositories.AppointmentRepository;
import com.miso.eksamensprojektf23.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class AppointmentController {


    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/appointments")
    public List<Appointment> returnAppointments() {
        return appointmentRepository.findAll();
    }

    @GetMapping("/user/appointments")
    public List<Appointment> employeeAppointments(Model model, Principal principal) {
        String loggedInUsername = principal.getName();
        // Use the logged-in user's ID to fetch data specific to that user

        Optional<User> loggedInUser = userRepository.findUserByUsername(loggedInUsername);
        // Currently throws on error for user if user doesn't have appointments
        return appointmentRepository.findAppointmentsByUser(loggedInUser.get());
    }

    @PostMapping("/createAppointment")
    public ResponseEntity<String> createAppointment(@RequestBody Appointment appointment, Principal principal) {
        String loggedInUsername = principal.getName();
        // Use the logged-in user's ID to fetch data specific to that user

        Optional<User> loggedInUser = userRepository.findUserByUsername(loggedInUsername);

        appointment.setUser(loggedInUser.get());

        appointmentRepository.save(appointment);

        return new ResponseEntity<>("Appointment Created", HttpStatus.OK);
    }


}
