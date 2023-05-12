package com.miso.eksamensprojektf23.services;


import com.miso.eksamensprojektf23.dtos.PatientDTO;
import com.miso.eksamensprojektf23.models.Patient;

import java.util.List;

public interface PatientService {

  Patient getPatientById(Long id);

  List<Patient> getAllPatients();

  void updatePatient(PatientDTO patientDTO);

  void savePatient(PatientDTO patientDTO);



}
