package com.miso.eksamensprojektf23.controllers;


import com.miso.eksamensprojektf23.models.Appointment;
import com.miso.eksamensprojektf23.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class AppointmentController {


    @Autowired
    AppointmentRepository appointmentRepository;


    @GetMapping("/appointments")
    public List<Appointment> returnAppointments() {
        return appointmentRepository.findAll();
    }

}
