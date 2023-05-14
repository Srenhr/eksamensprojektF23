package com.miso.eksamensprojektf23.repositories;

import com.miso.eksamensprojektf23.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
