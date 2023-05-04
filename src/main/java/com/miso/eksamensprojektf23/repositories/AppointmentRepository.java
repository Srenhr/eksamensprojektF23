package com.miso.eksamensprojektf23.repositories;

import com.miso.eksamensprojektf23.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
}
