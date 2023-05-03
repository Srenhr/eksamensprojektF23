package com.miso.eksamensprojektf23.repositories;

import com.miso.eksamensprojektf23.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
}
