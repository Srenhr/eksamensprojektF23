package com.miso.eksamensprojektf23.services.impl;

import com.miso.eksamensprojektf23.models.Appointment;
import com.miso.eksamensprojektf23.models.Patient;
import com.miso.eksamensprojektf23.repositories.AppointmentRepository;
import com.miso.eksamensprojektf23.services.AppointmentService;
import com.miso.eksamensprojektf23.services.PatientService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

  private final AppointmentRepository appointmentRepository;
  private final PatientService patientService;

  @Override
  public Appointment getAppointmentById(Long id) {
    return appointmentRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Der blev ikke fundet nogen aftale, der matcher ID'et: " + id));
  }

  @Override
  public List<Appointment> getAppointmentsByUsername(String username) {
    return appointmentRepository.findAppointmentsByUserUsername(username);
  }

  @Override
  public void updateAppointment(Appointment requestBody) {
    Appointment appointment = appointmentRepository.findById(requestBody.getAppointmentId())
        .orElseThrow(() -> new EntityNotFoundException("Der blev ikke fundet nogen aftale, der matcher ID'et: " + requestBody.getAppointmentId()));
    appointment.setTitle(requestBody.getTitle());
    appointment.setUser(requestBody.getUser());
    appointment.setStartTime(requestBody.getStartTime());
    appointment.setEndTime(requestBody.getEndTime());
    appointment.setDescription(requestBody.getDescription());
    appointmentRepository.save(appointment);
  }

  @Override
  public void saveAppointment(Appointment requestBody) {
    appointmentRepository.save(requestBody);
  }

  @Override
  public void deleteAppointment(Appointment requestBody) {
    appointmentRepository.delete(requestBody);
  }


}
