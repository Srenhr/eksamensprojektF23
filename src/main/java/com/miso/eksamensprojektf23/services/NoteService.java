package com.miso.eksamensprojektf23.services;


import com.miso.eksamensprojektf23.models.Note;

import java.util.List;

public interface NoteService {

  Note getNoteById(Long id);

  List<Note> getAllNotesByPatientId(Long patientId);

  void save(Note note);

}
