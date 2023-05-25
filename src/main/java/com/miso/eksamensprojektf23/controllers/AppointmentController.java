package com.miso.eksamensprojektf23.controllers;


import com.miso.eksamensprojektf23.models.Appointment;
import com.miso.eksamensprojektf23.models.User;
import com.miso.eksamensprojektf23.services.AppointmentService;
import com.miso.eksamensprojektf23.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api")
public class AppointmentController {

  private final AppointmentService appointmentService;
  private final UserService userService;

  @GetMapping("/appointments")
  public List<Appointment> returnAppointments(Principal principal) {
    return appointmentService.getAppointmentsByUsername(principal.getName());
  }

  @PostMapping("/appointment/create")
  public ResponseEntity<String> createAppointment(@RequestBody Appointment requestBody, Principal principal) {
    // Use the logged-in user's ID to fetch data specific to that user
    User loggedInUser = userService.getUserByUsername(principal.getName());
    requestBody.setUser(loggedInUser);
    appointmentService.saveAppointment(requestBody);
    return new ResponseEntity<>("Appointment Created", HttpStatus.OK);
  }

  @PostMapping("/appointment/edit")
  public ResponseEntity<String> editAppointment(@RequestBody Appointment requestBody, Principal principal) {
    User loggedInUser = userService.getUserByUsername(principal.getName());
    requestBody.setUser(loggedInUser);
    appointmentService.updateAppointment(requestBody);
    return new ResponseEntity<>("Appointment Updated", HttpStatus.OK);
  }

  @PostMapping("/appointment/delete")
  public ResponseEntity<String> deleteAppointment(@RequestBody Appointment requestBody) {
    appointmentService.deleteAppointment(requestBody);
    return new ResponseEntity<>("Appointment Deleted", HttpStatus.OK);
  }

}
