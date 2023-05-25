package com.miso.eksamensprojektf23.repositories;

import com.miso.eksamensprojektf23.models.Appointment;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findAppointmentsByUserUsername(@NotBlank String username);

}
