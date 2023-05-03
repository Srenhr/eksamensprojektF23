package com.miso.eksamensprojektf23.repositories;

import com.miso.eksamensprojektf23.models.AppointmentNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentNoteRepository extends JpaRepository<AppointmentNote, Integer> {
}
