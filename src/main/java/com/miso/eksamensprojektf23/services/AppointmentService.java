package com.miso.eksamensprojektf23.services;

import com.miso.eksamensprojektf23.models.Appointment;
import com.miso.eksamensprojektf23.models.Patient;
import com.miso.eksamensprojektf23.models.Role;

import java.util.List;

public interface AppointmentService {

  Appointment getAppointmentById(Long id);

  List<Appointment> getAppointmentsByUsername(String username);

  void updateAppointment(Appointment appointment);

  void saveAppointment(Appointment appointment);

  void deleteAppointment(Appointment appointment);
}
