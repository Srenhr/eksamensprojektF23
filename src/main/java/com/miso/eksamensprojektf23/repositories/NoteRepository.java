package com.miso.eksamensprojektf23.repositories;

import com.miso.eksamensprojektf23.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

  List<Note> findAllByPatientPatientId (Long patientId);
}
