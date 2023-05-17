package com.miso.eksamensprojektf23.controllers;


import com.miso.eksamensprojektf23.models.Appointment;
import com.miso.eksamensprojektf23.models.User;
import com.miso.eksamensprojektf23.repositories.AppointmentRepository;
import com.miso.eksamensprojektf23.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @GetMapping("/employees/{employeeId}/appointments")
    public List<Appointment> employeeAppointments(@PathVariable long employeeId) {
        User employee = userRepository.findByUserId(employeeId);
        return appointmentRepository.findAppointmentsByUser(employee);
    }



}
