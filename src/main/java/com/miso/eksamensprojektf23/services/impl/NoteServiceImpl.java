package com.miso.eksamensprojektf23.services.impl;


import com.miso.eksamensprojektf23.models.Note;
import com.miso.eksamensprojektf23.repositories.NoteRepository;
import com.miso.eksamensprojektf23.services.NoteService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

  private final NoteRepository noteRepository;

  @Override
  public Note getNoteById(Long id) {
    return noteRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Der blev ikke fundet noget notat, der matcher ID'et: " + id));
  }

  @Override
  public void save(Note note) {
    noteRepository.save(note);
  }

  @Override
  public List<Note> getAllNotesByPatientId(Long patientId) {
    return noteRepository.findAllByPatientPatientId(patientId);
  }

}
