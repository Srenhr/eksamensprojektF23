package com.miso.eksamensprojektf23.services.impl;


import com.miso.eksamensprojektf23.dtos.PatientDTO;
import com.miso.eksamensprojektf23.models.Patient;
import com.miso.eksamensprojektf23.repositories.PatientRepository;
import com.miso.eksamensprojektf23.services.PatientService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

  private final PatientRepository patientRepository;

  @Override
  public Patient getPatientById(Long id) {
    return patientRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + id));
  }

  @Override
  public List<Patient> getAllPatients() {
    return patientRepository.findAll();
  }

  @Override
  public void updatePatient(PatientDTO patientDTO) {
    Patient patient = patientRepository.findById(patientDTO.getPatientId())
        .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientDTO.getPatientId()));
    patient = fillPatientObject(patientDTO, patient);
    patientRepository.save(patient);
  }

  @Override
  public void savePatient(PatientDTO patientDTO) {
    Patient patient = new Patient();
    patient = fillPatientObject(patientDTO, patient);
    patientRepository.save(patient);
  }

  public Patient fillPatientObject(PatientDTO patientDTO, Patient patient) {
    patient.setFirstName(patientDTO.getFirstName());
    patient.setLastName(patientDTO.getLastName());
    patient.setEmail(patientDTO.getEmail());
    patient.setPhoneNumber(patientDTO.getPhoneNumber());
    patient.setBirthdate(patientDTO.getBirthdate());
    patient.setReasonForRefferal(patientDTO.getReasonForRefferal());
    /*patient.setEmployees(patientDTO.getEmployees())*/; /*TODO: skal ændres*/
    return patient;
  }


}
