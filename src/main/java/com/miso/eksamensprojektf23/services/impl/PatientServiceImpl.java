package com.miso.eksamensprojektf23.services.impl;


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
        .orElseThrow(() -> new EntityNotFoundException("No patient was found matching the id: " + id));
  }

  @Override
  public List<Patient> getAllPatients() {
    return patientRepository.findAll();
  }

  @Override
  public void updatePatient(Patient patientModel) {
    Patient newPatient = patientRepository.findById(patientModel.getPatientId())
        .orElseThrow(() -> new EntityNotFoundException("No patient was found matching the id: " + patientModel.getPatientId()));
    newPatient.setFirstName(patientModel.getFirstName());
    newPatient.setLastName(patientModel.getLastName());
    newPatient.setEmail(patientModel.getEmail());
    newPatient.setPhoneNumber(patientModel.getPhoneNumber());
    newPatient.setBirthdate(patientModel.getBirthdate());
    newPatient.setReasonForRefferal(patientModel.getReasonForRefferal());
    newPatient.setUsers(patientModel.getUsers());
    patientRepository.save(newPatient);
  }

  @Override
  public void savePatient(Patient patient) {
    patientRepository.save(patient);
  }

}
