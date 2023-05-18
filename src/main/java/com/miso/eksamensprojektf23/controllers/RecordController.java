package com.miso.eksamensprojektf23.controllers;

import com.miso.eksamensprojektf23.models.Note;
import com.miso.eksamensprojektf23.models.Patient;
import com.miso.eksamensprojektf23.services.NoteService;
import com.miso.eksamensprojektf23.services.PatientService;
import com.miso.eksamensprojektf23.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;

@Controller
@CrossOrigin
@RequiredArgsConstructor
public class RecordController {

  private final NoteService noteService;
  private final UserService userService;
  private final PatientService patientService;

  @GetMapping("/records")
  public String getRecords(Principal principal, Model model) {
    List<Patient> patients = userService.getPatientsByUsername(principal.getName());
    // Sort the list by the id field
    patients.sort(Comparator.comparingLong(Patient::getPatientId));
    model.addAttribute("patients", patients);
    return "records";
  }

  @GetMapping("/record/{patientId}")
  public String getRecord(@PathVariable Long patientId, Model model) {
    List<Note> record = noteService.getAllNotesByPatientId(patientId);
    // Sort the list by the id field
    record.sort(Comparator.comparingLong(Note::getNoteId));
    model.addAttribute("record", record);
    return "record";
  }

  @GetMapping("/record/entry/{patientId}")
  public String newEntry(@PathVariable Long patientId, Model model, HttpSession session) {
    Note note = new Note();
    Patient patient = patientService.getPatientById(patientId);
    session.setAttribute("patient", patient);
    model.addAttribute("note", note);
    return "record_entry_form";
  }

  @PostMapping("/record/entry/save")
  public String saveEntry(Note note, Principal principal, HttpSession session, RedirectAttributes redirectAttributes) {
    note.setUser(userService.getUserByUsername(principal.getName()));
    Patient patient = (Patient) session.getAttribute("patient");
    note.setPatient(patient);
    session.removeAttribute("patient");
    noteService.save(note);
    redirectAttributes.addFlashAttribute("message", "The record has been successfully saved in the database");
    return "redirect:/records";
  }

  @GetMapping("/record/note/read/{noteId}")
  public String readEntry(@PathVariable Long noteId, Model model) {
    Note note = noteService.getNoteById(noteId);
    model.addAttribute("note", note);
    return "record_note";
  }
}
